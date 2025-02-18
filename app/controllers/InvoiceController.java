package controllers;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.util.ULocale;
import com.itextpdf.html2pdf.HtmlConverter;
import common.OrderInvoice;
import jakarta.inject.Inject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.jsoup.parser.Parser;
import org.xhtmlrenderer.pdf.ITextRenderer;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import v2.order.OrderRepository;
import views.html.invoice;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletionStage;

import java.io.*;


public class InvoiceController extends Controller {
	private final OrderRepository repository;
	Logger.ALogger logger = Logger.of("invoiceController");

	@Inject
	public InvoiceController(OrderRepository repository) {
		this.repository = repository;
	}


	public CompletionStage<Result> invoice(Http.Request request, Long orderId) {
		logger.info("[" + request.id() + "] " + request.path());
		return repository.getOrderDetailById(orderId)
				.thenApplyAsync(orderModel -> {
					OrderInvoice orderInvoice = new OrderInvoice(orderModel.get());
					orderInvoice.setAmountWords(numberToWordConvert(orderModel.get().getCustomerPayable()));
					orderInvoice.setInvoiceDate(dateFormat(orderModel.get().getDeliveryDate()));
					File pdfFile = htmlToPDFConverter(invoice.render(orderInvoice).toString(), "public/invoices/");
					logger.info("file is generate");
//					return ok(invoice.render(orderInvoice));
					return ok(pdfFile);
				});
	}

	private String numberToWordConvert(BigDecimal totalAmount) {
		RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(ULocale.US, RuleBasedNumberFormat.SPELLOUT);
		return formatter.format(totalAmount);
	}

	private String dateFormat(String date) {
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDateTime dateTime = LocalDateTime.parse(date, inputFormatter);
		return dateTime.format(outputFormatter);
	}

	private static String htmlToXhtml(String html) {
		// ✅ Use Jsoup to parse and enforce XHTML compliance
		Document document = Jsoup.parse(html, "", Parser.xmlParser());
		document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
		document.outputSettings().escapeMode(Entities.EscapeMode.xhtml);

		return document.html().replaceAll("[\\r\\n]+", " ").trim();
	}

	public File htmlToPDFConverter(String htmlContent, String outputDirectory) {
		try {
			logger.info("Generating PDF...");

			// Ensure output directory exists
			File directory = new File(outputDirectory);
			if (!directory.exists()) {
				directory.mkdirs();
			}

			// Generate a unique filename
			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
			File outputPdfPath = new File(outputDirectory + "/invoice_" + timestamp + ".pdf");

			logger.info("Saving PDF to: " + outputPdfPath);

			String xhtmlContent = htmlToXhtml(htmlContent);

			OutputStream os = new FileOutputStream(outputPdfPath);
			ITextRenderer renderer = new ITextRenderer();

			// ✅ Use Flying Saucer Renderer to process the HTML string
			renderer.setDocumentFromString(xhtmlContent);
			renderer.layout();
			renderer.createPDF(os);
			os.close();

			logger.info("PDF saved at: " + outputPdfPath);
			return outputPdfPath;
		} catch (Exception e) {
			logger.error("Error generating PDF", e);
			return null;
		}
	}

}
