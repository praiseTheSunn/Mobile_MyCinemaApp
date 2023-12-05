package com.example.mycinemaapp.repositories;

import android.content.Context;
import android.os.AsyncTask;

import com.example.mycinemaapp.daos.CinemaDao;
import com.example.mycinemaapp.daos.MovieDao;
import com.example.mycinemaapp.daos.MovieShowDao;
import com.example.mycinemaapp.databases.CinemaDatabase;
import com.example.mycinemaapp.databases.MovieShowDatabase;
import com.example.mycinemaapp.models.MovieShowEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class MovieShowRepository {
    private MovieShowDatabase movieShowDatabase;
    private CinemaDatabase cinemaDatabase;

    public MovieShowRepository(Context context) {
        movieShowDatabase = MovieShowDatabase.getInstance(context);
        cinemaDatabase = CinemaDatabase.getInstance(context);
    }

    public void insertMovie(MovieShowEntity movieShow) {
        new MovieShowRepository.InsertMovieShowAsyncTask(movieShowDatabase.movieShowDao()).execute(movieShow);
    }

    public int getCount() {
//        return movieDao.getCount();
        try {
            return new MovieShowRepository.GetCountAsyncTask(movieShowDatabase.movieShowDao()).execute().get();
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // Handle the exception according to your requirements
        }

    }

    public List<String> getCinemaNamesByDateAndMovieId(Date date, long movieId) {
        List<Long> cinemaIds = getCinemaIdsByDateAndMovieId(date, movieId).blockingFirst();

        List<String> cinemaNames = new ArrayList<>();

        for (int i = 0; i < cinemaIds.size(); i++) {
            String cinemaName = getCinemaNameById(cinemaIds.get(i));
//            String cinemaName = cinemaId.get(i).toString();
            cinemaNames.add(cinemaName);
        }

        return cinemaNames;
    }

    public Flowable<List<Long>> getCinemaIdsByDateAndMovieId(Date date, Long movieId) {
        Flowable<List<Long>> cinemaIds = movieShowDatabase.movieShowDao().getCinemaIdByDateAndMovieId(date, movieId);
        return cinemaIds;
    }

    String getCinemaNameById(long id) {
        try {
            return new MovieShowRepository.GetCinemaNameByIdAsyncTask(cinemaDatabase.cinemaDao()).execute(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return ""; // Handle the exception according to your requirements
        }
    }

    public List<String> getTimesByDateAndMovieIdAndCinemaId(Date date, Long movieId, Long cinemaId) {
        List<String> res;
        res = movieShowDatabase.movieShowDao().getTimeByDateAndMovieIdAndCinemaId(date, movieId, cinemaId).blockingFirst();
        return res;
    }

    public Flowable<List<String>> getAllMovieShow() {
        return movieShowDatabase.movieShowDao().getAllShowTimes();
    }

    // AsyncTask to insert a movie show in the background
    private static class InsertMovieShowAsyncTask extends AsyncTask<MovieShowEntity, Void, Void> {
        private MovieShowDao movieShowDao;

        private InsertMovieShowAsyncTask(MovieShowDao movieShowDao) {
            this.movieShowDao = movieShowDao;
        }

        @Override
        protected Void doInBackground(MovieShowEntity... movieShows) {
            movieShowDao.insertMovie(movieShows[0]);
            return null;
        }
    }

    private static class GetCountAsyncTask extends AsyncTask<Void, Void, Integer> {
        private MovieShowDao movieShowDao;

        private GetCountAsyncTask(MovieShowDao movieShowDao) {
            this.movieShowDao = movieShowDao;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return movieShowDao.getCount();
        }
    }

    private static class GetCinemaNameByIdAsyncTask extends AsyncTask<Long, Void, String> {
        private CinemaDao cinemaDao;

        private GetCinemaNameByIdAsyncTask(CinemaDao cinemaDao) {
            this.cinemaDao = cinemaDao;
        }

        @Override
        protected String doInBackground(Long... movieId) {
            return cinemaDao.getCinemaNameById(movieId[0]);
        }
    }
}
