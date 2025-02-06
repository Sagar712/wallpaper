package com.example.wallpaperservice.services;

import com.example.wallpaperservice.web.PhotoEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CloudStorageService {
    @Autowired
    MongoAtlasRepo mongoAtlasRepo;

    public void saveImage(MultipartFile file){
        try{
            byte[] imageToByte = file.getBytes();
            PhotoEntry photoEntry = new PhotoEntry();
            photoEntry.setTitle(file.getOriginalFilename());
            photoEntry.setMedia(imageToByte);
//            photoEntry.setMedia(null);
            photoEntry.setUploadTime(Date.from(Instant.now()));
            photoEntry.setCategory(null);
            photoEntry.setRating(0);
            mongoAtlasRepo.save(photoEntry);
        }
        catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePhoto(String id, MultipartFile file){
        try{
            Optional<PhotoEntry> pic_exists = mongoAtlasRepo.findById(id);
            if(pic_exists.isPresent()){
                PhotoEntry photo = pic_exists.get();
                photo.setMedia(file.getBytes());
                photo.setTitle(file.getOriginalFilename());
                photo.setUploadTime(Date.from(Instant.now()));
                mongoAtlasRepo.save(photo);
            }
        } catch (RuntimeException | IOException r) {
            throw new RuntimeException(r);
        }
    }

    public List<PhotoEntry> getAllPhotos(){
        try{
            return mongoAtlasRepo.findAll();
        }catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deletePhotoById(String id){
        try{
            Optional<PhotoEntry> pic_exists = mongoAtlasRepo.findById(id);
            if(pic_exists.isEmpty())
                return false;
            mongoAtlasRepo.deleteById(id);
        }catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public PhotoEntry findPhotoById(String id){
        try{
            Optional<PhotoEntry> pic_exists = mongoAtlasRepo.findById(id);
            if(!pic_exists.isEmpty())
                return pic_exists.get();
        }catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
