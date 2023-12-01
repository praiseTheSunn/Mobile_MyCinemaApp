package com.example.mycinemaapp.models;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;

// MovieEntity.java
@Entity(tableName = "movies")
public class MovieEntity {

//    @PrimaryKey(autoGenerate = true)
//    @PrimaryKey
    public int id;

    public String imagePath;
    @PrimaryKey @NonNull
    public String title;
    public float rating;
    public int duration;
    public String type;
    public String categories; // Convert the list of categories to a single string
    public String synopsis;

    // Constructors, getters, and setters
    // Constructor
    public MovieEntity(int id, String imagePath, String title, float rating, int duration, String type, String categories, String synopsis) {
        this.id = id;
        this.imagePath = imagePath;
        this.title = title;
        this.rating = rating;
        this.duration = duration;
        this.type = type;
        this.categories = categories;
        this.synopsis = synopsis;
    }

    @Ignore
    public MovieEntity(String imagePath, String title, float rating, int duration, String type, String categories, String synopsis) {
        this.imagePath = imagePath;
        this.title = title;
        this.rating = rating;
        this.duration = duration;
        this.type = type;
        this.categories = categories;
        this.synopsis = synopsis;
    }

    public MovieEntity(String imagePath, String title, float rating, int duration, String type, ArrayList<String> categories, String synopsis) {
        this.imagePath = imagePath;
        this.title = title;
        this.rating = rating;
        this.duration = duration;
        this.type = type;
        this.categories = fromCategoriesList(categories);
        this.synopsis = synopsis;
    }

    public int getId() {
        return id;
    }

    public void setId(int _id) {
        id = _id;
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

    public String getSynopsis() {
        return synopsis;
    }

    public String getCategories() {
        return categories;
    }

    // Helper method to convert list of categories to a comma-separated string
    @TypeConverter
    public static String fromCategoriesList(ArrayList<String> categories) {
        return TextUtils.join(",", categories);
    }

    // Helper method to convert comma-separated string to a list of categories
    @TypeConverter
    public static ArrayList<String> toCategoriesList(String categories) {
        return new ArrayList<>(Arrays.asList(categories.split(",")));
    }
}
