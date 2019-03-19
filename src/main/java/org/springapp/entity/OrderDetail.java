package org.springapp.entity;


import javax.persistence.*;

@Entity
@Table(name="order_details", schema = "springapp", catalog = "")
public class OrderDetail {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Order order;

    private Product product;

    private int quantity;


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

    @ManyToOne
    @JoinColumn(name = "orders_id", nullable = false,
                foreignKey = @ForeignKey(name = "order_items_orders_fk"))
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne
    @JoinColumn(name = "products_id", nullable = false,
                foreignKey = @ForeignKey(name = "order_items_products_fk"))
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
