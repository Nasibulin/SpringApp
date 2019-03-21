package org.springapp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "order_addresses")
public class OrderAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_addresses_seq")
    @SequenceGenerator(sequenceName = "order_addresses_seq", allocationSize = 1, name = "order_addresses_seq")
    @Column(name = "id")
    private Integer id;
    
    private Order order;

    @Column(name = "orders_id")
    private Integer orderId;
    
    @Column(name = "address_id")
    private Integer addressId;
    
    @Column(name = "created_at")
    private Date createdAt;
    
    @Column(name = "updated_at")
    private Date updatedAt;
    
    @Column(name = "region_id")
    private Integer regionId;
    
    @Column(name = "region")
    private String region;
    
    @Column(name = "postcode")
    private String postcode;
    
    @Column(name = "prefix")
    private String prefix;
    
    @Column(name = "suffix")
    private String suffix;
    
}
