package common.Authorization;


import common.ApiResponse.ApiUnAuthorize;
import common.Attrs;
import common.enums.PermissionType;
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
import v2.customer.CustomerRepository;
import v2.user.UserRepository;
import v2.user.permissions.RolePermissionsRepository;

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

	@Inject
	public PermissionBasedAuthorizationAction(UserRepository userRepository, RolePermissionsRepository permissionsRepository, CustomerRepository customerRepository) {
		this.userRepository = userRepository;
		this.permissionsRepository = permissionsRepository;
		this.customerRepository = customerRepository;
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
		return permissionsRepository.getPermissions(role).thenComposeAsync(
				rolePermissionModelStream -> {
					List<PermissionType> permissions = rolePermissionModelStream.map(UserPermissionModel::getPermission)
							.collect(Collectors.toList());
					for (PermissionType permission : callRestrictedPermission) {
						if (permissions.contains(permission)) {
							if ("CUSTOMER".equalsIgnoreCase(role)) {
								return customerRepository.getById(userId).thenComposeAsync(
										customerModel -> {
											if (customerModel.isEmpty()) {
												return supplyAsync(() -> unauthorized(Json.toJson(new ApiUnAuthorize("Not authorize to access this"))));
											}
											return delegate.call(request.addAttr(Attrs.CUSTOMER, customerModel.get())
													.addAttr(Attrs.ROLE, role));
										}

								);
							}
							return userRepository.findByUserId(userId).thenComposeAsync(
									userModel -> {
										if (userModel.isEmpty()) {
											logger.error("[" + request.id() + "] " + "response: " + "Not able to determine user from auth token.");
											return supplyAsync(() -> unauthorized(Json.toJson(new ApiUnAuthorize("Not able to determine user from auth token."))));
										}
										return delegate.call(request.addAttr(Attrs.USER, userModel.get()).addAttr(Attrs.ROLE, role));
									}
							);
						}
						return supplyAsync(() -> unauthorized(Json.toJson(new ApiUnAuthorize("Not authorize to access this"))));
					}
					return supplyAsync(() -> unauthorized(Json.toJson(new ApiUnAuthorize("Not authorize to access this"))));
				}
		);
	}
}
