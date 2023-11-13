package com.example.restservice.service;


import com.example.restservice.adapter.AnimalAdapter;
import com.example.restservice.model.Animal;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AnimalService {

    AnimalAdapter animalAdapter = new AnimalAdapter();

    public Animal getDog() throws IOException {
        Animal dog = animalAdapter.callDogApi();
        if(dog == null) {
            throw new NullPointerException("Dog not found");
        }
        else {
                String urlAsString = dog.getMessage();
                String[] urlAsArray = urlAsString.split("/");
                URL url = new URL(dog.getMessage());
                InputStream in = url.openStream();
                String path = "src\\main\\resources\\download\\" + urlAsArray[urlAsArray.length-1];
                Files.copy(in, Paths.get(path));

        }
        return dog;
    }

    public void uploadAnimalPicture(MultipartFile file) throws IOException {
        File convertFile = new File("src\\main\\resources\\upload\\"+file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
    }
}