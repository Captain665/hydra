package v2.order;

import com.fasterxml.jackson.databind.JsonNode;
import common.ApiResponse.ApiSuccess;
import common.Attrs;
import common.Authorization.PermissionBasedAuthorization;
import common.customer.model.CustomerModel;
import common.enums.PermissionType;
import common.order.resources.OrderResource;
import common.user.model.UserModel;
import jakarta.inject.Inject;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.With;

import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;


public class OrderController extends Controller {

	public final Logger.ALogger logger = Logger.of("v2.order.controller");
	private final OrderResourceHandler handler;

	@Inject
	public OrderController(OrderResourceHandler handler) {
		this.handler = handler;
	}

	@PermissionBasedAuthorization({PermissionType.ORDER_CREATE})
	@With(OrderAction.class)
	public CompletionStage<Result> create(Http.Request request) {
		CustomerModel customerModel = request.attrs().get(Attrs.CUSTOMER);

		logger.info("[" + request.id() + "] " + "json " + request.body().asJson());
		JsonNode json = request.body().asJson();
		OrderResource resource = Json.fromJson(json, OrderResource.class);

		return handler.createOrder(resource, customerModel).thenComposeAsync(
				response -> {
					logger.info("[" + request.id() + "] " + "response is " + response.toString());
					return supplyAsync(() -> ok(Json.toJson(new ApiSuccess(response))));
				}
		);
	}

	@PermissionBasedAuthorization({PermissionType.ORDER_LIST_READ, PermissionType.ORDER_CREATE})
	public CompletionStage<Result> getOrderList(Http.Request request) {
		logger.info("[" + request.id() + "] " + " Json + " + request.body().asJson());
		UserModel userModel = null;
		CustomerModel customerModel = null;
		String role = request.attrs().get(Attrs.ROLE);
		if ("customer".equalsIgnoreCase(role)) {
			customerModel = request.attrs().get(Attrs.CUSTOMER);
		} else {
			userModel = request.attrs().get(Attrs.USER);
		}
		return handler.orderList().thenComposeAsync(
				response -> {
					logger.info("[" + request.id() + "] " + "Json -> " + response.toString());
					return supplyAsync(() -> ok(Json.toJson(new ApiSuccess(response))));
				}
		);
	}
}

