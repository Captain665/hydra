package v2.user;

import jakarta.inject.Inject;
import org.apache.pekko.actor.ActorSystem;
import play.api.libs.concurrent.CustomExecutionContext;

public class UserExecutionContext extends CustomExecutionContext {
    @Inject
    public UserExecutionContext(ActorSystem system) {
        super(system, "user.repository");
    }
}
