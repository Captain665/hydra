package v2.order;

import common.customer.model.CustomerModel;
import common.order.model.OrderModel;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface OrderRepository {
	CompletionStage<OrderModel> create(OrderModel model);

	CompletionStage<List<OrderModel>> getOrderList();

	CompletionStage<List<OrderModel>> getCustomerOrderList(CustomerModel model);


}
