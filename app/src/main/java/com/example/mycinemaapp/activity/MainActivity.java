package com.example.mycinemaapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.daos.MovieDao;
import com.example.mycinemaapp.databases.MovieDatabase;
import com.example.mycinemaapp.models.MovieEntity;
import com.example.mycinemaapp.repositories.MovieRepository;
import com.example.mycinemaapp.repositories.MovieShowRepository;
import com.example.mycinemaapp.utils.DataInitializer;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

//         Initialize the Room database
        MovieDatabase.getInstance(this);

//         Initialize data from CSV file
        initializeData();



    }

    private void initializeData() {
        MovieRepository movieRepo = new MovieRepository(this);

        if (movieRepo.getCount() > 0)
            return;


        DataInitializer dataInitializer = new DataInitializer(this);

        // Call the data initialization process
        dataInitializer.initializeMovies("movie.csv");
        dataInitializer.initializeMovieShows("movieShows.csv");
        dataInitializer.initializeCinemas("cinema.csv");

//        MovieShowRepository movieShowRepository = new MovieShowRepository(this);
//        Log.d("DATABASE", "" + movieShowRepository.getCount());
    }
}