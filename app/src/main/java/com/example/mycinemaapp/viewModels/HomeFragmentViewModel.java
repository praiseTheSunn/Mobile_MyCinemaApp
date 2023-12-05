package com.example.mycinemaapp.viewModels;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycinemaapp.models.Movie;
import com.example.mycinemaapp.models.MovieEntity;
import com.example.mycinemaapp.repositories.MovieRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class HomeFragmentViewModel extends ViewModel {

    List<MovieEntity> movieEntityList = null;
    List<MovieEntity> hotMovieEntityList = null;
    MovieRepository movieRepository = null;

    public List<MovieEntity> getMovies(Context context) {
        if (movieEntityList != null)
            return movieEntityList;
        if (movieRepository == null)
            movieRepository = new MovieRepository(context);
        movieEntityList = movieRepository.getAllMovies().blockingFirst();

        return movieEntityList;
    }

    public List<MovieEntity> getHotMovies(Context context) {
        if (hotMovieEntityList != null)
            return hotMovieEntityList;
        if (movieRepository == null)
            movieRepository = new MovieRepository(context);

        hotMovieEntityList = movieRepository.getHotMovies().blockingFirst();
        return hotMovieEntityList;
    }
}
