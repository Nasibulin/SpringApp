package org.springapp.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category", schema = "springapp", catalog = "")
public class Category {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "parent_id")
    private int parentId;

    @Column(name = "category")
    private String category;

    private Set<Product> productSet = new HashSet<Product>();

    public Category() {
    }

    @OneToMany(mappedBy = "product", cascade=CascadeType.ALL, orphanRemoval=true)
    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    public void addProductSet (Product product){
    product.setCategory(this);
    getProductSet().add(product);
    }

    public Category(String category) {
        this.category=category;
    }

    public int getId() {
        return id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
