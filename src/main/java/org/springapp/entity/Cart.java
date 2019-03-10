package org.springapp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.function.Function;

public class Cart implements Serializable {

    private static final long serialVersionUID = 6554623865768217431L;

    private Integer id;
    private Set<CartItem> cartItems;
    private BigDecimal grandTotal;

    public Cart(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getGrandTotal() {
        updateGrandTotal();
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public CartItem getItemByProductId(Integer productId) {
        return cartItems.stream().filter(cartItem -> cartItem.getProduct().getId().equals(productId))
                .findAny()
                .orElse(null);
    }

    public void updateGrandTotal() {

        Function<CartItem, BigDecimal> totalMapper = cartItem -> cartItem.getSubTotal();

        BigDecimal grandTotal = cartItems.stream()
                .map(totalMapper)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.setGrandTotal(grandTotal);
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
        Cart other = (Cart) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

