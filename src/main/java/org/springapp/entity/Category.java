package org.springapp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;





@Entity
@Table(name = "category", schema = "springapp", catalog = "")

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = Category.NamedQuery_findCatnameById,
                resultSetMappings = {
                        "CatnameMapping"
                },
                procedureName = "cat_tree",
                //resultClasses = Category.class,
                parameters = {
                        @StoredProcedureParameter(name = "catid", type = Integer.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "cat", type = void.class, mode = ParameterMode.REF_CURSOR)
                }
        )})

@SqlResultSetMapping(
        name = "CatnameMapping",
        entities = @EntityResult(
                entityClass = Category.class,
                fields = {
                        @FieldResult(name = "id", column = "id"),
                        @FieldResult(name = "parent_Id", column = "parent_id"),
                        @FieldResult(name = "catname", column = "catname")}))
public class Category implements Serializable {
    public static final String NamedQuery_findCatnameById = "findCatnameById";
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
