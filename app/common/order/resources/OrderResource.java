package common.order.resources;

import common.customer.model.CustomerModel;
import common.enums.OrderStatus;
import common.enums.PaymentType;
import common.order.model.OrderItemModel;
import common.order.model.OrderModel;
import common.order.model.OrderOutletModel;

import java.math.BigDecimal;
import java.util.List;

public class
OrderResource {
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
	public BigDecimal deliveryCharge;
	public BigDecimal discount;
	public String orderFrom;
	public OrderStatus orderStatus;
	public List<OrderItemModel> orderItem;
	public OrderOutletModel orderOutlet;


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

	public List<OrderItemModel> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItemModel> orderItem) {
		this.orderItem = orderItem;
	}

	public OrderOutletModel getOrderOutlet() {
		return orderOutlet;
	}

	public void setOrderOutlet(OrderOutletModel orderOutlet) {
		this.orderOutlet = orderOutlet;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public OrderResource() {
	}

	public OrderResource(OrderModel model) {
		this.id = model.getId();
		this.trainNo = model.getTrainNo();
		this.trainName = model.getTrainName();
		this.stationCode = model.getStationCode();
		this.stationName = model.getStationName();
		this.deliveryDate = model.getDeliveryDate();
		this.bookingDate = model.getBookingDate();
		this.coach = model.getCoach();
		this.berth = model.getBerth();
		this.pnr = model.getPnr();
		this.paymentType = model.getPaymentType();
		this.deliveryCharge = model.getDeliveryCharge();
		this.discount = model.getDiscount();
		this.orderFrom = model.getOrderFrom();
		this.orderStatus = model.getOrderStatus();
		this.orderItem = model.getOrderItem();
		this.orderOutlet = model.getOrderOutlet();
	}


	@Override
	public String toString() {
		return "OrderResource{" +
				"id=" + id +
				", trainNo='" + trainNo + '\'' +
				", trainName='" + trainName + '\'' +
				", stationCode='" + stationCode + '\'' +
				", stationName='" + stationName + '\'' +
				", deliveryDate='" + deliveryDate + '\'' +
				", bookingDate='" + bookingDate + '\'' +
				", coach='" + coach + '\'' +
				", berth='" + berth + '\'' +
				", pnr='" + pnr + '\'' +
				", paymentType=" + paymentType +
				", deliveryCharge='" + deliveryCharge + '\'' +
				", orderFrom='" + orderFrom + '\'' +
				", orderStatus=" + orderStatus +
				", orderItem=" + orderItem +
				", orderOutlet=" + orderOutlet +
				'}';
	}
}
