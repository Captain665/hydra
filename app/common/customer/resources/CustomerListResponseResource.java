package common.customer.resources;

import common.customer.model.CustomerModel;
import common.enums.Gender;

public class CustomerListResponseResource {
	public Long id;
	public String fullName;
	public String mobile;
	public String emailId;
	public Boolean active;
	public Gender gender;
	public Boolean newUser;

	public CustomerListResponseResource(CustomerModel model) {
		this.id = model.getId();
		this.fullName = model.getFullName();
		this.mobile = model.getEmailId();
		this.emailId = model.getEmailId();
		this.active = model.isActive();
		this.gender = model.getGender();
		this.newUser = model.getNewUser();
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

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Boolean getNewUser() {
		return newUser;
	}

	public void setNewUser(Boolean newUser) {
		this.newUser = newUser;
	}

	@Override
	public String toString() {
		return "CustomerListResponseResource{" +
				"id=" + id +
				", fullName='" + fullName + '\'' +
				", mobile='" + mobile + '\'' +
				", emailId='" + emailId + '\'' +
				", active=" + active +
				", gender=" + gender +
				", newUser=" + newUser +
				'}';
	}
}
