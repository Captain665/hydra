package v2.order;

import jakarta.inject.Inject;
import org.apache.pekko.actor.ActorSystem;
import play.api.libs.concurrent.CustomExecutionContext;

public class OrderExecutionContext extends CustomExecutionContext {
	@Inject
	public OrderExecutionContext(ActorSystem system) {
		super(system, "order.repository");
	}
}
