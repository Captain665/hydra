package v2.user;

import common.user.model.UserModel;
import common.user.resources.UserResource;
import jakarta.inject.Inject;
import org.mindrot.jbcrypt.BCrypt;
import play.Logger;
import v2.user.permissions.RolePermissionsRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class UserResourceHandler {
	private final Logger.ALogger logger = Logger.of("application.UserResourceHandler");
	private final UserRepository repository;
	private final RolePermissionsRepository permissionsRepository;

	@Inject
	public UserResourceHandler(UserRepository repository, RolePermissionsRepository permissionsRepository) {
		this.repository = repository;
		this.permissionsRepository = permissionsRepository;
	}

	public CompletionStage<Optional<UserResource>> findByUserNamePassword(String mobile, String password) {
		return repository.findByUserName(mobile).thenComposeAsync(
				optional -> {
					if (optional.isPresent()) {
						List<UserModel> modelList = optional.get();
						UserModel model = modelList.stream().filter(user ->
										BCrypt.checkpw(password, user.getPassword())).findFirst()
								.orElse(null);
						if (Optional.ofNullable(model).isEmpty()) {
							return supplyAsync(Optional::empty);
						}
						return permissionsRepository.getPermissions(model.getRole()).thenComposeAsync(
								rolePermissionModelStream -> {
									List<String> permissions = rolePermissionModelStream.map(
											rolePermissionModel -> rolePermissionModel.getPermission().toString()
									).collect(Collectors.toList());
									UserResource resource = new UserResource(model);
									resource.setPermissions(permissions);
									return supplyAsync(() -> Optional.of(resource));
								}
						);
					}
					return supplyAsync(Optional::empty);
				}
		);
	}

}
