package com.example.mycinemaapp.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.mycinemaapp.daos.MovieDao;
import com.example.mycinemaapp.databases.MovieDatabase;
import com.example.mycinemaapp.models.MovieEntity;
import com.example.mycinemaapp.repositories.MovieRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

// MovieViewModel.java
public class MovieViewModel extends AndroidViewModel {

    private MovieRepository repository;
    private Flowable<List<MovieEntity>> movies;

    public MovieViewModel(Application application) {
        super(application);
        MovieDao movieDao = MovieDatabase.getInstance(application).movieDao();
//        movies = movieDao.getAllMovies();
        repository = new MovieRepository(movieDao);
        movies = repository.getAllMovies();
    }

    public Flowable<List<MovieEntity>> getMovies() {
        return movies;
    }

    public void insertMovie(MovieEntity movie) {
        repository.insertMovie(movie);
    }
}
