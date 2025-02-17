package controllers;

import play.Logger;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static play.mvc.Results.ok;

public class InvoiceHelper {

	Logger.ALogger logger = Logger.of("Invoice helper");

	public CompletionStage<Result> converter(Http.Request request) {
		logger.info("[" + request.id() + "] " + request.toString());
		String htmlContent = "<html><body><h1>Hello, PDF!</h1><p>This is a sample PDF generated from HTML.</p></body></html>";
		String outputFilePath = "public/invoices/pdfbox.pdf";

		try (OutputStream outputStream = new FileOutputStream(outputFilePath)) {
			PdfRendererBuilder builder = new PdfRendererBuilder();

			// Provide the HTML content
			builder.useFastMode();
			builder.withFile(new File("public/invoices/ticket.html"));

			// Specify the output file
			builder.toStream(outputStream);

			// Build and create the PDF
			builder.run();

			System.out.println("PDF generated successfully at: " + outputFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return supplyAsync(() -> ok(Json.toJson("PDF is generate")));
	}
}
