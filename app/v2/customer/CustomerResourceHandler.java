package v2.customer;

import common.customer.CustomerBuilder;
import common.customer.model.CustomerModel;
import common.customer.resources.CustomerResource;
import jakarta.inject.Inject;
import org.mindrot.jbcrypt.BCrypt;
import play.Logger;

import javax.xml.transform.Result;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class CustomerResourceHandler {

	private final CustomerRepository repository;
	private final Logger.ALogger logger = Logger.of("application.CustomerController.CustomerResourceHandler");

	@Inject
	public CustomerResourceHandler(CustomerRepository repository) {
		this.repository = repository;
	}


	public CompletionStage<CustomerResource> create(CustomerResource resource) {
		CustomerModel model = new CustomerBuilder()
				.setFullName(resource.fullName)
				.setMobile(resource.mobile)
				.setEmailId(resource.emailId)
				.setPassword(BCrypt.hashpw(resource.password, BCrypt.gensalt()))
				.setGender(resource.getGender())
				.build();
		return repository.createOrGet(model).thenComposeAsync(
				customer -> {
					CustomerResource customerResource = new CustomerResource(customer);
					return supplyAsync(() -> customerResource);
				}
		);
	}

	public CompletionStage<Optional<CustomerResource>> getCustomerDetails(Long id) {
		return repository.getById(id).thenApplyAsync(
				details ->
						details.map(CustomerResource::new)
		);
	}

	public CompletionStage<List<CustomerResource>> getAllCustomerList() {
		return repository.getAllCustomer().thenApplyAsync(
				customerModel ->
						customerModel.stream().map(CustomerResource::new)
								.collect(Collectors.toList()));
	}

	public CompletionStage<Optional<Boolean>> deleteById(Long id) {
		return repository.deleteById(id);

	}

}
