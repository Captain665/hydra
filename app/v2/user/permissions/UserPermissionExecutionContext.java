package v2.user.permissions;

import jakarta.inject.Inject;
import org.apache.pekko.actor.ActorSystem;
import play.api.libs.concurrent.CustomExecutionContext;

public class UserPermissionExecutionContext extends CustomExecutionContext {

	@Inject
	public UserPermissionExecutionContext(ActorSystem system) {
		super(system, "user.permissions");
	}
}
