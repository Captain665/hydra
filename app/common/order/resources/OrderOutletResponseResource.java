package common.order.resources;

import common.order.model.OrderOutletModel;

import java.security.PublicKey;

public class OrderOutletResponseResource {
	public Long id;
	public String name;
	public String address;
	public String city;
	public String state;
	public String pinCode;
	public String fssaiNo;
	public String contact;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getFssaiNo() {
		return fssaiNo;
	}

	public void setFssaiNo(String fssaiNo) {
		this.fssaiNo = fssaiNo;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public OrderOutletResponseResource() {
	}

	public OrderOutletResponseResource(OrderOutletModel model) {
		this.id = model.getId();
		this.name = model.getName();
		this.address = model.getAddress();
		this.city = model.getCity();
		this.state = model.getState();
		this.pinCode = model.getPinCode();
		this.fssaiNo = model.getFssaiNumber();
		this.contact = model.getContactNumber();
	}
}
