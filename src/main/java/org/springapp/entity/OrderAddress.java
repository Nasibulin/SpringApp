package org.springapp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "order_addresses")
public class OrderAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private Order order;

    private Date createdAt;
    
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

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_addresses_seq")
    @SequenceGenerator(sequenceName = "order_addresses_seq", allocationSize = 1, name = "order_addresses_seq")
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "orders_id")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
