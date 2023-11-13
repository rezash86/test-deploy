package com.example.restservice.api;

import com.example.restservice.model.Animal;
import com.example.restservice.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class AnimalController {

    AnimalService animalService = new AnimalService();

    @GetMapping("/message")
    public String getDefault() {
       return "hello world";
    }

    @GetMapping("/animal")
    public ResponseEntity<Animal> getRandomDog() {
        try {
            return new ResponseEntity<>(animalService.getDog(), HttpStatus.OK);
        }
        catch (NullPointerException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity("Picture Download Failed", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/animal", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity fileUpload(@RequestParam("file") MultipartFile file) {
        try {
            animalService.uploadAnimalPicture(file);
            return new ResponseEntity("Upload Successful", HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}