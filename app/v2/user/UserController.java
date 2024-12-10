package v2.user;

import com.fasterxml.jackson.databind.JsonNode;
import common.ApiResponse.ApiFailure;
import common.ApiResponse.ApiSuccess;
import common.Attrs;
import common.user.resources.UserResource;
import jakarta.inject.Inject;
import org.redisson.api.RedissonClient;
import org.w3c.dom.Attr;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utilities.JwtUtilities;
import utilities.RedisClientUtilities;
import utilities.RedisSerialize;
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
		String value = redisService.getClient("user-" + userResource.mobile + ":" + userResource.password);
		if (value != null) {
			try {
				logger.info("found redis data ");
				RedisSerialize<UserResource> serialize = new RedisSerialize<>(UserResource.class);
				UserResource resource = serialize.deserialize(value);
				return supplyAsync(() -> ok(Json.toJson(new ApiSuccess(resource))));
			} catch (Exception e) {
				e.printStackTrace();
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
					redisService.setClient("user-" + userResource.mobile + ":" + userResource.password, String.valueOf(user));
					return supplyAsync(() -> ok(Json.toJson(new ApiSuccess(user))));
				}
		);
	}
}
