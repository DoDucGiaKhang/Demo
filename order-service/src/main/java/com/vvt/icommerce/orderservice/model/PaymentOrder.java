package com.vvt.icommerce.orderservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Entity
@Transactional
@Setter @Getter @NoArgsConstructor
public class PaymentOrder {
    @Id
    private Long orderId;

    private Long userId;

    private String status;

    private Double totalPrice;

    public PaymentOrder(Long orderId, Long userId, String status, Double totalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.status = status;
        this.totalPrice = totalPrice;
    }
}
