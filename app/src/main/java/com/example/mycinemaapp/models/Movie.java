package com.example.mycinemaapp.models;

import java.util.ArrayList;

public class Movie {
    private String imagePath;
    private String title;
    private float rating;
    private int duration;
    private String type;
    private ArrayList<String> categories;
    private String synopsis;

    // Constructor
    public Movie(String imagePath, String title, float rating, int duration, String type, ArrayList<String> categories, String synopsis) {
        this.imagePath = imagePath;
        this.title = title;
        this.rating = rating;
        this.duration = duration;
        this.type = type;
        this.categories = categories;
        this.synopsis = synopsis;
    }

    // Getters
    public String getImagePath() {
        return imagePath;
    }

    public String getTitle() {
        return title;
    }

    public float getRating() {
        return rating;
    }

    public int getDuration() {
        return duration;
    }

    public String getType() {
        return type;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public String getSynopsis() {
        return synopsis;
    }
}
