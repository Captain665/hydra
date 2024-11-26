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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static play.mvc.Results.ok;

public class UserController extends Controller {
    private final Logger.ALogger logger = Logger.of("application.UserController");
    private final UserResourceHandler handler;

    @Inject
    public UserController(UserResourceHandler handler) {
        this.handler = handler;
    }

    public CompletionStage<Result> login(Http.Request request) {
        UserResource userResource = Json.fromJson(request.body().asJson(), UserResource.class);
        logger.info("[" + request.id() + "] " + "json " + userResource);
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
                    return supplyAsync(() -> ok(Json.toJson(new ApiSuccess(user))));
                }
        );
    }
}
