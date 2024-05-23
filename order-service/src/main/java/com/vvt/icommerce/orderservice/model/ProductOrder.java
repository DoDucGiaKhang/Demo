package com.vvt.icommerce.orderservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class ProductOrder {

    @EmbeddedId
    @JsonIgnore
    private OrderProductPK pk;

    @Column(nullable = false)
    private Integer quantity;

    @Transient
    private Double totalPrice;

    public ProductOrder() {
        pk = new OrderProductPK();
    }

    public ProductOrder(Order order, Long productId, Integer quantity) {
        pk = new OrderProductPK();
        pk.setOrder(order);
        pk.setProductId(productId);
        this.quantity = quantity;
    }

    @Transient
    public Long getProductId() {
        return this.pk.getProductId();
    }

    @Transient
    public Double getTotalPrice() {
        return this.totalPrice == null ? 0.0 : this.totalPrice;
    }

    @Transient
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderProductPK getPk() {
        return pk;
    }

    public void setPk(OrderProductPK pk) {
        this.pk = pk;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProductOrder))
            return false;
        ProductOrder other = (ProductOrder) o;

        return this.pk == other.pk;
    }

    @Override
    public final int hashCode() {
        return pk.hashCode();
    }

}
