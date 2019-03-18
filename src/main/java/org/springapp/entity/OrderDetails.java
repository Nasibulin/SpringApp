package org.springapp.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="order_details", schema = "springapp", catalog = "")
public class OrderDetails {

    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    private Integer id;
    private Set<CartItem> cartItemSet;
    private Order order;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_details_id_seq")
    @SequenceGenerator(sequenceName = "order_details_id_seq", allocationSize = 1, name = "order_details_id_seq")
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<CartItem> getCartItemSet() {
        return cartItemSet;
    }

    public void setCartItemSet(Set<CartItem> cartItemSet) {
        this.cartItemSet = cartItemSet;
    }

    @ManyToOne
    @JoinColumn(name = "orders_id")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
