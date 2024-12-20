package v2.customer;

import jakarta.inject.Inject;
import org.apache.pekko.actor.ActorSystem;
import play.api.libs.concurrent.CustomExecutionContext;

public class CustomerExecutionContext extends CustomExecutionContext {
	@Inject
	public CustomerExecutionContext(ActorSystem system) {
		super(system, "customer.repository");
	}

}
