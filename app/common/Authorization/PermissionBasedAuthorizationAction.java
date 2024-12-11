package common.Authorization;


import common.ApiResponse.ApiSuccess;
import common.ApiResponse.ApiUnAuthorize;
import common.Attrs;
import common.customer.model.CustomerModel;
import common.enums.PermissionType;
import common.user.model.UserModel;
import common.user.model.UserPermissionModel;
import jakarta.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.libs.Json;
import play.libs.typedmap.TypedKey;
import play.mvc.Action;
import play.mvc.Http;

import play.mvc.Result;
import utilities.JwtUtilities;
import utilities.RedisService;
import v2.customer.CustomerRepository;
import v2.user.UserRepository;
import v2.user.permissions.RolePermissionsRepository;

import java.security.Permission;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import static common.Attrs.AUTH_HEADER;
import static java.util.concurrent.CompletableFuture.supplyAsync;

public class PermissionBasedAuthorizationAction extends Action<PermissionBasedAuthorization> {
	private final Logger.ALogger logger = Logger.of("common.Authorization.PermissionBaseAuthorization");
	private final UserRepository userRepository;
	private final RolePermissionsRepository permissionsRepository;
	private final CustomerRepository customerRepository;
	private final RedisService<List<PermissionType>> redisPermissionService;
	private final RedisService<UserModel> redisUserService;
	private final RedisService<CustomerModel> redisCustomerService;

	@Inject
	public PermissionBasedAuthorizationAction(UserRepository userRepository, RolePermissionsRepository permissionsRepository, CustomerRepository customerRepository, RedisService<List<PermissionType>> redisPermissionService, RedisService<UserModel> redisUserService, RedisService<CustomerModel> redisCustomerService) {
		this.userRepository = userRepository;
		this.permissionsRepository = permissionsRepository;
		this.customerRepository = customerRepository;
		this.redisPermissionService = redisPermissionService;
		this.redisUserService = redisUserService;
		this.redisCustomerService = redisCustomerService;
	}


	@Override
	public CompletionStage<Result> call(Http.Request request) {
		logger.info("[" + request.id() + "] " + " json" + request.body());
		String token = request.header(AUTH_HEADER).orElse(StringUtils.EMPTY);
		if (token.isEmpty()) {
			logger.error("[" + request.id() + "] " + "token should not empty");
			return supplyAsync(() -> unauthorized(Json.toJson(new ApiUnAuthorize("token should not empty"))));
		}
		try {
			Map<String, String> contains = JwtUtilities.decodeToken(token);
			String userId = contains.get("id");
			List<String> payload = Arrays.asList(contains.get("payload").split("="));
			if (contains.get("payload").contains("role")) {
				String role = Arrays.asList(payload.get(1).split("}")).get(0);
				if (userId == null) {
					logger.info("[" + request.id() + "] " + "response: " + "Not able to verify user from auth token:");
					return supplyAsync(() -> unauthorized(Json.toJson(new ApiUnAuthorize("Not able to verify user from auth token:"))));
				}
				return checkPermission(Long.valueOf(userId), role, request);
			}
			logger.info("[" + request.id() + "] " + "response: " + "Not authorize to access this");
			return supplyAsync(() -> unauthorized(Json.toJson(new ApiUnAuthorize("Not authorize to access this"))));
		} catch (Exception e) {
			logger.info("[" + request.id() + "] " + "Response: " + "Not able to determine user from auth token.");
			return supplyAsync(() -> badRequest(Json.toJson(new ApiUnAuthorize("Not able to determine user from auth token."))));
		}
	}


	private CompletionStage<Result> checkPermission(Long userId, String role, Http.Request request) {
		final PermissionType[] callRestrictedPermission = configuration.value();
		List<PermissionType> permissionType = redisPermissionService.getClient("ROLE_" + role);
		if (permissionType != null) {
			return checkUserByRoleID(permissionType, callRestrictedPermission, userId, role, request);
		}
		return permissionsRepository.getPermissions(role).thenComposeAsync(
				rolePermissionModelStream -> {
					List<PermissionType> permissions = rolePermissionModelStream.map(UserPermissionModel::getPermission)
							.collect(Collectors.toList());
					redisPermissionService.setClient("ROLE_" + role, permissions);
					return checkUserByRoleID(permissions, callRestrictedPermission, userId, role, request);
				}
		);
	}

	private CompletionStage<Result> checkUserByRoleID(List<PermissionType> permissions, PermissionType[] restrictedPermission, Long userId, String role, Http.Request request) {
		for (PermissionType permission : restrictedPermission) {
			if (permissions.contains(permission)) {
				if ("CUSTOMER".equalsIgnoreCase(role)) {
					logger.info("[" + request.id() + "] " + " user role is  " + Attrs.CUSTOMER);
					CustomerModel customerInfo = redisCustomerService.getClient("CUSTOMER_ID_" + userId);
					if (customerInfo != null) {
						logger.info("[" + request.id() + "] " + " Call delegate by Redis data ......");
						return delegate.call(request.addAttr(Attrs.CUSTOMER, customerInfo)
								.addAttr(Attrs.ROLE, role));
					}
					return customerRepository.getById(userId).thenComposeAsync(
							customerModel -> {
								if (customerModel.isEmpty()) {
									return supplyAsync(() -> unauthorized(Json.toJson(new ApiUnAuthorize("Not authorize to access this"))));
								}
								redisCustomerService.setClient("CUSTOMER_ID_" + userId, customerModel.get());
								return delegate.call(request.addAttr(Attrs.CUSTOMER, customerModel.get())
										.addAttr(Attrs.ROLE, role));
							}
					);
				}
				logger.info("[" + request.id() + "] " + " user role is  " + Attrs.USER);
				UserModel userInfo = redisUserService.getClient("USER_ID_" + userId);
				if (userInfo != null) {
					logger.info("[" + request.id() + "] " + "Call delegate by Redis data ......");
					return delegate.call(request.addAttr(Attrs.USER, userInfo).addAttr(Attrs.ROLE, role));
				}
				return userRepository.findByUserId(userId).thenComposeAsync(
						userModel -> {
							if (userModel.isEmpty()) {
								logger.error("[" + request.id() + "] " + "response: " + "Not able to determine user from auth token.");
								return supplyAsync(() -> unauthorized(Json.toJson(new ApiUnAuthorize("Not able to determine user from auth token."))));
							}
							redisUserService.setClient("USER_ID_" + userId, userModel.get());
							return delegate.call(request.addAttr(Attrs.USER, userModel.get()).addAttr(Attrs.ROLE, role));
						}
				);
			}
			return supplyAsync(() -> unauthorized(Json.toJson(new ApiUnAuthorize("Not authorize to access this"))));
		}
		return null;
	}
}
