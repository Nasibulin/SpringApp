package org.springapp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="users", schema = "springapp", catalog = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String password;

    @Column(name = "status")
    private Integer status;

    private Date createDate;

    @Column(name = "phone")
    private String phone;

    private Role role;

    @Column(name = "salt")
    private String salt;

    private Set<Order> orders;

    public User() {
        createDate = null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_user_id_seq")
    @SequenceGenerator(sequenceName = "users_user_id_seq", allocationSize = 1, name = "users_user_id_seq")
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL, orphanRemoval=true)
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
    @ManyToOne
    @JoinColumn(name = "roles_role_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "password_hash")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

//    @Column(name = "created_at")
//    public Date getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
