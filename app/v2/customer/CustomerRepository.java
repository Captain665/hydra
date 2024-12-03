package v2.customer;

import common.customer.model.CustomerModel;
import common.customer.resources.CustomerResource;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public interface CustomerRepository {

	CompletionStage<CustomerModel> createOrGet(CustomerModel model);

	CompletionStage<CustomerModel> save(CustomerModel customerModel);

	CompletionStage<Optional<CustomerModel>> getById(Long id);

	CompletionStage<List<CustomerModel>> getAllCustomer();

	CompletionStage<Optional<Boolean>> deleteById(Long id);

	CompletionStage<Integer> count();

}
