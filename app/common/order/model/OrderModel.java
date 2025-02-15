package common.order.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import common.base.BaseModel;
import common.customer.model.CustomerModel;
import common.enums.OrderStatus;
import common.enums.PaymentType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderModel extends BaseModel {
	@Column(name = "train_no")
	private String trainNo;
	@Column(name = "train_name")
	private String trainName;
	@Column(name = "station_code")
	private String stationCode;
	@Column(name = "station_name")
	private String stationName;
	@Column(name = "delivery_date")
	private String deliveryDate;
	@Column(name = "booking_date")
	private String bookingDate;
	@Column(name = "coach")
	private String coach;
	@Column(name = "berth")
	private String berth;
	@ManyToOne(targetEntity = CustomerModel.class, fetch = FetchType.EAGER, optional = false)
	private CustomerModel customer;
	@Column(name = "pnr")
	private String pnr;
	@Column(name = "payment_type")
	private PaymentType paymentType;
	@Column(name = "sub_total")
	private BigDecimal subTotal;
	@Column(name = "tax")
	private BigDecimal tax;
	@Column(name = "total_amount")
	private BigDecimal totalAmount;
	@Column(name = "delivery_charge")
	private BigDecimal deliveryCharge;
	@Column(name = "discount")
	private BigDecimal discount;
	@Column(name = "customer_payable")
	private BigDecimal customerPayable;
	@Column(name = "order_from")
	private String orderFrom;
	@Column(name = "order_status")
	private OrderStatus orderStatus;
	@OneToMany(targetEntity = OrderItemModel.class, fetch = FetchType.EAGER, mappedBy = "order")
	@JsonManagedReference
	private List<OrderItemModel> orderItem;
	@OneToOne(targetEntity = OrderOutletModel.class, fetch = FetchType.EAGER, mappedBy = "order")
	@JsonManagedReference
	private OrderOutletModel orderOutlet;


	public OrderModel() {
	}

	public OrderModel(String trainNo,
					  String trainName,
					  String stationCode,
					  String stationName,
					  String deliveryDate,
					  String bookingDate,
					  String coach,
					  String berth,
					  CustomerModel customer,
					  String pnr,
					  PaymentType paymentType,
					  BigDecimal subTotal,
					  BigDecimal tax,
					  BigDecimal totalAmount,
					  BigDecimal deliveryCharge,
					  BigDecimal discount,
					  BigDecimal customerPayable,
					  String orderFrom,
					  OrderStatus orderStatus,
					  List<OrderItemModel> orderItem,
					  OrderOutletModel orderOutlet,
					  String createdBy) {
		this.trainNo = trainNo;
		this.trainName = trainName;
		this.stationCode = stationCode;
		this.stationName = stationName;
		this.deliveryDate = deliveryDate;
		this.bookingDate = bookingDate;
		this.coach = coach;
		this.berth = berth;
		this.customer = customer;
		this.pnr = pnr;
		this.paymentType = paymentType;
		this.subTotal = subTotal;
		this.tax = tax;
		this.totalAmount = totalAmount;
		this.deliveryCharge = deliveryCharge;
		this.discount = discount;
		this.customerPayable = customerPayable;
		this.orderFrom = orderFrom;
		this.orderStatus = orderStatus;
		this.orderItem = orderItem;
		this.orderOutlet = orderOutlet;
		this.createdBy = createdBy;
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

	public List<OrderItemModel> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItemModel> orderItem) {
		this.orderItem = orderItem;
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public OrderOutletModel getOrderOutlet() {
		return orderOutlet;
	}

	public void setOrderOutlet(OrderOutletModel orderOutlet) {
		this.orderOutlet = orderOutlet;
	}

	@Override
	public String toString() {
		return "OrderModel{" +
				"trainNo='" + trainNo + '\'' +
				", trainName='" + trainName + '\'' +
				", stationCode='" + stationCode + '\'' +
				", stationName='" + stationName + '\'' +
				", deliveryDate='" + deliveryDate + '\'' +
				", bookingDate='" + bookingDate + '\'' +
				", coach='" + coach + '\'' +
				", berth='" + berth + '\'' +
				", customer=" + customer +
				", pnr='" + pnr + '\'' +
				", paymentType=" + paymentType +
				", deliveryCharge='" + deliveryCharge + '\'' +
				", orderFrom='" + orderFrom + '\'' +
				", orderStatus=" + orderStatus +
				", orderItem=" + orderItem +
				", orderOutlet=" + orderOutlet +
				", id=" + id +
				", createdAt=" + createdAt +
				", createdBy='" + createdBy + '\'' +
				", updatedAt=" + updatedAt +
				", updatedBy='" + updatedBy + '\'' +
				'}';
	}
}
