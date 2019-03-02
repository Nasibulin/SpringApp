package org.springapp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "id")
    private Integer id;
    @Column(name = "status")
    private Integer status;
    @Column(name = "is_active")
    private Integer isActive;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "items_count")
    private Integer itemsCount;
    @Column(name = "items_quantity")
    private Integer itemsQuantity;
    @Column(name = "order_total")
    private BigDecimal orderTotal;
    @Column(name = "customer_is_guest")
    private Integer customerIsGuest;

    private User user;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getActive() {
        return isActive;
    }

    public void setActive(Integer active) {
        isActive = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(Integer itemsCount) {
        this.itemsCount = itemsCount;
    }

    public Integer getItemsQuantity() {
        return itemsQuantity;
    }

    public void setItemsQuantity(Integer itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }


    public Integer getCustomerIsGuest() {
        return customerIsGuest;
    }

    public void setCustomerIsGuest(Integer customerIsGuest) {
        this.customerIsGuest = customerIsGuest;
    }



}
