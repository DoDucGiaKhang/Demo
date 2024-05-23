package com.vvt.icommerce.paymentservice.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.transaction.Transactional;

@Entity
@Transactional
@Setter @Getter @NoArgsConstructor
public class PaymentOrder {
    @Id
    private Long orderId;

    private Long userId;

    private String status;

    private Double totalPrice;

}
