package com.example.wallpaperservice.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.example.wallpaperservice.utils.Utils.generateRandomString;

@Service
public class LocalServices {
    public void storeSingleImageLocal(MultipartFile file) throws IOException {
        byte[] imageToBytes = file.getBytes();
        String fileName = generateRandomString(10) + ".txt";
        try{
            File file1 = new File("./src/main/resources/images/"+fileName);
            FileOutputStream fo = new FileOutputStream(file1);
            fo.write(imageToBytes);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<byte[]> retrieveAllImages(){
        String folderPath = "./src/main/resources/images";
        List<byte[]> imagesData = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(folderPath))) {
            for (Path filePath : stream) {
                if (Files.isRegularFile(filePath)) {  // Ensure it’s a regular file, not a directory
                    byte[] fileBytes = Files.readAllBytes(filePath);
                    imagesData.add(fileBytes);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imagesData;
    }
    public byte[] retrieveFirstImage(){
        String folderPath = "./src/main/resources/images";
        List<byte[]> imagesData = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(folderPath))) {
            for (Path filePath : stream) {
                if (Files.isRegularFile(filePath)) {  // Ensure it’s a regular file, not a directory
                    byte[] fileBytes = Files.readAllBytes(filePath);
                    imagesData.add(fileBytes);
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imagesData.getFirst();
    }

    public List<byte[]> retrieveImagesInChunks(int chunkSize, int start){
        String folderPath = "./src/main/resources/images";
        List<byte[]> imagesData = new ArrayList<>();
        int init = 0, dataCount = 0;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(folderPath))) {
            for (Path filePath : stream) {
                if(init < start){
                    init++;
                    continue;
                }
                if(dataCount >= chunkSize)
                    break;
                if (Files.isRegularFile(filePath)) {  // Ensure it’s a regular file, not a directory
                    byte[] fileBytes = Files.readAllBytes(filePath);
                    imagesData.add(fileBytes);
                    dataCount++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imagesData;
    }
}
