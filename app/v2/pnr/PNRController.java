package v2.pnr;

import com.mysql.cj.log.Log;
import common.ApiResponse.ApiFailure;
import common.ApiResponse.ApiSuccess;
import common.Authorization.PermissionBasedAuthorization;
import common.enums.PermissionType;
import org.checkerframework.checker.units.qual.A;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class PNRController extends Controller {

	final Logger.ALogger logger = Logger.of("application.PNRController");
	final JourneyClient client = new JourneyClient();

	@PermissionBasedAuthorization({PermissionType.PNR_READ})
	public CompletionStage<Result> getPNRDetail(Http.Request request, Long pnr) {
		logger.info("[" + request.id() + "] " + "json " + request);

		return client.getJourneyInfoByPNR(pnr, request)
				.thenComposeAsync(
						optional -> optional.map(
								pnrInfo -> {
									if (!pnrInfo.message.isEmpty()) {
										return supplyAsync(() -> badRequest(Json.toJson(new ApiFailure("PNR info not found"))));
									}
									logger.info("[" + request.id() + "] " + "response " + pnrInfo.result);
									return CompletableFuture.completedFuture(ok(Json.toJson(new ApiSuccess(pnrInfo.result))));
								}).orElseGet(() -> {
									logger.error("[" + request.id() + "] " + "message " + "pnr info not found");
									return CompletableFuture.completedFuture(notFound(Json.toJson(new ApiFailure("pnr info not found"))));
								}
						));
	}
}
