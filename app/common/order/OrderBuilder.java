package common.order;

import common.customer.model.CustomerModel;
import common.enums.OrderStatus;
import common.enums.PaymentType;
import common.order.model.OrderItemModel;
import common.order.model.OrderModel;
import common.order.model.OrderOutletModel;

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
    public String deliveryCharge;
    public String orderFrom;
    public OrderStatus orderStatus;
    public List<OrderItemModel> orderItem;
    public OrderOutletModel orderOutlet;


    public OrderBuilder setTrainNo(String trainNo) {
        this.trainNo = trainNo;
        return this;
    }

    public OrderBuilder setTrainName(String trainName) {
        this.trainName = trainName;
        return this;
    }

    public OrderBuilder setStationCode(String stationCode) {
        this.stationCode = stationCode;
        return this;
    }

    public OrderBuilder setStationName(String stationName) {
        this.stationName = stationName;
        return this;
    }

    public OrderBuilder setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public OrderBuilder setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
        return this;
    }

    public OrderBuilder setCoach(String coach) {
        this.coach = coach;
        return this;
    }

    public OrderBuilder setBerth(String berth) {
        this.berth = berth;
        return this;
    }

    public OrderBuilder setCustomerId(CustomerModel customerId) {
        this.customerId = customerId;
        return this;
    }

    public OrderBuilder setPnr(String pnr) {
        this.pnr = pnr;
        return this;
    }

    public OrderBuilder setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public OrderBuilder setDeliveryCharge(String deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
        return this;
    }

    public OrderBuilder setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom;
        return this;
    }

    public OrderBuilder setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public OrderBuilder setOrderItem(List<OrderItemModel> orderItem) {
        this.orderItem = orderItem;
        return this;
    }

    public OrderBuilder setOrderOutlet(OrderOutletModel orderOutlet) {
        this.orderOutlet = orderOutlet;
        return this;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getCoach() {
        return coach;
    }

    public String getBerth() {
        return berth;
    }

    public CustomerModel getCustomerId() {
        return customerId;
    }

    public String getPnr() {
        return pnr;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public String getDeliveryCharge() {
        return deliveryCharge;
    }

    public String getOrderFrom() {
        return orderFrom;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<OrderItemModel> getOrderItem() {
        return orderItem;
    }

    public OrderOutletModel getOrderOutlet() {
        return orderOutlet;
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
                this.deliveryCharge,
                this.orderFrom,
                this.orderStatus,
                this.orderItem,
                this.orderOutlet
        );
    }
}
