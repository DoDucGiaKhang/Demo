package com.vvt.icommerce.orderservice.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class OrderDTO {
    private List<OrderProductDTO> productOrders;
}
