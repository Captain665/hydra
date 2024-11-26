package v2.user.permissions;

import common.user.model.UserPermissionModel;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public interface RolePermissionsRepository {
    CompletionStage<Stream<UserPermissionModel>> getPermissions(String role);
}
