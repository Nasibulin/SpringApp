package org.springapp.entity;

import java.io.Serializable;
import java.math.BigDecimal;

//@Component
//@Scope(value= WebApplicationContext.SCOPE_SESSION, proxyMode= ScopedProxyMode.TARGET_CLASS)
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Product product;
    private int quantity;
    private BigDecimal subTotal = BigDecimal.ZERO;

    public CartItem() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        this.updateSubTotal();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubTotal() {
        this.updateSubTotal();
        return subTotal;
    }

    public void updateSubTotal() {
        this.subTotal = this.product.getPrice().multiply(new BigDecimal(this.quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem)) return false;

        CartItem cartItem = (CartItem) o;

        if (id != null ? !id.equals(cartItem.id) : cartItem.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", subTotal=" + subTotal +
                '}';
    }
}

