package com.example.wallpaperservice.web;

import com.example.wallpaperservice.services.CloudStorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static com.example.wallpaperservice.utils.Utils.findLengthInKbMb;

@RestController
@RequestMapping("/data")
@CrossOrigin(origins = "*")
public class MongoController {

    @Autowired
    CloudStorageService cloudStorageService;

//    @GetMapping("/")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    String test(){
        return "Data endpoint working !";
    }

    @PostMapping("/upload")
    ResponseEntity<?> uploadSingleImage(@RequestParam("image") MultipartFile image){
        try{
            cloudStorageService.saveImage(image);
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal error occurred!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Image saved successfully!", HttpStatus.OK);
    }

    @PutMapping("/upload")
    ResponseEntity<?> updateSingleImage(@RequestParam("image") MultipartFile image, @RequestParam("id") String id){
        try{
            cloudStorageService.updatePhoto(id, image);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>("Internal error occurred!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Image updated successfully!", HttpStatus.OK);
    }

    @GetMapping("/showAll")
    ResponseEntity<?> getAllImages(){
        List<PhotoEntry> allImagesObj = cloudStorageService.getAllPhotos();
        List<String> allImages = new ArrayList<>();
        for(PhotoEntry picObj: allImagesObj){
            allImages.add(Base64.getEncoder().encodeToString(picObj.getMedia()));
        }
        return new ResponseEntity<>(allImages.reversed(), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    ResponseEntity<?> getAllImagesInstances(){
        List<PhotoEntry> allImagesObj = cloudStorageService.getAllPhotos();
        List<String> allImages = new ArrayList<>();
        for(PhotoEntry picObj: allImagesObj){
//            picObj.setMedia(null);

            // Need to encode all byte[] to send as response!
            allImages.add(Base64.getEncoder().encodeToString(picObj.getMedia()));
        }
        return new ResponseEntity<>(allImagesObj.reversed(), HttpStatus.OK);
    }

    @GetMapping("/getIds")
    ResponseEntity<List<ShortDescPhoto>> getAllImagesDescription(HttpServletRequest request){
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        List<PhotoEntry> allImagesObj = cloudStorageService.getAllPhotos();
        List<ShortDescPhoto> allImages = new ArrayList<>();
        for(PhotoEntry picObj: allImagesObj){
            String len = findLengthInKbMb(String.valueOf(picObj.getMedia().length / (1024.0 * 1024.0)));
            StringBuilder temp = new StringBuilder(baseUrl + "/data/get?id="+picObj.getId());
            allImages.add(new ShortDescPhoto(picObj.getId(), picObj.getTitle(), picObj.getUploadTime(), picObj.getCategory(), picObj.getRating(), len, String.valueOf(temp)));
        }
        return new ResponseEntity<>(allImages.reversed(), HttpStatus.OK);
    }

    @GetMapping("/total")
    ResponseEntity<?> total(){
        List<PhotoEntry> allImagesObj = cloudStorageService.getAllPhotos();
        Map<String, Long> map = new HashMap<>();
        map.put("total images:", (long) allImagesObj.size());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/delete")
    ResponseEntity<?> deleteImage(@RequestParam String id){
        try{
            if(!cloudStorageService.deletePhotoById(id))
                return new ResponseEntity<>("Id not found. Please provide existing id only.", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>("Sorry for convenience. Some error on our side", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Successfully deleted entry: "+id, HttpStatus.OK);
    }

    @GetMapping("/show")
    ResponseEntity<?> getImageByIndex(@RequestParam int index){
        List<PhotoEntry> allImagesObj = cloudStorageService.getAllPhotos();
        int init=1;
        for(PhotoEntry p : allImagesObj){
            if(init < index){
                init++;
                continue;
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("image/png"))
                    .body(p.getMedia());
        }
        return new ResponseEntity<>("Index is out of range", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get")
    ResponseEntity<?> getImageById(@RequestParam String id){
        PhotoEntry image = cloudStorageService.findPhotoById(id);
        if(image != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("image/png"))
                    .body(image.getMedia());
        return new ResponseEntity<>("Invalid/Not found", HttpStatus.BAD_REQUEST);
    }
}
