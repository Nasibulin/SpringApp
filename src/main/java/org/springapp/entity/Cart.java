package org.springapp.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Function;

//@Component
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Set<CartItem> cartItems;
    private int quantity;
    private BigDecimal grandTotal = BigDecimal.ZERO;

    public Cart() {
        cartItems = new CopyOnWriteArraySet<CartItem>();
    }

    public Cart(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public int getQuantity() {
        updateQuantity();
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public void addCartItems(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }

    public CartItem getItemByProductId(Integer productId) {
        return cartItems.stream().filter(cartItem -> cartItem.getProduct().getId().equals(productId))
                .findAny()
                .orElse(null);
    }

    public void updateQuantity() {

        Function<CartItem, Integer> totalMapper = cartItem -> cartItem.getQuantity();

        Integer quantity = cartItems.stream()
                .map(totalMapper)
                .mapToInt(i->i).sum();

        this.setQuantity(quantity);
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

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", cartItems=" + cartItems +
                ", quantity=" + quantity +
                ", grandTotal=" + grandTotal +
                '}';
    }
}

