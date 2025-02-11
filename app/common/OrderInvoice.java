package common;

import common.order.model.OrderModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OrderInvoice {
	public String invoiceNo;
	public String paymentType;
	public String invoiceDate;
	public String orderId;
	public String customerName;
	public String seatNo;
	public String coachNo;
	public String supplierName;
	public String supplierAddress;
	public String trainNo;
	public String place;
	public String state;
	public String deliveryAddress;
	public List<InvoiceItem> items;
	public BigDecimal totalAmount;
	public String amountWords;
	public BigDecimal subtotal;
	public BigDecimal cgst;
	public BigDecimal sgst;

	public OrderInvoice(OrderModel model) {
		this.invoiceNo = "IN24-25/" + model.getId();
		this.paymentType = model.getPaymentType().toString();
		this.invoiceDate = model.getDeliveryDate();
		this.orderId = model.getId().toString();
		this.customerName = model.getCustomer().getFullName();
		this.seatNo = model.getBerth();
		this.coachNo = model.getCoach();
		this.supplierName = model.getOrderOutlet().getName();
		this.supplierAddress = model.getOrderOutlet().getAddress();
		this.trainNo = model.getTrainNo();
		this.place = model.getStationName() + " (" + model.getStationCode() + ")";
		this.state = model.getOrderOutlet().getState();
		this.deliveryAddress = model.getStationName() + " (" + model.getStationCode() + ")";
		this.items = model.getOrderItem().stream().map(InvoiceItem::new).collect(Collectors.toList());
		this.totalAmount = model.getCustomerPayable();
		this.amountWords = amountWords;
		this.subtotal = model.getSubTotal();
		this.cgst = model.getTax().divide(BigDecimal.valueOf(2));
		this.sgst = model.getTax().divide(BigDecimal.valueOf(2));
	}


	public OrderInvoice() {
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getCoachNo() {
		return coachNo;
	}

	public void setCoachNo(String coachNo) {
		this.coachNo = coachNo;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public List<InvoiceItem> getItems() {
		return items;
	}

	public void setItems(List<InvoiceItem> items) {
		this.items = items;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getAmountWords() {
		return amountWords;
	}

	public void setAmountWords(String amountWords) {
		this.amountWords = amountWords;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getCgst() {
		return cgst;
	}

	public void setCgst(BigDecimal cgst) {
		this.cgst = cgst;
	}

	public BigDecimal getSgst() {
		return sgst;
	}

	public void setSgst(BigDecimal sgst) {
		this.sgst = sgst;
	}
}

