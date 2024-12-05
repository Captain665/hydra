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
	public Boolean veg;

	public OrderItemResponseResource() {
	}

	public OrderItemResponseResource(OrderItemModel model) {
		this.id = model.getId();
		this.quantity = model.getQuantity();
		this.name = model.getName();
		this.description = model.getDescription();
		this.basePrice = model.getBasePrice();
		this.tax = model.getTax();
		this.sellingPrice = model.getSellingPrice();
		this.veg = model.getVeg();
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
		return veg;
	}

	public void setVeg(Boolean veg) {
		this.veg = veg;
	}

	@Override
	public String toString() {
		return "OrderItemResponseResource{" +
				"id=" + id +
				", quantity=" + quantity +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", basePrice=" + basePrice +
				", tax=" + tax +
				", sellingPrice=" + sellingPrice +
				", veg=" + veg +
				'}';
	}
}
