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


	public static List<OrderListResponseResource> mapOrderList(List<OrderModel> model) {
		return model.stream().map(orderResponse -> {
			OrderListResponseResource resource = new OrderListResponseResource();
			resource.setId(orderResponse.getId());
			resource.setCustomerPayable(orderResponse.getCustomerPayable());
			resource.setPaymentType(orderResponse.getPaymentType());
			resource.setBookingDate(orderResponse.getBookingDate());
			resource.setDeliveryDate(orderResponse.getDeliveryDate());
			resource.setStationCode(orderResponse.getStationCode());
			resource.setStationName(orderResponse.getStationName());
			resource.setStatus(orderResponse.getOrderStatus());
			resource.setOutletId(orderResponse.getOrderOutlet().getId());
			resource.setOutletName(orderResponse.getOrderOutlet().getName());
			resource.setOutletContact(orderResponse.getOrderOutlet().getContactNumber());
			resource.setCustomerId(orderResponse.getCustomer().getId());
			return resource;
		}).collect(Collectors.toList());
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
