package v2.order;

import common.customer.model.CustomerModel;
import common.order.model.OrderItemModel;
import common.order.model.OrderModel;
import common.order.model.OrderOutletModel;
import common.order.resources.OrderSearchResource;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import play.db.jpa.JPAApi;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Singleton
public class JPAOrderRepository implements OrderRepository {

	private final JPAApi jpaApi;
	private final OrderExecutionContext ec;

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

	@Override
	public CompletionStage<List<OrderModel>> getOrderList(OrderSearchResource searchResource) {
		return supplyAsync(() -> wrap(em -> {
			int offSet = (searchResource.page - 1) * searchResource.size;
			Query query = em.createNativeQuery("SELECT * from Orders o " +
							"Limit " + offSet + " , " + searchResource.size,
					OrderModel.class);
			return query.getResultList();
		}), ec);
	}

	@Override
	public CompletionStage<List<OrderModel>> getCustomerOrderList(CustomerModel model, OrderSearchResource searchResource) {
		return supplyAsync(() -> wrap(em -> {
			int offSet = (searchResource.page - 1) * searchResource.size;
			Long customerId = model.getId();
			Query query = em.createNativeQuery("SELECT * from Orders o " +
							"where o.customer_id = " + customerId +
							" Limit " + offSet + " , " + searchResource.size,
					OrderModel.class);
			return query.getResultList();
		}), ec);
	}

	@Override
	public CompletionStage<Integer> count(CustomerModel model) {
		return supplyAsync(() -> wrap(em -> {
			String queryString = "Select count(*) from Orders o where true = true ";
			if (model != null) {
				queryString += "AND o.customer_id =" + model.getId();
			}
			Query query = em.createNativeQuery(queryString);
			return ((Long) query.getSingleResult()).intValue();
		}), ec);
	}

	@Override
	public CompletionStage<Optional<OrderModel>> getOrderDetailById(Long id) {
		return supplyAsync(() -> wrap(em -> {
			TypedQuery<OrderModel> query = em.createQuery("SELECT m from OrderModel m where m.id = :id", OrderModel.class).setParameter("id", id);
			try {
				return Optional.of(query.getSingleResult());
			} catch (NoResultException e) {
				return Optional.empty();
			}
		}));
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
