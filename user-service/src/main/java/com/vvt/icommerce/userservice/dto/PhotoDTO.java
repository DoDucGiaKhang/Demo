package com.vvt.icommerce.userservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor
public class PhotoDTO {
    private String id;

    private String title;

    private Binary image;

    public PhotoDTO(String id, String title, Binary image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }
}
