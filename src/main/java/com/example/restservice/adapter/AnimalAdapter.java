package com.example.restservice.adapter;

import com.example.restservice.model.Animal;
import org.springframework.web.client.RestTemplate;

public class AnimalAdapter {

    public Animal callDogApi(){
        String url = "https://dog.ceo/api/breeds/image/random";
        RestTemplate restTemplate = new RestTemplate();
        Animal dog = restTemplate.getForObject(url, Animal.class);
        return dog;
    }
}