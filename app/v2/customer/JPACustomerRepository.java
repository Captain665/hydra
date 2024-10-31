package v2.customer;import common.customer.model.CustomerModel;import common.customer.resources.CustomerResource;import jakarta.inject.Inject;import jakarta.inject.Singleton;import jakarta.persistence.EntityManager;import jakarta.persistence.NoResultException;import jakarta.persistence.TypedQuery;import play.Logger;import play.db.jpa.JPAApi;import java.util.Optional;import java.util.concurrent.CompletionStage;import java.util.function.Function;import static java.util.concurrent.CompletableFuture.supplyAsync;@Singletonpublic class JPACustomerRepository implements CustomerRepository {    private final JPAApi jpaApi;    private final CustomerExecutionContext ce;    private final Logger.ALogger logger = Logger.of("application.CustomerController.JPACustomerRepository");    @Inject    public JPACustomerRepository(JPAApi jpaApi, CustomerExecutionContext ce) {        this.jpaApi = jpaApi;        this.ce = ce;    }    @Override    public CompletionStage<CustomerModel> createOrGet(CustomerModel model) {        return jpaApi.withTransaction(                (Function<EntityManager, CompletionStage<CustomerModel>>)                        entityManager -> {                            entityManager.persist(model);                            entityManager.flush();                            return supplyAsync(() -> model);                        }        );    }    private <T> T wrap(Function<EntityManager, T> function) {        return jpaApi.withTransaction(function);    }    @Override    public CompletionStage<CustomerModel> save(CustomerModel customerModel) {        return null;    }    @Override    public CompletionStage<Optional<CustomerModel>> getById(Long id) {        return supplyAsync(() -> wrap(em -> details(em, id)));    }    private Optional<CustomerModel> details(EntityManager em, Long id) {        try {            TypedQuery<CustomerModel> query = em.createQuery("SELECT m FROM CustomerModel m " +                            "Where m.id = :id", CustomerModel.class)                    .setParameter("id", id);            return Optional.ofNullable(query.getSingleResult());        } catch (NoResultException e) {            return Optional.empty();        }    }}