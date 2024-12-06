package common.order.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mysql.cj.xdevapi.JsonString;
import common.base.BaseModel;
import jakarta.persistence.*;
import org.hibernate.annotations.TypeDef;

import java.util.Date;

//@TypeDef(name = "json", typeClass = JsonString.class)
@Entity
@Table(name = "order_outlets")
public class OrderOutletModel extends BaseModel {
	@Column(name = "name")
	private String name;
	@Column(name = "address")
	private String address;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "fssai_number")
	private String fssaiNumber;
	@Column(name = "fssai_cut_off_date")
	private Date fssaiCutOffDate;
	@Column(name = "gst_number")
	private String gstNumber;
	@Column(name = "contact_number")
	private String contactNumber;
	@Column(name = "pin_code")
	private String pinCode;
	@OneToOne
	@JoinColumn(name = "order_id")
	@JsonBackReference
	private OrderModel order;


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

	public String getFssaiNumber() {
		return fssaiNumber;
	}

	public void setFssaiNumber(String fssaiNumber) {
		this.fssaiNumber = fssaiNumber;
	}

	public Date getFssaiCutOffDate() {
		return fssaiCutOffDate;
	}

	public void setFssaiCutOffDate(Date fssaiCutOffDate) {
		this.fssaiCutOffDate = fssaiCutOffDate;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public OrderModel getOrder() {
		return order;
	}

	public void setOrder(OrderModel order) {
		this.order = order;
	}


	public OrderOutletModel() {
	}

	public OrderOutletModel(String name,
							String address,
							String city,
							String state,
							String fssaiNumber,
							Date fssaiCutOffDate,
							String gstNumber,
							String contactNumber,
							String pinCode) {
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.fssaiNumber = fssaiNumber;
		this.fssaiCutOffDate = fssaiCutOffDate;
		this.gstNumber = gstNumber;
		this.contactNumber = contactNumber;
		this.pinCode = pinCode;
	}

	@Override
	public String toString() {
		return "OrderOutletModel{" +
				"name='" + name + '\'' +
				", address='" + address + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				", fssaiNumber='" + fssaiNumber + '\'' +
				", fssaiCutOffDate=" + fssaiCutOffDate +
				", gstNumber='" + gstNumber + '\'' +
				", contactNumber='" + contactNumber + '\'' +
				", pinCode='" + pinCode + '\'' +
				'}';
	}
}
