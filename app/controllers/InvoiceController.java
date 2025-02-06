package controllers;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.util.ULocale;
import common.InvoiceItem;
import common.OrderInvoice;
import common.order.model.OrderModel;
import jakarta.inject.Inject;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import v2.order.OrderRepository;
import views.html.invoice;

import java.math.BigDecimal;
import java.text.RuleBasedCollator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletionStage;

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
					return ok(invoice.render(orderInvoice));
				});
	}

	public String numberToWordConvert(BigDecimal totalAmount) {
		RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(ULocale.US, RuleBasedNumberFormat.SPELLOUT);
		return formatter.format(totalAmount);
	}

	public String dateFormat(String date) {
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDateTime dateTime = LocalDateTime.parse(date, inputFormatter);
		return dateTime.format(outputFormatter);
	}
}
