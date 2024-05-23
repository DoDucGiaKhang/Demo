package com.vvt.icommerce.userservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor
public class Binary {
    private byte type;
    private byte[] data;
}
