package v2.user;

import common.user.model.UserModel;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import play.Logger;
import play.db.jpa.JPAApi;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Singleton
public class JPAUserRepository implements UserRepository {

    private final JPAApi jpaApi;
    private final UserExecutionContext ec;
    private final Logger.ALogger logger = Logger.of("application.UserController.JPAUserRepository");

    @Inject
    public JPAUserRepository(JPAApi jpaApi, UserExecutionContext ec) {
        this.jpaApi = jpaApi;
        this.ec = ec;
    }

    @Override
    public CompletionStage<Optional<UserModel>> findByUserId(Long id) {
        return supplyAsync(() -> wrap(em -> lookup(em, id)), ec);
    }

    @Override
    public CompletionStage<Optional<List<UserModel>>> findByUserName(String mobile) {
        return supplyAsync(() -> wrap(em -> lookup(em, mobile)), ec);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private <T> T wrapReadOnly(Function<EntityManager, T> function) {
        return jpaApi.withTransaction("read", true, function);
    }

    private Optional<List<UserModel>> lookup(EntityManager em, String mobile) {
        TypedQuery<UserModel> query = em.createQuery(
                        "SELECT m from UserModel m where " +
                                "m.mobile = :mobile", UserModel.class)
                .setParameter("mobile", mobile);
        return Optional.ofNullable(query.getResultList());
    }

    private Optional<UserModel> lookup(EntityManager em, Long id) {
        return Optional.ofNullable(em.find(UserModel.class, id));
    }

}
