package common.customer.resources;

import common.customer.model.CustomerModel;
import common.enums.Gender;

import javax.validation.constraints.*;

public class CustomerResource {
	public Long id;
	@NotBlank(message = "customer name cannot be blank")
	@Pattern(regexp = "([A-Z a-z])", message = "regex failed")
	public String fullName;
	@NotBlank(message = "customer mobile cannot be blank")
	@Size(min = 10, max = 10, message = "mobile number should be 10 digits")
	@Pattern(regexp = "([0-9]{10})", message = "regex failed")
	public String mobile;
	@NotBlank(message = "test")
	@Email(message = "format issue")
	public String emailId;
	public boolean active;
	public Gender gender;
	@NotBlank(message = "test ")
	@Size(min = 8, max = 20, message = "regex failed")
	public String password;
	public String jwt;
	public Boolean newUser;

	public CustomerResource(CustomerModel customer) {
		this.id = customer.getId();
		this.fullName = customer.getFullName();
		this.mobile = customer.getMobile();
		this.emailId = customer.getEmailId();
		this.active = customer.isActive();
		this.gender = customer.getGender();
		this.jwt = null;
		this.newUser = customer.getNewUser();
	}

	public CustomerResource(Long id, String fullName, String mobile, String emailId, boolean active, Gender gender, String jwt) {
		this.id = id;
		this.fullName = fullName;
		this.mobile = mobile;
		this.emailId = emailId;
		this.active = active;
		this.gender = gender;
		this.jwt = jwt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public Boolean getNewUser() {
		return newUser;
	}

	public void setNewUser(Boolean newUser) {
		this.newUser = newUser;
	}

	@Override
	public String toString() {
		return "CustomerResource{" +
				"id=" + id +
				", fullName='" + fullName + '\'' +
				", mobile='" + mobile + '\'' +
				", emailId='" + emailId + '\'' +
				", active=" + active +
				", gender=" + gender +
				", jwt='" + jwt + '\'' +
				'}';
	}
}
