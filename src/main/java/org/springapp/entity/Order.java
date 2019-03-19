package org.springapp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "id")
    private Integer id;
    @Column(name = "status")
    private int status;

    private int isActive;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "items_count")
    private int itemsCount;
    @Column(name = "items_quantity")
    private int itemsQuantity;
    @Column(name = "order_total")
    private BigDecimal orderTotal;
    @Column(name = "customer_is_guest")
    private int customerIsGuest;

    private User user;
    private Set<org.springapp.entity.OrderDetail> orderDetailsSet;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<org.springapp.entity.OrderDetail> getOrderDetailsSet() {
        return orderDetailsSet;
    }

    public void setOrderDetailsSet(Set<org.springapp.entity.OrderDetail> orderDetailsSet) {
        this.orderDetailsSet = orderDetailsSet;
    }

    @ManyToOne
    @JoinColumn(name = "users_user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_id_seq")
    @SequenceGenerator(sequenceName = "orders_id_seq", allocationSize = 1, name = "orders_id_seq")
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Column(name = "is_active")
    public int getActive() {
        return isActive;
    }

    public void setActive(int active) {
        isActive = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public int getItemsQuantity() {
        return itemsQuantity;
    }

    public void setItemsQuantity(int itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }


    public int getCustomerIsGuest() {
        return customerIsGuest;
    }

    public void setCustomerIsGuest(int customerIsGuest) {
        this.customerIsGuest = customerIsGuest;
    }

}
