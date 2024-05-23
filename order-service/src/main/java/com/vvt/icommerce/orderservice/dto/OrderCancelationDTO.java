package com.vvt.icommerce.orderservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class OrderCancelationDTO {
    private Long orderId;
    private String reason;
}
