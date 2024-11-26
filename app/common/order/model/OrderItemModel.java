package common.order.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mysql.cj.xdevapi.JsonString;
import common.base.BaseModel;
import jakarta.persistence.*;
import org.hibernate.annotations.TypeDef;

import java.math.BigDecimal;

//@TypeDef(name = "json", typeClass = JsonString.class)
@Entity
@Table(name = "order_items")
public class OrderItemModel extends BaseModel {
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "is_veg")
    private Boolean isVeg;
    @Column(name = "base_price")
    private BigDecimal basePrice;
    @Column(name = "tax")
    private BigDecimal tax;
    @Column(name = "selling_price")
    private BigDecimal sellingPrice;
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private OrderModel order;

    public OrderItemModel() {
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

    public Boolean getVeg() {
        return isVeg;
    }

    public void setVeg(Boolean veg) {
        isVeg = veg;
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

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public OrderItemModel(Long quantity,
                          String name,
                          String description,
                          Boolean isVeg,
                          BigDecimal basePrice,
                          BigDecimal tax,
                          BigDecimal sellingPrice) {
        this.quantity = quantity;
        this.name = name;
        this.description = description;
        this.isVeg = isVeg;
        this.basePrice = basePrice;
        this.tax = tax;
        this.sellingPrice = sellingPrice;
    }

}
