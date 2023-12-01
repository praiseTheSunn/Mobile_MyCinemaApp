package com.example.mycinemaapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class MovieParcelable implements Parcelable {

    private String imagePath;
    private String title;
    private float rating;
    private int duration;
    private String type;
    private String categories;
    private String synopsis;

    // Constructor
    public MovieParcelable(String imagePath, String title, float rating, int duration, String type, String categories, String synopsis) {
        this.imagePath = imagePath;
        this.title = title;
        this.rating = rating;
        this.duration = duration;
        this.type = type;
        this.categories = categories;
        this.synopsis = synopsis;
    }

    // Constructor based on Movie object
//    public MovieParcelable(Movie movie) {
//        this.imagePath = movie.getImagePath();
//        this.title = movie.getTitle();
//        this.rating = movie.getRating();
//        this.duration = movie.getDuration();
//        this.type = movie.getType();
//        this.categories = movie.getCategories();
//        this.synopsis = movie.getSynopsis();
//    }

    public MovieParcelable(MovieEntity movie) {
        this.imagePath = movie.getImagePath();
        this.title = movie.getTitle();
        this.rating = movie.getRating();
        this.duration = movie.getDuration();
        this.type = movie.getType();
        this.categories = movie.getCategories();
        this.synopsis = movie.getSynopsis();
    }

    // Parcelable constructor
    private MovieParcelable(Parcel in) {
        imagePath = in.readString();
        title = in.readString();
        rating = in.readFloat();
        duration = in.readInt();
        type = in.readString();
//        categories = new ArrayList<>();
        categories = in.readString();
//        in.readList(categories, null);
        synopsis = in.readString();
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

//    public List<String> getCategories() {
//        return categories;
//    }
    public String getCategories() {
        return categories;
    }

    public String getSynopsis() {
        return synopsis;
    }

    // Implement Parcelable.Creator
    public static final Creator<MovieParcelable> CREATOR = new Creator<MovieParcelable>() {
        @Override
        public MovieParcelable createFromParcel(Parcel in) {
            return new MovieParcelable(in);
        }

        @Override
        public MovieParcelable[] newArray(int size) {
            return new MovieParcelable[size];
        }
    };

    // Implement Parcelable methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imagePath);
        dest.writeString(title);
        dest.writeFloat(rating);
        dest.writeInt(duration);
        dest.writeString(type);
        dest.writeString(categories);
        dest.writeString(synopsis);
    }
}
