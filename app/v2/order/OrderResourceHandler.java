package v2.order;

import common.customer.model.CustomerModel;
import common.order.OrderBuilder;
import common.order.model.OrderModel;
import common.order.resources.OrderListResponseResource;
import common.order.resources.OrderResource;
import common.order.resources.OrderResponseResource;
import common.order.resources.OrderSearchResource;
import jakarta.inject.Inject;
import play.Logger;
import play.api.libs.json.Json;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;


import static java.util.concurrent.CompletableFuture.supplyAsync;

public class OrderResourceHandler {

	private final OrderRepository repository;

	@Inject
	public OrderResourceHandler(OrderRepository repository) {
		this.repository = repository;
	}

	public CompletionStage<OrderResponseResource> createOrder(OrderResource resource, CustomerModel customerModel) {
		return repository.create(build(resource, customerModel)).thenComposeAsync(
				model -> {
					OrderResponseResource resource2 = new OrderResponseResource(model);
					return supplyAsync(() -> resource2);
				}
		);
	}

	public CompletionStage<List<OrderListResponseResource>> orderList(CustomerModel customerModel, OrderSearchResource searchResource) {
		if (customerModel != null) {
			return repository.getCustomerOrderList(customerModel, searchResource).thenApplyAsync(
					list -> list.stream().map(OrderListResponseResource::new)
							.collect(Collectors.toList()));
		}
		return repository.getOrderList(searchResource).thenApplyAsync(
				model -> model.stream().map(OrderListResponseResource::new)
						.collect(Collectors.toList()));
	}

	public CompletionStage<Integer> countOrderList(CustomerModel model) {
		return repository.count(model);
	}

	public CompletionStage<Optional<OrderResponseResource>> orderDetail(Long id) {
		return repository.getOrderDetailById(id).thenApplyAsync(
				model ->
						model.map(OrderResponseResource::new)
		);
	}

	private OrderModel build(OrderResource resource, CustomerModel customerModel) {

		BigDecimal subtotal = resource.getOrderItem().stream().map(item -> item.getBasePrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal tax = subtotal.multiply(BigDecimal.valueOf(0.05));
		BigDecimal totalAmount = subtotal.add(tax).setScale(0, RoundingMode.HALF_UP);
		BigDecimal customerPayable = totalAmount.add(resource.getDeliveryCharge()).subtract(resource.getDiscount()).setScale(0, RoundingMode.HALF_UP);

		return new OrderBuilder()
				.setTrainNo(resource.getTrainNo())
				.setTrainName(resource.getTrainName())
				.setStationCode(resource.getStationCode())
				.setStationName(resource.getStationName())
				.setBerth(resource.getBerth())
				.setCoach(resource.getCoach())
				.setDeliveryDate(resource.getDeliveryDate())
				.setBookingDate(resource.getBookingDate())
				.setOrderFrom(resource.getOrderFrom())
				.setOrderStatus(resource.getOrderStatus())
				.setPnr(resource.getPnr())
				.setPaymentType(resource.getPaymentType())
				.setSubTotal(subtotal)
				.setTax(tax)
				.setTotalAmount(totalAmount)
				.setDeliveryCharge(resource.getDeliveryCharge())
				.setDiscount(resource.getDiscount())
				.setCustomerPayable(customerPayable)
				.setCustomerId(customerModel)
				.setOrderOutlet(resource.getOrderOutlet())
				.setOrderItem(resource.getOrderItem())
				.setCreatedBy(customerModel != null ? "customer" : "admin")
				.build();
	}

}
