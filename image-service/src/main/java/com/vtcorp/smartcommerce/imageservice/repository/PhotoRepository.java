package com.vtcorp.smartcommerce.imageservice.repository;

import com.vtcorp.smartcommerce.imageservice.model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo, String> { }
