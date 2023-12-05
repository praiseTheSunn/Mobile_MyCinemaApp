package com.example.mycinemaapp.repositories;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mycinemaapp.daos.MovieDao;
import com.example.mycinemaapp.databases.MovieDatabase;
import com.example.mycinemaapp.models.MovieEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

// MovieRepository.java
public class MovieRepository {
    private MovieDatabase movieDatabase;

    public MovieRepository(Context context) {
        movieDatabase = MovieDatabase.getInstance(context);
    }

    public Flowable<List<MovieEntity>> getAllMovies() {
        return movieDatabase.movieDao().getAllMovies();
    }

    public void insertMovie(MovieEntity movie) {
        // Use a background thread for database operations
        new InsertMovieAsyncTask(movieDatabase.movieDao()).execute(movie);
    }

    public int getCount() {
//        return movieDao.getCount();
        try {
            return new GetCountAsyncTask(movieDatabase.movieDao()).execute().get();
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // Handle the exception according to your requirements
        }

    }
    public Flowable<List<MovieEntity>> getHotMovies() {
        return movieDatabase.movieDao().getHotMovies();
    }

    public List<MovieEntity> getAllMoviesByNames(String newText) {
        List<MovieEntity> newMovies = movieDatabase.movieDao().getMoviesByNames(newText).blockingFirst();
        return newMovies;
    }


    // AsyncTask to insert a movie in the background
    private static class InsertMovieAsyncTask extends AsyncTask<MovieEntity, Void, Void> {
        private MovieDao movieDao;

        private InsertMovieAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(MovieEntity... movies) {
            movieDao.insertMovie(movies[0]);
            return null;
        }
    }

    private static class GetCountAsyncTask extends AsyncTask<Void, Void, Integer> {
        private MovieDao movieDao;

        private GetCountAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return movieDao.getCount();
        }
    }
}
