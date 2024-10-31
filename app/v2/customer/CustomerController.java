package v2.customer;import com.fasterxml.jackson.databind.JsonNode;import common.ApiResponse.ApiFailure;import common.ApiResponse.ApiSuccess;import common.Authorization.PermissionBasedAuthorization;import common.customer.resources.CustomerResource;import common.enums.PermissionType;import jakarta.inject.Inject;import play.Logger;import play.libs.Json;import play.mvc.Controller;import play.mvc.Http;import play.mvc.Result;import utilities.JwtUtilities;import java.util.HashMap;import java.util.Map;import java.util.Optional;import java.util.concurrent.CompletableFuture;import java.util.concurrent.CompletionStage;import static java.util.concurrent.CompletableFuture.supplyAsync;public class CustomerController extends Controller {    private final CustomerResourceHandler handler;    private final Logger.ALogger logger = Logger.of("application.CustomerController");    @Inject    public CustomerController(CustomerResourceHandler handler) {        this.handler = handler;    }    public CompletionStage<Result> create(Http.Request request) {        JsonNode json = request.body().asJson();        logger.info("[" + request.id() + "] " + "json " + json.toString());        CustomerResource resource = Json.fromJson(json, CustomerResource.class);        if (json.isEmpty()) {            logger.error("[" + request.id() + "] + error -> Request body can not be empty");            return supplyAsync(() -> badRequest(Json.toJson(new ApiFailure("Request body can not be empty"))));        }        return handler.create(resource).thenApplyAsync(                customer -> {                    Map<String, String> payload = new HashMap<>();                    payload.put("ROLE", "CUSTOMER");                    customer.jwt = JwtUtilities.generateToken(customer.getId().toString(), payload);                    logger.info("[" + request.id() + "] " + "response : " + customer);                    return created(Json.toJson(new ApiSuccess(customer)));                }        );    }    @PermissionBasedAuthorization(PermissionType.CUSTOMER_LIST_READ)    public CompletionStage<Result> getAll(Http.Request request) {        logger.info("[" + request.id() + "] " + "json " + Json.toJson(request));        return supplyAsync(                () -> ok(Json.toJson(new ApiSuccess("test passed")))        );    }    @PermissionBasedAuthorization(PermissionType.CUSTOMER_READ)    public CompletionStage<Result> get(Http.Request request, Long id) {        JsonNode json = Json.toJson(request.body());        logger.info("[" + request.id() + "] " + "json " + json);        return handler.getCustomerDetails(id).thenComposeAsync(                details -> {                    if (details.isPresent()) {                        Map<String, String> payload = new HashMap<>();                        payload.put("role", "CUSTOMER");                        final String token = JwtUtilities.generateToken(details.get().id.toString(), payload);                        details.get().setJwt(token);                        return supplyAsync(() -> ok(Json.toJson(                                new ApiSuccess(details))));                    }                    return supplyAsync(() -> notFound(Json.toJson(                            new ApiFailure("User not found"))));                }        );    }}