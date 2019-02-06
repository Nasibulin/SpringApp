package org.springapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "category", schema = "db", catalog = "")
public class Category {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "parent_id")
    private int parentId;

    @Column(name = "category")
    private String category;

    public Category() {
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
