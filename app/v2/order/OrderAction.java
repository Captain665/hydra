package v2.order;

import common.ApiResponse.ApiFailure;
import common.ApiResponse.ApiSuccess;
import common.Attrs;
import play.Logger;
import play.libs.Json;
import play.libs.typedmap.TypedKey;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class OrderAction extends Action.Simple {

	private final Logger.ALogger logger = Logger.of("v2.order.OrderAction");

	@Override
	public CompletionStage<Result> call(Http.Request req) {
		if (req.body().asJson().isEmpty()) {
			logger.error("[" + req.id() + "] " + "Response -> Request body should not empty");
			return supplyAsync(() -> ok(Json.toJson(new ApiFailure("request body should not empty"))));
		}
		logger.info("[" + req.id() + "] " + "Response -> Call delegate from the action class");
		return delegate.call(req.addAttr(TypedKey.create("Role"), "customer"));
	}
}
