package com.example.mycinemaapp.utils;

import android.content.Context;

import com.example.mycinemaapp.daos.MovieDao;
import com.example.mycinemaapp.models.CinemaEntity;
import com.example.mycinemaapp.models.CinemaRoomEntity;
import com.example.mycinemaapp.models.MovieEntity;
import com.example.mycinemaapp.models.MovieShowEntity;
import com.example.mycinemaapp.repositories.CinemaRepository;
import com.example.mycinemaapp.repositories.MovieRepository;
import com.example.mycinemaapp.repositories.MovieShowRepository;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

// In your repository or a dedicated data initialization class
public class DataInitializer {

    private MovieRepository movieRepo;
    private MovieShowRepository movieShowRepository;
    private CinemaRepository cinemaRepository;
    private Context context;

    public DataInitializer(Context context) {
        movieRepo = new MovieRepository(context);
        movieShowRepository = new MovieShowRepository(context);
        cinemaRepository = new CinemaRepository(context);
        this.context = context;
    }

    public void initializeMovies(String fileName) {
        try {

            List<String[]> csvData = CsvReaderUtil.readCsvFromAssets(context, fileName);

            int i = 0;
            for (String[] row : csvData) {
                // Parse CSV data and create MovieEntity objects
                MovieEntity movie = parseCsvRowForMovie(row);
                movie.setId(i++);

                // Insert each movie into the Room database
                movieRepo.insertMovie(movie);
                int a = 5;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MovieEntity parseCsvRowForMovie(String[] row) {
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

    public void initializeMovieShows(String fileName) {
        try {

            List<String[]> csvData = CsvReaderUtil.readCsvFromAssets(context, fileName);

            int i = 1;
            for (String[] row : csvData) {
                // Parse CSV data and create MovieEntity objects
                MovieShowEntity movieShow = parseCsvRowForMovieShow(row);
//                movie.setId(i++);

                // Insert each movie into the Room database
                movieShowRepository.insertMovie(movieShow);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MovieShowEntity parseCsvRowForMovieShow(String[] row) {
        // Implement logic to convert CSV row to MovieShowEntity object
        // For example:

        return new MovieShowEntity(
                Date.valueOf(row[0]),    // Assuming the first column is the show date in "yyyy-MM-dd" format
                row[1],                     // Show time
                Long.parseLong(row[2]), // cinemaId
                Long.parseLong(row[3]), // roomId
                Long.parseLong(row[4]) // movieId
        );
    }

    public void initializeCinemas(String fileName) {
        try {

            List<String[]> csvData = CsvReaderUtil.readCsvFromAssets(context, fileName);

            int i = 0;
            for (String[] row : csvData) {
                // Parse CSV data and create MovieEntity objects
                CinemaEntity cinemaEntity = parseCsvRowForCinema(row);
//                movie.setId(i++);

                // Insert each movie into the Room database
                cinemaRepository.insertCinema(cinemaEntity);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CinemaEntity parseCsvRowForCinema(String[] row) {
        // Implement logic to convert CSV row to MovieShowEntity object
        // For example:

        return new CinemaEntity(
                Long.parseLong(row[0]), // id
                row[1]  // name
        );
    }
}
