package v2.user.permissions;

import common.user.model.UserPermissionModel;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import play.Logger;
import play.db.jpa.JPAApi;


import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Singleton
public class JPARolePermissionRepository implements RolePermissionsRepository {

	private final JPAApi jpaApi;
	private final UserPermissionExecutionContext ec;
	final Logger.ALogger logger = Logger.of("application.JPARolePermissionRepository");

	@Inject
	public JPARolePermissionRepository(JPAApi jpaApi, UserPermissionExecutionContext ec) {
		this.jpaApi = jpaApi;
		this.ec = ec;
	}


	@Override
	public CompletionStage<Stream<UserPermissionModel>> getPermissions(String role) {
		return supplyAsync(() -> wrap(em -> getAllPermission(em, role)), ec);
	}

	private <T> T wrap(Function<EntityManager, T> function) {
		return jpaApi.withTransaction(function);
	}

	private Stream<UserPermissionModel> getAllPermission(EntityManager em, String role) {
		TypedQuery<UserPermissionModel> query = em.createQuery(
						"SELECT m FROM UserPermissionModel m " +
								"where role = :role", UserPermissionModel.class)
				.setParameter("role", role);
		return query.getResultList().stream();
	}
}
