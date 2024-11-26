package common.user.model;

import common.base.BaseModel;
import common.enums.PermissionType;
import jakarta.persistence.*;


@Entity
@Table(name = "role_permission")
public class UserPermissionModel extends BaseModel {
	@Enumerated(EnumType.STRING)
	private PermissionType permission;

	@Column(name = "role")
	private String role;

	public PermissionType getPermission() {
		return permission;
	}

	public UserPermissionModel(PermissionType permission) {
		this.permission = permission;
	}

	public UserPermissionModel() {
	}
}
