package org.springapp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "products", schema = "springapp", catalog = "")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    @Column(name = "part_number")
    private String partNumber;
    @Column(name = "description")
    private String description;
    @Column(name = "service")
    private String service;
    @Column(name = "price")
    private BigDecimal price;
    private Category category;

    public Product() {
    }

    public Product(String partNumber, String description, String service, BigDecimal price, Category category) {
        this.partNumber = partNumber;
        this.description = description;
        this.service = service;
        this.price = price;
        this.category = category;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_id_seq")
    @SequenceGenerator(sequenceName = "products_id_seq", allocationSize = 1, name = "products_id_seq")
    @Column(name = "id")
    public Integer getId() {
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", partNumber='" + partNumber + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (partNumber != null ? !partNumber.equals(product.partNumber) : product.partNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (partNumber != null ? partNumber.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
