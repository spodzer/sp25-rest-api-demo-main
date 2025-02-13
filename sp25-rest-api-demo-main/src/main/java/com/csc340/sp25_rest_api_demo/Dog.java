package com.csc340.sp25_rest_api_demo;


public class Dog {
    private int id;
    private String breed;
    private String category;
    private String lifespan;
    private String temperament;
    private String imageUrl;

    // Constructor
    public Dog(int id, String breed, String category, String lifespan, String temperament) {
        this.id = id;
        this.breed = breed;
        this.category = category;
        this.lifespan = lifespan;
        this.temperament = temperament;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLifespan() {
        return lifespan;
    }

    public void setLifespan(String lifespan) {
        this.lifespan = lifespan;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

}
