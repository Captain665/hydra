package controllers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.jsoup.parser.Parser;
import org.xhtmlrenderer.pdf.ITextRenderer;
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

			String pdfPath = "public/invoices/output.pdf";

			Document document = Jsoup.parse(htmlFile, "UTF-8");
			document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
			document.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
			String xhtml = document.html().replaceAll("[\\r\\n]+", " ").trim();

			OutputStream os = new FileOutputStream(pdfPath);
			ITextRenderer renderer = new ITextRenderer();

			renderer.setDocumentFromString(xhtml);
			renderer.layout();
			renderer.createPDF(os);

			os.close();
			return supplyAsync(() -> ok(Json.toJson("pdf generated successfully")));
		} catch (Exception e) {

			return supplyAsync(() -> badRequest(Json.toJson(e.getMessage())));
		}
	}
}
