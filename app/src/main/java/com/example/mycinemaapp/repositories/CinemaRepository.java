package com.example.mycinemaapp.repositories;

import android.content.Context;
import android.os.AsyncTask;

import com.example.mycinemaapp.daos.CinemaDao;
import com.example.mycinemaapp.daos.MovieShowDao;
import com.example.mycinemaapp.databases.CinemaDatabase;
import com.example.mycinemaapp.databases.MovieShowDatabase;
import com.example.mycinemaapp.models.CinemaEntity;
import com.example.mycinemaapp.models.MovieShowEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class CinemaRepository {
    private CinemaDatabase cinemaDatabase;

    public CinemaRepository(Context context) {
        cinemaDatabase = CinemaDatabase.getInstance(context);
    }

    public void insertCinema(CinemaEntity cinemaEntity) {
        new CinemaRepository.InsertCinemaAsyncTask(cinemaDatabase.cinemaDao()).execute(cinemaEntity);
    }

    public List<String> getAllCinemaNames() {
        return cinemaDatabase.cinemaDao().getAllCinemaNames().blockingFirst();
    }

    public List<Long> getAllCinemaIds() {
        return cinemaDatabase.cinemaDao().getAllCinemaIds().blockingFirst();
    }


    private static class InsertCinemaAsyncTask extends AsyncTask<CinemaEntity, Void, Void> {
        private CinemaDao cinemaDao;

        private InsertCinemaAsyncTask(CinemaDao cinemaDao) {
            this.cinemaDao = cinemaDao;
        }

        @Override
        protected Void doInBackground(CinemaEntity... cinemaEntity) {
            cinemaDao.insertMovie(cinemaEntity[0]);
            return null;
        }
    }
}
