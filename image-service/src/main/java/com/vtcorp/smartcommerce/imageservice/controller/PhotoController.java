package com.vtcorp.smartcommerce.imageservice.controller;

import com.vtcorp.smartcommerce.imageservice.model.Photo;
import com.vtcorp.smartcommerce.imageservice.service.PhotoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {
    @Autowired
    private PhotoServiceImpl photoService;

    @PostMapping("/add")
    public String addPhoto(@RequestParam("title") String title,
                           @RequestParam("image") MultipartFile image) throws IOException {
        String id = photoService.addPhoto(title, image);
        return "redirect:/photos/" + id;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Photo> getPhoto(@PathVariable String id) {
        Photo photo = photoService.getPhoto(id);
//        final HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_JPEG);
//        return new ResponseEntity<byte[]>(photo.getImage().getData(), headers, HttpStatus.OK);
        return new ResponseEntity<Photo>(photo, HttpStatus.OK);
    }
}
