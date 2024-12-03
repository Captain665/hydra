package common.order.resources;

import common.enums.OrderStatus;
import common.enums.PaymentType;
import common.order.model.OrderModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OrderListResponseResource {

	public Long id;
	public BigDecimal customerPayable;
	public PaymentType paymentType;
	public String bookingDate;
	public String deliveryDate;
	public String stationCode;
	public String stationName;
	public OrderStatus status;
	public Long outletId;
	public String outletName;
	public String outletContact;
	public Long customerId;

	public OrderListResponseResource() {
	}

	public OrderListResponseResource(OrderModel model) {
		this.setId(model.getId());
		this.setCustomerPayable(model.getCustomerPayable());
		this.setPaymentType(model.getPaymentType());
		this.setBookingDate(model.getBookingDate());
		this.setDeliveryDate(model.getDeliveryDate());
		this.setStationCode(model.getStationCode());
		this.setStationName(model.getStationName());
		this.setStatus(model.getOrderStatus());
		this.setOutletId(model.getOrderOutlet().getId());
		this.setOutletName(model.getOrderOutlet().getName());
		this.setOutletContact(model.getOrderOutlet().getContactNumber());
		this.setCustomerId(model.getCustomer().getId());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCustomerPayable() {
		return customerPayable;
	}

	public void setCustomerPayable(BigDecimal customerPayable) {
		this.customerPayable = customerPayable;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Long getOutletId() {
		return outletId;
	}

	public void setOutletId(Long outletId) {
		this.outletId = outletId;
	}

	public String getOutletName() {
		return outletName;
	}

	public void setOutletName(String outletName) {
		this.outletName = outletName;
	}

	public String getOutletContact() {
		return outletContact;
	}

	public void setOutletContact(String outletContact) {
		this.outletContact = outletContact;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	@Override
	public String toString() {
		return "OrderListResponseResource{" +
				"id=" + id +
				", customerPayable=" + customerPayable +
				", paymentType=" + paymentType +
				", bookingDate='" + bookingDate + '\'' +
				", deliveryDate='" + deliveryDate + '\'' +
				", stationCode='" + stationCode + '\'' +
				", stationName='" + stationName + '\'' +
				", status=" + status +
				", outletId=" + outletId +
				", outletName='" + outletName + '\'' +
				", outletContact='" + outletContact + '\'' +
				", customerId=" + customerId +
				'}';
	}
}
