package controllers;

import common.ApiResponse.ApiSuccess;
import play.libs.Json;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static play.mvc.Results.ok;

public class InvoiceHelper {

	public CompletionStage<Result> converter() {
		return supplyAsync(() -> ok(Json.toJson(new ApiSuccess("ok"))));
	}
}
