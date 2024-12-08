package v2.order;

import common.customer.model.CustomerModel;
import common.order.model.OrderModel;
import common.order.resources.OrderSearchResource;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public interface OrderRepository {
	CompletionStage<OrderModel> create(OrderModel model);

	CompletionStage<List<OrderModel>> getOrderList(OrderSearchResource searchResource);

	CompletionStage<List<OrderModel>> getCustomerOrderList(CustomerModel model, OrderSearchResource searchResource);

	CompletionStage<Integer> count(CustomerModel model);

	CompletionStage<Optional<OrderModel>> getOrderDetailById(Long id);

}
