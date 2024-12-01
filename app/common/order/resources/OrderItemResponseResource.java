package common.order.resources;

import common.order.model.OrderItemModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OrderItemResponseResource {

	public Long id;
	public Long quantity;
	public String name;
	public String description;
	public BigDecimal basePrice;
	public BigDecimal tax;
	public BigDecimal sellingPrice;
	public Boolean isVeg;

	public OrderItemResponseResource() {
	}

	public static List<OrderItemResponseResource> mapOrderItem(List<OrderItemModel> orderItem) {
		return orderItem.stream().map(model -> {
			OrderItemResponseResource orderItemResponseResource = new OrderItemResponseResource();
			orderItemResponseResource.setId(model.getId());
			orderItemResponseResource.setName(model.getName());
			orderItemResponseResource.setDescription(model.getDescription());
			orderItemResponseResource.setBasePrice(model.getBasePrice());
			orderItemResponseResource.setQuantity(model.getQuantity());
			orderItemResponseResource.setTax(model.getTax());
			orderItemResponseResource.setSellingPrice(model.getSellingPrice());
			orderItemResponseResource.setVeg(model.getVeg());
			return orderItemResponseResource;
		}).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Boolean getVeg() {
		return isVeg;
	}

	public void setVeg(Boolean veg) {
		isVeg = veg;
	}
}
