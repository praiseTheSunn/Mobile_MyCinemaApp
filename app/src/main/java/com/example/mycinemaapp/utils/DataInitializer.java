package com.example.mycinemaapp.utils;

import android.content.Context;

import com.example.mycinemaapp.daos.MovieDao;
import com.example.mycinemaapp.models.MovieEntity;
import com.example.mycinemaapp.repositories.MovieRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// In your repository or a dedicated data initialization class
public class DataInitializer {

    private MovieDao movieDao;
    private Context context;

    public DataInitializer(Context context, MovieDao movieDao) {
        this.movieDao = movieDao;
        this.context = context;
    }

    public void initializeData(File csvFile) {
        try {

            List<String[]> csvData = CsvReaderUtil.readCsv(csvFile);

            for (String[] row : csvData) {
                // Parse CSV data and create MovieEntity objects
                MovieEntity movie = parseCsvRow(row);

                // Insert each movie into the Room database
                movieDao.insertMovie(movie);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initializeData(String fileName) {
        try {
            MovieRepository movieRepo = new MovieRepository(movieDao);
            List<String[]> csvData = CsvReaderUtil.readCsvFromAssets(context, fileName);

            int i = 0;
            for (String[] row : csvData) {
                // Parse CSV data and create MovieEntity objects
                MovieEntity movie = parseCsvRow(row);
                movie.setId(i++);

                // Insert each movie into the Room database
                movieRepo.insertMovie(movie);
                int a = 5;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MovieEntity parseCsvRow(String[] row) {
        // Implement logic to convert CSV row to MovieEntity object
        // For example:
        return new MovieEntity(
                (row[0]),    // Assuming the first column is the image resource ID
                row[1],                      // Title
                Float.parseFloat(row[2]),    // Rating
                Integer.parseInt(row[3]),    // Duration
                row[4],                      // Type
                new ArrayList<>(Arrays.asList(row[5].split(","))),  // Categories
                row[6]                       // Synopsis
        );
    }

}
