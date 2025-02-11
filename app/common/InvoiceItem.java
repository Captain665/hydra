package common;

import common.order.model.OrderItemModel;

import java.math.BigDecimal;

public class InvoiceItem {

	public String itemName;
	public String hsnCode;
	public BigDecimal unitPrice;
	public Long qty;
	public BigDecimal taxableValue;
	public BigDecimal taxAmount;
	public BigDecimal itemTotal;

	public InvoiceItem() {
	}

	public InvoiceItem(OrderItemModel itemModel) {
		this.itemName = itemModel.getName();
		this.hsnCode = "1905";
		this.unitPrice = itemModel.getBasePrice();
		this.qty = itemModel.getQuantity();
		this.taxableValue = itemModel.getBasePrice().multiply(BigDecimal.valueOf(itemModel.getQuantity()));
		this.taxAmount = itemModel.getTax();
		this.itemTotal = itemModel.getSellingPrice().multiply(BigDecimal.valueOf(itemModel.getQuantity()));
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	public BigDecimal getTaxableValue() {
		return taxableValue;
	}

	public void setTaxableValue(BigDecimal taxableValue) {
		this.taxableValue = taxableValue;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(BigDecimal itemTotal) {
		this.itemTotal = itemTotal;
	}
}
