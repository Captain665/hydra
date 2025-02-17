package controllers;

import play.libs.Json;
import play.mvc.Result;
//import com.cete.dynamicpdf.html.Converter;
//import com.cete.dynamicpdf.html.ConversionOptions;


import java.io.File;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static play.mvc.Results.ok;

public class InvoiceHelper {
	public CompletionStage<Result> converter() {
		File htmlFile = new File("public/invoices/ticket.html");
		File pdfFile = new File("public/invoices/test.pdf");
//		Converter.Convert(htmlContent, outputFilePath, options);

		return supplyAsync(() -> ok(Json.toJson(htmlFile)));

	}
}
