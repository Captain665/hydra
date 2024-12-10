package v2.user;

import common.ApiResponse.ApiFailure;
import common.ApiResponse.ApiSuccess;
import common.user.resources.UserResource;
import jakarta.inject.Inject;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utilities.JwtUtilities;
import utilities.RedisService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class UserController extends Controller {
	private final Logger.ALogger logger = Logger.of("application.UserController");
	private final UserResourceHandler handler;

	private final RedisService redisService;

	@Inject
	public UserController(UserResourceHandler handler, RedisService redisService) {
		this.handler = handler;
		this.redisService = redisService;
	}

	public CompletionStage<Result> login(Http.Request request) {
		UserResource userResource = Json.fromJson(request.body().asJson(), UserResource.class);
		logger.info("[" + request.id() + "] " + "json " + userResource);
		UserResource bucketValue = redisService.getClient("user_" + userResource.mobile + "_" + userResource.password);
		if (bucketValue != null) {
			try {
				logger.info("Redis data found");
				return supplyAsync(() -> ok(Json.toJson(new ApiSuccess(bucketValue))));
			} catch (Exception e) {
				return supplyAsync(() -> ok(Json.toJson(new ApiFailure("Exception " + e.getMessage()))));
			}
		}
		return handler.findByUserNamePassword(userResource.mobile, userResource.getPassword()).thenComposeAsync(
				optional -> {
					if (optional.isEmpty()) {
						logger.error("[" + request.id() + "] " + "Response: username or password not correct.");
						return supplyAsync(() -> badRequest(Json.toJson(new ApiFailure("Response: username or password not correct."))));
					}
					UserResource user = optional.get();
					Map<String, String> payload = new HashMap<>();
					payload.put("role", user.getRole());
					final String token = JwtUtilities.generateToken(user.getId().toString(), payload);
					user.setJwtToken(token);
					logger.info("[" + request.id() + "] " + "Response: " + user);
					redisService.setClient("user_" + userResource.mobile + "_" + userResource.password, user);
					return supplyAsync(() -> ok(Json.toJson(new ApiSuccess(user))));
				}
		);
	}
}
