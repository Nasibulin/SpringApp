package org.springapp.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_payments")
public class OrderPayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "order_id")
    private Integer orderId;

    @Id
    @Column(name = "payment_id")
    private Integer paymentId;

    @Column(name = "transaction_id")
    private Integer transactionId;
    
    @Column(name = "status")
    private int status;

}
