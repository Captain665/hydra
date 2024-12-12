package common.user.resources;

import common.user.model.UserModel;

import java.util.List;

public class UserResource {
	public Long id;
	public String mobile;
	public String fullName;
	public String emailId;
	public String role;
	public List<String> permissions;
	public boolean active;
	public String password;
	public String jwtToken;

	public UserResource(UserModel model) {
		this.id = model.getId();
		this.mobile = model.getMobile();
		this.fullName = model.getFull_name();
		this.emailId = model.email_id;
		this.role = model.getRole();
		this.active = model.active;
		this.password = model.getPassword();
		this.jwtToken = null;
	}

	public UserResource() {

	}

	public UserResource(Long id, String mobile, String fullName, String emailId, String role, List<String> permissions, boolean active, String jwtToken) {
		this.id = id;
		this.mobile = mobile;
		this.fullName = fullName;
		this.emailId = emailId;
		this.role = role;
		this.permissions = permissions;
		this.active = active;
		this.jwtToken = jwtToken;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	@Override
	public String toString() {
		return "UserResource{" +
				"id=" + id +
				", mobile='" + mobile + '\'' +
				", fullName='" + fullName + '\'' +
				", emailId='" + emailId + '\'' +
				", role='" + role + '\'' +
				", permissions=" + permissions +
				", active=" + active +
				", jwtToken='" + jwtToken + '\'' +
				'}';
	}
}
