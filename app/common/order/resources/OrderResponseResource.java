package common.order.resources;

import common.enums.OrderStatus;
import common.enums.PaymentType;
import common.order.model.OrderModel;

import java.math.BigDecimal;
import java.util.List;

import static common.order.resources.OrderItemResponseResource.mapOrderItem;

public class OrderResponseResource {
	public Long id;
	public String trainNo;
	public String trainName;
	public String stationCode;
	public String stationName;
	public String deliveryDate;
	public String bookingDate;
	public String coach;
	public String berth;
	public String pnr;
	public PaymentType paymentType;
	public BigDecimal subTotal;
	public BigDecimal tax;
	public BigDecimal totalAmount;
	public BigDecimal deliveryCharge;
	public BigDecimal discount;
	public BigDecimal customerPayable;
	public String orderFrom;
	public OrderStatus orderStatus;
	public List<OrderItemResponseResource> orderItem;
	public OrderOutletResponseResource orderOutlet;

	public OrderResponseResource() {
	}

	public OrderResponseResource(OrderModel model) {
		this.id = model.getId();
		this.trainName = model.getTrainName();
		this.trainNo = model.getTrainNo();
		this.stationCode = model.getStationCode();
		this.stationName = model.getStationName();
		this.bookingDate = model.getBookingDate();
		this.coach = model.getCoach();
		this.berth = model.getBerth();
		this.pnr = model.getPnr();
		this.paymentType = model.getPaymentType();
		this.orderFrom = model.getOrderFrom();
		this.orderStatus = model.getOrderStatus();
		this.subTotal = model.getSubTotal();
		this.tax = model.getTax();
		this.totalAmount = model.getTotalAmount();
		this.deliveryCharge = model.getDeliveryCharge();
		this.discount = model.getDiscount();
		this.customerPayable = model.getCustomerPayable();
		this.orderItem = mapOrderItem(model.getOrderItem());
		this.orderOutlet = new OrderOutletResponseResource(model.getOrderOutlet());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
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

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}

	public String getBerth() {
		return berth;
	}

	public void setBerth(String berth) {
		this.berth = berth;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getCustomerPayable() {
		return customerPayable;
	}

	public void setCustomerPayable(BigDecimal customerPayable) {
		this.customerPayable = customerPayable;
	}

	public BigDecimal getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(BigDecimal deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	public String getOrderFrom() {
		return orderFrom;
	}

	public void setOrderFrom(String orderFrom) {
		this.orderFrom = orderFrom;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<OrderItemResponseResource> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItemResponseResource> orderItem) {
		this.orderItem = orderItem;
	}

	public OrderOutletResponseResource getOrderOutlet() {
		return orderOutlet;
	}

	public void setOrderOutlet(OrderOutletResponseResource orderOutlet) {
		this.orderOutlet = orderOutlet;
	}
}
