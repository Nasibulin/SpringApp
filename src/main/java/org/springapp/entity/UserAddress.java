package org.springapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_addresses")
public class UserAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer addressId;

    private User user;

    @Column(name = "address")
    private String address;

    @Column(name = "apartment")
    private String apartment;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "fax")
    private String fax;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "country")
    private String country;
    
//    @Column(name = "status")
//    private int status;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_addresses_seq")
    @SequenceGenerator(sequenceName = "user_addresses_seq", allocationSize = 1, name = "user_addresses_seq")
    @Column(name = "address_id")
    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    //    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
}
