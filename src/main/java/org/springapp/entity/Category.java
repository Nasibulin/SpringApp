package org.springapp.entity;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "category", schema = "springapp", catalog = "")
public class Category {

    private int id;

    @Column(name = "parent_id")
    private int parentId;

    @Column(name = "catname")
    private String catname;

    @OneToMany(mappedBy = "category", cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<Product> productSet;

    public Category() {
    }


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

    public Category(String catname) {
        this.catname=catname;
    }
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

}
