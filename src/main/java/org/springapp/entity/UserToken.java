package org.springapp.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user_tokens")
public class UserToken implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "token")
    private String token;
    
    @Column(name = "user_id")
    private String userId;
    
    @Column(name = "login_date")
    private Date loginDate;
    
    @Column(name = "expiration_date")
    private Date expirationDate;
    
    @Column(name = "session_data")
    private String sessionData;
    
}
