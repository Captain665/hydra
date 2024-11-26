package v2.user;

import common.user.model.UserModel;
import common.user.resources.UserResource;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public interface UserRepository {

	CompletionStage<Optional<UserModel>> findByUserId(Long id);

	CompletionStage<Optional<List<UserModel>>> findByUserName(String mobile);

}
