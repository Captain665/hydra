package common.user.model;

import common.base.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserModel extends BaseModel {
	@Column(name = "role")
	public String role;
	@Column(name = "full_name")
	public String full_name;
	@Column(name = "email_id")
	public String email_id;
	@Column(name = "mobile")
	public String mobile;
	@Column(name = "password")
	public String password;
	@Column(name = "active")
	public boolean active;


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public UserModel(String role, String full_name, String email_id, String mobile, String password, boolean active) {
		this.role = role;
		this.full_name = full_name;
		this.email_id = email_id;
		this.mobile = mobile;
		this.password = password;
		this.active = active;
	}

	public UserModel() {

	}

	@Override
	public String toString() {
		return "UserModel{" +
				"role='" + role + '\'' +
				", full_name='" + full_name + '\'' +
				", email_id='" + email_id + '\'' +
				", mobile='" + mobile + '\'' +
				", password='" + password + '\'' +
				", active=" + active +
				", id=" + id +
				", createdAt=" + createdAt +
				", createdBy=" + createdBy +
				", updatedAt=" + updatedAt +
				", updatedBy=" + updatedBy +
				'}';
	}
}
