package com.example.mycinemaapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.util.Log;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.daos.MovieDao;
import com.example.mycinemaapp.databases.MovieDatabase;
import com.example.mycinemaapp.models.MovieEntity;
import com.example.mycinemaapp.repositories.MovieRepository;
import com.example.mycinemaapp.utils.DataInitializer;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//         Initialize the Room database
        MovieDatabase.getInstance(this);

//         Initialize data from CSV file
        initializeData();
    }

    private void initializeData() {
        MovieDao movieDao = MovieDatabase.getInstance(this).movieDao();
        MovieRepository movieRepo = new MovieRepository(movieDao);

        if (movieRepo.getCount() > 0)
            return;


        DataInitializer dataInitializer = new DataInitializer(this, movieDao);

        // Call the data initialization process
        dataInitializer.initializeData("movie.csv");
    }
}