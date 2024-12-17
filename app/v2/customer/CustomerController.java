package v2.customer;

import com.fasterxml.jackson.databind.JsonNode;
import common.ApiResponse.ApiFailure;
import common.ApiResponse.ApiSuccess;
import common.Authorization.PermissionBasedAuthorization;
import common.customer.resources.CustomerResource;
import common.customer.resources.CustomerResponseResource;
import common.enums.PermissionType;
import jakarta.inject.Inject;
import jakarta.validation.*;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utilities.JwtUtilities;
import utilities.ResourceValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;


public class CustomerController extends Controller {

	private final CustomerResourceHandler handler;
	private final ResourceValidator<CustomerResource> validator;
	private final Logger.ALogger logger = Logger.of("application.CustomerController");

	@Inject
	public CustomerController(CustomerResourceHandler handler, ResourceValidator<CustomerResource> validator) {
		this.handler = handler;
		this.validator = validator;
	}

	public CompletionStage<Result> create(Http.Request request) {
		JsonNode json = request.body().asJson();
		logger.info("[" + request.id() + "] " + "json " + json.toString());
		CustomerResource resource = Json.fromJson(json, CustomerResource.class);
		if (json.isEmpty()) {
			logger.error("[" + request.id() + "] + error : Request body can not be empty");
			return supplyAsync(() -> badRequest(Json.toJson(new ApiFailure("Request body can not be empty"))));
		}
		String validation = validator.resourcePreValidation(resource);
		if (validation != null) {
			logger.error("[" + request.id() + "] + error : " + validation);
			return supplyAsync(() -> badRequest(Json.toJson(new ApiFailure("error :- " + validation))));
		}

		return handler.create(resource).thenApplyAsync(
				customer -> {
					Map<String, String> payload = new HashMap<>();
					payload.put("role", "CUSTOMER");
					customer.jwt = JwtUtilities.generateToken(customer.getId().toString(), payload);
					logger.info("[" + request.id() + "] " + "response : " + customer);
					return created(Json.toJson(new ApiSuccess(customer)));
				}
		);
	}

	@PermissionBasedAuthorization({PermissionType.CUSTOMER_LIST_READ})
	public CompletionStage<Result> getCustomerList(Http.Request request) {
		logger.info("[" + request.id() + "] " + "json " + Json.toJson(request));

		return handler.getCustomerCount().thenComposeAsync(
				count -> handler.getAllCustomerList().thenApplyAsync(
						customer -> {
							CustomerResponseResource customerResponseResource;
							customerResponseResource = new CustomerResponseResource(count, customer);

							if (customer.isEmpty()) {
								logger.error("[" + request.id() + "] " + "response ->  " + "No result");
								return ok(Json.toJson(new ApiSuccess(customerResponseResource)));
							}

							logger.info("[" + request.id() + "] " + "response: " + customerResponseResource.toString());
							return ok(Json.toJson(new ApiSuccess(customerResponseResource)));
						}
				)
		);
	}

	@PermissionBasedAuthorization(PermissionType.CUSTOMER_READ)
	public CompletionStage<Result> get(Http.Request request, Long id) {
		JsonNode json = Json.toJson(request.body());
		logger.info("[" + request.id() + "] " + "json " + json);
		return handler.getCustomerDetails(id).thenComposeAsync(
				details -> {
					if (details.isPresent()) {
						Map<String, String> payload = new HashMap<>();
						payload.put("role", "CUSTOMER");
						final String token = JwtUtilities.generateToken(details.get().id.toString(), payload);
						details.get().setJwt(token);
						logger.info("[" + request.id() + "] " + "response: " + details.toString());
						return supplyAsync(() -> ok(Json.toJson(new ApiSuccess(details))));
					}
					logger.info("[" + request.id() + "] " + "response: " + "Customer not found");
					return supplyAsync(() -> notFound(Json.toJson(new ApiFailure("Customer not found"))));
				}
		);
	}

	@PermissionBasedAuthorization({PermissionType.CUSTOMER_DELETE})
	public CompletionStage<Result> delete(Http.Request request, Long id) {
		logger.info("[" + request.id() + "] " + "json" + request.body().asJson());
		return handler.deleteById(id).thenComposeAsync(
				model -> {
					if (model.isPresent()) {
						logger.info("[" + request.id() + "] " + "response: " + "DELETE successfully");
						return supplyAsync(() -> ok(Json.toJson(new ApiSuccess("DELETE successfully"))));
					}
					logger.info("[" + request.id() + "] " + "response: " + "Customer not found");
					return supplyAsync(() -> notFound(Json.toJson(new ApiFailure("customer not found"))));
				});
	}
}
