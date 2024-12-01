package v2.order;

import common.order.model.OrderItemModel;
import common.order.model.OrderModel;
import common.order.model.OrderOutletModel;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import play.Logger;
import play.db.jpa.JPAApi;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Singleton
public class JPAOrderRepository implements OrderRepository {

	private final JPAApi jpaApi;
	private final OrderExecutionContext ec;

	private final Logger.ALogger logger = Logger.of("order.repository");

	@Inject
	public JPAOrderRepository(JPAApi jpaApi, OrderExecutionContext ec) {
		this.jpaApi = jpaApi;
		this.ec = ec;
	}


	@Override
	public CompletionStage<OrderModel> create(OrderModel model) {
		return supplyAsync(() -> wrap(em -> {

			List<OrderItemModel> orderItems = model.getOrderItem();
			OrderOutletModel orderOutlet = model.getOrderOutlet();

			logger.info("subtotal amount is " + model.getSubTotal());

			OrderModel orderModel = insert(em, model);

			if (orderItems != null) {
				List<OrderItemModel> items = orderItems.stream().map(
						orderItem -> {
							BigDecimal tax = orderItem.getBasePrice().multiply(BigDecimal.valueOf(0.05));
							orderItem.setTax(tax);
							orderItem.setSellingPrice(orderItem.getBasePrice().add(tax));
							orderItem.setOrder(orderModel);
							return insert(em, orderItem);
						}
				).collect(Collectors.toList());
				orderModel.setOrderItem(items);
			}

			if (orderOutlet != null) {
				orderOutlet.setOrder(orderModel);
				OrderOutletModel orderOutletModel = insert(em, orderOutlet);
				orderModel.setOrderOutlet(orderOutletModel);
			}
			return orderModel;
		}), ec);
	}

	private <T> T wrap(Function<EntityManager, T> function) {
		return jpaApi.withTransaction(function);
	}

	private OrderModel insert(EntityManager em, OrderModel model) {
		return em.merge(model);
	}

	private OrderItemModel insert(EntityManager em, OrderItemModel model) {
		return em.merge(model);
	}

	private OrderOutletModel insert(EntityManager em, OrderOutletModel model) {
		return em.merge(model);
	}
}
