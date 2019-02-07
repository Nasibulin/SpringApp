package org.springapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "category", schema = "springapp", catalog = "")
public class Product {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "parent_id")
    private int parentId;

    @Column(name = "category")
    private String category;

    public Product() {
    }

    public Product(String category) {
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
