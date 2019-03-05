package org.springapp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "categories", schema = "springapp", catalog = "")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;

    @Column(name = "parent_id")
    private int parentId;

    @Column(name = "catname")
    private String catname;


    private Set<Product> products;

    public Category() {
    }

    @OneToMany(mappedBy = "category", cascade=CascadeType.ALL, orphanRemoval=true)
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProduct (Product product){
    product.setCategory(this);
    getProducts().add(product);
    }

    public Category(String catname) {
        this.catname=catname;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_id_seq")
    @SequenceGenerator(sequenceName = "categories_id_seq", allocationSize = 1, name = "categories_id_seq")
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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", catname='" + catname + '\'' +
                '}';
    }

}
