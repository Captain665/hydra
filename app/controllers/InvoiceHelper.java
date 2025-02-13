package controllers;

import com.itextpdf.html2pdf.HtmlConverter;
import play.libs.Json;
import play.mvc.Result;

import java.io.*;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class InvoiceHelper {

	public CompletionStage<Result> converter() {
		try {
			File htmlFile = new File("public/invoices/Heliyatra.html");
			String pdfPath = "public/invoices/itext.pdf";
			OutputStream pdfOutputStream = new FileOutputStream(pdfPath);
			InputStream htmlInputStream = new FileInputStream(htmlFile);
			HtmlConverter.convertToPdf(htmlInputStream, pdfOutputStream);
			pdfOutputStream.close();
			htmlInputStream.close();
			return supplyAsync(() -> ok("pdf is generate"));
		} catch (Exception e) {
			return supplyAsync(() -> badRequest(Json.toJson(e.getMessage())));
		}
	}
}
