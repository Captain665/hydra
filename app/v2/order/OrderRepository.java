package v2.order;

import common.order.model.OrderModel;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface OrderRepository {
	CompletionStage<OrderModel> create(OrderModel model);

	CompletionStage<List<OrderModel>> getOrderList();


}
