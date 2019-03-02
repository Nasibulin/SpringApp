package org.springapp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="users", schema = "springapp", catalog = "")
public class User implements Serializable {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_at")
    private Date createDate;

    @Column(name = "roles_role_id")
    private Integer roleId;

    @Column(name = "salt")
    private Integer salt;

}
