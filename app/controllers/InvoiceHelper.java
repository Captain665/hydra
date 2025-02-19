package controllers;

import com.itextpdf.html2pdf.HtmlConverter;
import play.Logger;

import play.mvc.Http;
import play.mvc.Result;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.runAsync;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static play.mvc.Results.ok;

public class InvoiceHelper {
	private final Logger.ALogger logger = Logger.of("utilities.InvoiceHelper.converter");

	public CompletionStage<Result> converter(Http.Request request) {
		return supplyAsync(() -> {
			logger.info("[" + request.id() + "] " + " Getting request for PDF generator");
			File htmlFile = new File("public/invoices/Heliyatra.html");
			String outputDirectory = "public/invoices/";
			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
			File outputPdfPath = new File(outputDirectory + "/invoice_" + timestamp + ".pdf");
			runAsync(() -> {
				try {
					HtmlConverter.convertToPdf(new FileInputStream(htmlFile), new FileOutputStream(outputPdfPath));
				} catch (Exception e) {
					logger.error("[" + request.id() + "] " + "Error: " + e.getMessage());
					e.printStackTrace();
				}
			});
			logger.info("[" + request.id() + "] " + "Response	: Saving PDF at " + outputPdfPath);
			return ok(new File("public/invoices/itext.pdf"));
		});
	}
}
