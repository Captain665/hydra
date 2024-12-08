package v2.order;

import com.fasterxml.jackson.databind.JsonNode;
import common.ApiResponse.ApiFailure;
import common.ApiResponse.ApiSuccess;
import common.ApiResponse.ApiUnAuthorize;
import common.Attrs;
import common.Authorization.PermissionBasedAuthorization;
import common.customer.model.CustomerModel;
import common.enums.PermissionType;
import common.order.resources.OrderListCountResponseResource;
import common.order.resources.OrderResource;
import common.order.resources.OrderSearchResource;
import common.user.model.UserModel;
import jakarta.inject.Inject;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.With;

import java.util.Objects;
import java.util.Optional;
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
	public CompletionStage<Result> getOrderList(OrderSearchResource resource, Http.Request request) {
		logger.info("[" + request.id() + "] " + " resource " + resource.toString());
		UserModel userModel;
		CustomerModel customerModel;
		String role = request.attrs().get(Attrs.ROLE);
		if ("customer".equalsIgnoreCase(role)) {
			customerModel = request.attrs().get(Attrs.CUSTOMER);
			logger.info("[" + request.id() + "] " + "customerModel -> " + Json.toJson(customerModel));
		} else {
			customerModel = null;
			userModel = request.attrs().get(Attrs.USER);
			logger.info("[" + request.id() + "] " + "userModel -> " + Json.toJson(userModel));
			if (userModel == null) {
				logger.info("[" + request.id() + "] " + "response -> " + "Not able to determine user from auth token");
				return supplyAsync(() -> badRequest(Json.toJson(new ApiFailure("Not able to determine user from auth token"))));
			}
		}
		return handler.countOrderList(customerModel).thenComposeAsync(
				count -> handler.orderList(customerModel, resource).thenApplyAsync(
						orders -> {
							OrderListCountResponseResource orderListCountResponseResource;
							orderListCountResponseResource = new OrderListCountResponseResource(count, orders);
							logger.info("[" + request.id() + "] " + "response -> " + Json.toJson(orderListCountResponseResource));
							return ok(Json.toJson(new ApiSuccess(orderListCountResponseResource)));
						}
				)
		);
	}

	@PermissionBasedAuthorization({PermissionType.ORDER_READ})
	public CompletionStage<Result> getOrderDetails(Http.Request request, Long id) {
		CustomerModel customerModel = request.attrs().get(Attrs.CUSTOMER);
		logger.info("[" + request.id() + "] " + "order_id -> " + id);
		return handler.orderDetail(id).thenApplyAsync(
				model -> {
					if (model.isPresent()) {
						if (!Objects.equals(customerModel.getId(), model.get().getCustomerId())) {
							return unauthorized(Json.toJson(new ApiUnAuthorize("Not Authorize to access this")));
						}
						logger.info("[" + request.id() + "] " + "response -> " + Json.toJson(model));
						return ok(Json.toJson(new ApiSuccess(model)));
					}
					logger.error("[" + request.id() + "] " + "response -> " + "Order not found");
					return badRequest(Json.toJson(new ApiFailure("Order not found")));
				}
		);
	}
}
