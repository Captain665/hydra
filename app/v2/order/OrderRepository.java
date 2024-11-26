package v2.order;

import common.order.model.OrderModel;

import java.util.concurrent.CompletionStage;

public interface OrderRepository {
    CompletionStage<OrderModel> create(OrderModel model);

}
