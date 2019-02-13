package org.springapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "product", schema = "springapp", catalog = "")
public class Product {

    private int id;

    @Column(name = "part_number")
    private String partNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "service")
    private String service;

    @Column(name = "price")
    private double price;

    private Category category;

    public Product() {
    }

    public Product(String partNumber, String description, String service, double price) {
        this.partNumber = partNumber;
        this.description = description;
        this.service = service;
        this.price = price;
    }
    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(sequenceName = "product_seq", allocationSize = 1, name = "product_seq")
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

}
