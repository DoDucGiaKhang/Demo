package com.vtcorp.smartcommerce.imageservice.service;

import com.vtcorp.smartcommerce.imageservice.model.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoService {
    public String addPhoto(String title, MultipartFile file) throws IOException;

    public Photo getPhoto(String id);
}
