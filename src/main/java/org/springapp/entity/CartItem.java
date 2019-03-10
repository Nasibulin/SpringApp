package org.springapp.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Product product;
    private int quantity;
    private BigDecimal subTotal;

    public CartItem(Integer id) {
        super();
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
        subTotal = this.product.getPrice().multiply(new BigDecimal(this.quantity));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CartItem other = (CartItem) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

