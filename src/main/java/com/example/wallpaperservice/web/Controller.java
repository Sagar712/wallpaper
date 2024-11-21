package com.example.wallpaperservice.web;

import com.example.wallpaperservice.services.LocalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    LocalServices localServices;

    @GetMapping("/test")
    String testApp(){
        return "App working!";
    }

    @PostMapping("/image")
    ResponseEntity<String> uploadImageSingle(@RequestParam("image") MultipartFile image){
        try{
            localServices.storeSingleImageLocal(image);
        }
        catch (Exception e){
            return new ResponseEntity<>("Unexpected error occured!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Image saved succesffully!", HttpStatus.OK);
    }

    @GetMapping("/images/first")
    ResponseEntity<?> getFirstImages(){
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(localServices.retrieveFirstImage());
    }

    @GetMapping("/images/all")
    ResponseEntity<?> getAllImages(){
        List<byte[]> allImages = localServices.retrieveAllImages();
        List<String> base64Images = allImages.stream()
                .map(x -> Base64.getEncoder().encodeToString(x))
                .toList();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(base64Images, HttpStatus.OK);
    }
    @GetMapping("/images/chunks/{chunk_details}")
    ResponseEntity<?> getImagesInChunks(@PathVariable String chunk_details){
        System.out.println(chunk_details);
        String[] chunk_details_splits = chunk_details.split("-");
        if(chunk_details_splits.length != 2)
            return new ResponseEntity<>("Incorrect chunks details provided!\n" +
                    "Correct way: /images/chunks/10-0 (here 10 - chunk size, 0 - start)",
                    HttpStatus.BAD_REQUEST);

        int chunk_size = Integer.parseInt(chunk_details_splits[0]);
        int start_index = Integer.parseInt(chunk_details_splits[1]);
        List<byte[]> allImages = localServices.retrieveImagesInChunks(chunk_size, start_index);
        List<String> base64Images = allImages.stream()
                .map(x -> Base64.getEncoder().encodeToString(x))
                .toList();

        return new ResponseEntity<>(base64Images, HttpStatus.OK);
    }
}
