package v2.order;

import common.customer.model.CustomerModel;
import common.order.OrderBuilder;
import common.order.model.OrderModel;
import common.order.resources.OrderResource;
import jakarta.inject.Inject;
import v2.customer.CustomerRepository;

import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class OrderResourceHandler {

    private final OrderRepository repository;
    private final CustomerRepository customerRepository;

    @Inject
    public OrderResourceHandler(OrderRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;

    }


    public CompletionStage<OrderResource> createOrder(OrderResource resource, CustomerModel customerModel) {
        return repository.create(build(resource, customerModel)).thenComposeAsync(
                model -> {
                    OrderResource resource1 = new OrderResource(model);
                    return supplyAsync(() -> resource1);
                }
        );
    }

    private OrderModel build(OrderResource resource, CustomerModel customerModel) {
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
                .setDeliveryCharge(resource.getDeliveryCharge())
                .setCustomerId(customerModel)
                .setOrderOutlet(resource.getOrderOutlet())
                .setOrderItem(resource.getOrderItem())
                .build();
    }

}
