package com.targa.labs.myboutique.orderservice.domain;

import com.targa.labs.myboutique.commons.domain.AbstractEntity;
import com.targa.labs.myboutique.orderservice.domain.enumeration.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * A Payment.
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "payment")
public class Payment extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paypal_payment_id")
    private String paypalPaymentId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status;

    @OneToOne
    @JoinColumn(unique = true)
    private Order order;

    public Payment() {
        // Empty Constructor for JPA
    }

    public Payment(String paypalPaymentId, PaymentStatus status, Order order) {
        this.paypalPaymentId = paypalPaymentId;
        this.status = status;
        this.order = order;
    }

    public String getPaypalPaymentId() {
        return paypalPaymentId;
    }

    public void setPaypalPaymentId(String paypalPaymentId) {
        this.paypalPaymentId = paypalPaymentId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
