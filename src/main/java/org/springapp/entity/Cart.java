package org.springapp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.function.Function;

public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;

        Cart cart = (Cart) o;

        if (id != null ? !id.equals(cart.id) : cart.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

