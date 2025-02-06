package controllers;

import common.ApiResponse.ApiFailure;
import play.Logger;
import play.http.HttpErrorHandler;
import play.libs.Json;
import play.mvc.*;

import java.io.File;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class HomeController extends Controller implements HttpErrorHandler {

	private final Logger.ALogger logger = Logger.of("Controller.HomeController");

	public Result index() {
		return ok(views.html.index.render());
//		return ok("OK");
	}

	public Result health() {
		return ok("health is OK");
	}


	@Override
	public CompletionStage<Result> onClientError(Http.RequestHeader request, int statusCode, String message) {
		logger.info("[" + request.id() + "] " + "request " + request + " statusCode " + statusCode + " message " + message);
		return supplyAsync(() -> {
			logger.info("[" + request.id() + "] " + " response " + message);
			return badRequest(Json.toJson(
					new ApiFailure(message)));
		});
	}

	@Override
	public CompletionStage<Result> onServerError(Http.RequestHeader request, Throwable exception) {
		logger.info("[" + request.id() + "] " + "request " + request + " exception " + exception.getMessage());
		return supplyAsync(() -> {
			logger.info("[" + request.id() + "] " + " response " + exception.getMessage());
			return badRequest(Json.toJson(
					new ApiFailure(exception.getMessage())));
		});
	}
}
