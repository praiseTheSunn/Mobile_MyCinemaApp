package com.example.mycinemaapp.viewModels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.mycinemaapp.models.MovieEntity;
import com.example.mycinemaapp.repositories.MovieRepository;

import java.util.List;

public class AllMovieFragmentViewModel extends ViewModel {
    List<MovieEntity> movieEntityList = null;
    MovieRepository movieRepository = null;

    public List<MovieEntity> getMovies(Context context) {
        if (movieEntityList != null)
            return movieEntityList;
        if (movieRepository == null)
            movieRepository = new MovieRepository(context);
        movieEntityList = movieRepository.getAllMovies().blockingFirst();

        return movieEntityList;
    }

    public List<MovieEntity> getMoviesWithNames(Context context, String newText) {
        if (movieRepository == null)
            movieRepository = new MovieRepository(context);
        List<MovieEntity> newMovieEntities = movieRepository.getAllMoviesByNames(newText);

        return newMovieEntities;
    }
}
