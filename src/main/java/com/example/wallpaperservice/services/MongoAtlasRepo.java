package com.example.wallpaperservice.services;
import com.example.wallpaperservice.web.PhotoEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoAtlasRepo extends MongoRepository<PhotoEntry, String> {
}
