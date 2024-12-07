package common.order;

import common.customer.model.CustomerModel;
import common.enums.OrderStatus;
import common.enums.PaymentType;
import common.order.model.OrderItemModel;
import common.order.model.OrderModel;
import common.order.model.OrderOutletModel;

import java.math.BigDecimal;
import java.util.List;

public class OrderBuilder {
	public String trainNo;
	public String trainName;
	public String stationCode;
	public String stationName;
	public String deliveryDate;
	public String bookingDate;
	public String coach;
	public String berth;
	public CustomerModel customerId;
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
	public List<OrderItemModel> orderItem;
	public OrderOutletModel orderOutlet;
	public String createdBy;

	public String getTrainNo() {
		return trainNo;
	}

	public OrderBuilder setTrainNo(String trainNo) {
		this.trainNo = trainNo;
		return this;
	}

	public String getTrainName() {
		return trainName;
	}

	public OrderBuilder setTrainName(String trainName) {
		this.trainName = trainName;
		return this;
	}

	public String getStationCode() {
		return stationCode;
	}

	public OrderBuilder setStationCode(String stationCode) {
		this.stationCode = stationCode;
		return this;
	}

	public String getStationName() {
		return stationName;
	}

	public OrderBuilder setStationName(String stationName) {
		this.stationName = stationName;
		return this;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public OrderBuilder setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
		return this;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public OrderBuilder setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
		return this;
	}

	public String getCoach() {
		return coach;
	}

	public OrderBuilder setCoach(String coach) {
		this.coach = coach;
		return this;
	}

	public String getBerth() {
		return berth;
	}

	public OrderBuilder setBerth(String berth) {
		this.berth = berth;
		return this;
	}

	public CustomerModel getCustomerId() {
		return customerId;
	}

	public OrderBuilder setCustomerId(CustomerModel customerId) {
		this.customerId = customerId;
		return this;
	}

	public String getPnr() {
		return pnr;
	}

	public OrderBuilder setPnr(String pnr) {
		this.pnr = pnr;
		return this;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public OrderBuilder setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
		return this;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public OrderBuilder setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
		return this;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public OrderBuilder setTax(BigDecimal tax) {
		this.tax = tax;
		return this;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public OrderBuilder setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
		return this;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public OrderBuilder setDiscount(BigDecimal discount) {
		this.discount = discount;
		return this;
	}

	public BigDecimal getCustomerPayable() {
		return customerPayable;
	}

	public OrderBuilder setCustomerPayable(BigDecimal customerPayable) {
		this.customerPayable = customerPayable;
		return this;
	}

	public BigDecimal getDeliveryCharge() {
		return deliveryCharge;
	}

	public OrderBuilder setDeliveryCharge(BigDecimal deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
		return this;
	}

	public String getOrderFrom() {
		return orderFrom;
	}

	public OrderBuilder setOrderFrom(String orderFrom) {
		this.orderFrom = orderFrom;
		return this;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public OrderBuilder setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
		return this;
	}

	public List<OrderItemModel> getOrderItem() {
		return orderItem;
	}

	public OrderBuilder setOrderItem(List<OrderItemModel> orderItem) {
		this.orderItem = orderItem;
		return this;
	}

	public OrderOutletModel getOrderOutlet() {
		return orderOutlet;
	}

	public OrderBuilder setOrderOutlet(OrderOutletModel orderOutlet) {
		this.orderOutlet = orderOutlet;
		return this;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public OrderBuilder setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
		return this;
	}

	public OrderModel build() {
		return new OrderModel(
				this.trainName,
				this.trainNo,
				this.stationCode,
				this.stationName,
				this.deliveryDate,
				this.bookingDate,
				this.coach,
				this.berth,
				this.customerId,
				this.pnr,
				this.paymentType,
				this.subTotal,
				this.tax,
				this.totalAmount,
				this.deliveryCharge,
				this.discount,
				this.customerPayable,
				this.orderFrom,
				this.orderStatus,
				this.orderItem,
				this.orderOutlet,
				this.createdBy
		);
	}
}
