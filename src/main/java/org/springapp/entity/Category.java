package org.springapp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "category", schema = "springapp", catalog = "")
//@NamedQuery(name = Category.NamedQuery_findCatnameById,
//        query = "SELECT category.id, category.parent_id, catname FROM (SELECT CONNECT_BY_ROOT id AS parent_id, id FROM category WHERE CONNECT_BY_ISLEAF = 1 AND id = 1121 CONNECT BY PRIOR id = parent_id) t1  JOIN category ON t1.parent_id = category.id ORDER BY parent_id")
public class Category implements Serializable {
//    public static final String NamedQuery_findCatnameById = "findCatnameById";
    private int id;

    @Column(name = "parent_id")
    private int parentId;

    @Column(name = "catname")
    private String catname;


    private Set<Product> productSet = new HashSet<Product>();

    public Category() {
    }

    @OneToMany(mappedBy = "category", cascade=CascadeType.ALL, orphanRemoval=true)
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(sequenceName = "category_seq", allocationSize = 1, name = "category_seq")
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
