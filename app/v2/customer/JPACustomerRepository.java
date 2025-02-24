package v2.customer;

import common.customer.model.CustomerModel;
import common.customer.resources.CustomerResource;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import play.Logger;
import play.db.jpa.JPAApi;

import javax.swing.text.html.Option;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Singleton
public class JPACustomerRepository implements CustomerRepository {

	private final JPAApi jpaApi;
	private final CustomerExecutionContext ce;


	@Inject
	public JPACustomerRepository(JPAApi jpaApi, CustomerExecutionContext ce) {
		this.jpaApi = jpaApi;
		this.ce = ce;
	}

	@Override
	public CompletionStage<CustomerModel> createOrGet(CustomerModel model) {
		return supplyAsync(() -> wrap(em -> {
			TypedQuery<CustomerModel> query = em.createQuery("SELECT m from CustomerModel m " +
							"Where mobile = :mobile and emailId = :emailId", CustomerModel.class)
					.setParameter("mobile", model.getMobile())
					.setParameter("emailId", model.getEmailId());
			try {
				CustomerModel modelInDB = query.setMaxResults(1).getSingleResult();
				model.setId(modelInDB.getId());
				model.setActive(modelInDB.isActive());
				model.setNewUser(false);
				return em.merge(model);
			} catch (NoResultException e) {
				model.setActive(true);
				model.setNewUser(true);
				return em.merge(model);
			}
		}), ce);
	}

	private <T> T wrap(Function<EntityManager, T> function) {
		return jpaApi.withTransaction(function);
	}

	@Override
	public CompletionStage<CustomerModel> save(CustomerModel customerModel) {
		return null;
	}

	@Override
	public CompletionStage<Optional<CustomerModel>> getById(Long id) {
		return supplyAsync(() -> wrap(em -> details(em, id)), ce);
	}

	@Override
	public CompletionStage<List<CustomerModel>> getAllCustomer() {

		return supplyAsync(
				() -> wrap(em -> {
					TypedQuery<CustomerModel> query = em.createQuery(
							"SELECT m from CustomerModel m",
							CustomerModel.class);
					return query.getResultList();
				})
				, ce);
	}

	@Override
	public CompletionStage<Optional<Boolean>> deleteById(Long id) {
		return supplyAsync(() -> wrap(entityManager -> {
					CustomerModel model = entityManager.find(CustomerModel.class, id);
					if (model == null) {
						return Optional.empty();
					}
					entityManager.remove(model);
					return Optional.of(true);
				})
		);
	}

	@Override
	public CompletionStage<Integer> count() {
		return supplyAsync(() -> wrap(em -> {
			Query query = em.createNativeQuery("SELECT count(*) from Customers m where true = true");
			return ((Long) query.getSingleResult()).intValue();
		}));
	}

	private Optional<CustomerModel> details(EntityManager em, Long id) {
		try {
			TypedQuery<CustomerModel> query = em.createQuery("SELECT m FROM CustomerModel m " +
							"Where m.id = :id", CustomerModel.class)
					.setParameter("id", id);
			return Optional.ofNullable(query.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}


}
