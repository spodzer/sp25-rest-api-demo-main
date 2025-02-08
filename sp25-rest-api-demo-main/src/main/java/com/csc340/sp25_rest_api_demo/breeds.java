package com.csc340.sp25_rest_api_demo;

public class breeds {
    private int id;
    private String name;
    private String bredFor;
    private String lifeSpan;
    private String temperament;
    private String imageUrl;


    public breeds(int id, String name, String bredFor, String lifeSpan, String temperament, String imageUrl) {
        this.id = id;
        this.name = name;
        this.bredFor = bredFor;
        this.lifeSpan = lifeSpan;
        this.temperament = temperament;
        this.imageUrl = imageUrl;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBredFor() { return bredFor; }
    public void setBredFor(String bredFor) { this.bredFor = bredFor; }

    public String getLifeSpan() { return lifeSpan; }
    public void setLifeSpan(String lifeSpan) { this.lifeSpan = lifeSpan; }

    public String getTemperament() { return temperament; }
    public void setTemperament(String temperament) { this.temperament = temperament; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
