import com.google.inject.AbstractModule;
import v2.customer.CustomerRepository;
import v2.customer.JPACustomerRepository;
import v2.order.JPAOrderRepository;
import v2.order.OrderRepository;
import v2.user.JPAUserRepository;
import v2.user.UserRepository;
import v2.user.permissions.JPARolePermissionRepository;
import v2.user.permissions.RolePermissionsRepository;

public class Module extends AbstractModule {

	@Override
	protected void configure() {
		bind(CustomerRepository.class).to(JPACustomerRepository.class);
		bind(UserRepository.class).to(JPAUserRepository.class);
		bind(RolePermissionsRepository.class).to(JPARolePermissionRepository.class);
		bind(OrderRepository.class).to(JPAOrderRepository.class);
	}

}
