package com.example.mycinemaapp.viewModels;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycinemaapp.models.MovieEntity;
import com.example.mycinemaapp.models.MovieShowEntity;
import com.example.mycinemaapp.models.Seat;
import com.example.mycinemaapp.repositories.CinemaRepository;
import com.example.mycinemaapp.repositories.MovieRepository;
import com.example.mycinemaapp.repositories.MovieShowRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SeatBookingViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    public MovieShowEntity movieShow;
    private String movieImagePath = null;

    public List<Seat> seatList;
    public int numberOfColumns;



    private MutableLiveData<Integer> selectedSeat = new MutableLiveData<>(0);

    public MutableLiveData<Integer> getSelectedSeat() {
        return selectedSeat;
    }

    public void increaseSelectedSeat() {
        try {
            int val = selectedSeat.getValue();
            selectedSeat.setValue(val + 1);
        } catch(NullPointerException e) {
            Log.e("SeatBooking", "selectedSeat is null " + e);
        }
    }

    public void decreaseSelectedSeat() {
        try {
            int val = selectedSeat.getValue();
            selectedSeat.setValue(val - 1);
        } catch(NullPointerException e) {
            Log.e("SeatBooking", "selectedSeat is null " + e);
        }
    }

    private Pair<List<String>, List<Long>> allMoviesAndIds = null;
    private Pair<List<String>, List<Date>> dateStringsAndDates = null;
    private List<String> dateList = null;
    private Pair<List<String>, List<Long>> cinemaNamesAndIds = null;

    public Pair<List<String>, List<Long>> getAllMovieNamesAndIds(Context context) {
        if (allMoviesAndIds != null) {
            return allMoviesAndIds;
        }

        MovieRepository movieRepository = new MovieRepository(context);

        List<MovieEntity> movieEntities = movieRepository.getAllMovies().blockingFirst();
        List<String> movieNames = new ArrayList<>();
        List<Long> movieIds = new ArrayList<>();
        for (int i = 0; i < movieEntities.size(); i++) {
            movieNames.add(movieEntities.get(i).getTitle());
            movieIds.add(movieEntities.get(i).getId());
        }

        allMoviesAndIds = new Pair<>(movieNames, movieIds);

        return allMoviesAndIds;
    }

    public Pair<List<String>, List<Date>> getAllDates() {
        if (dateStringsAndDates != null)
            return dateStringsAndDates;

        List<Date> dateList = new ArrayList<>();
        List<String> dateStringList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        // Add dates for the next 7 days
        for (int i = 0; i < 7; i++) {
            Date date = calendar.getTime();
            String abbreviatedWeekday = new SimpleDateFormat("EEE", Locale.ENGLISH).format(date);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String ymdDate = sdf.format(date);

            String dateString = abbreviatedWeekday + "," + String.valueOf(day);
            dateStringList.add(dateString);
            dateList.add(java.sql.Date.valueOf(ymdDate));

            // Move to the next day
            calendar.add(Calendar.DAY_OF_MONTH, 1);

        }
        dateStringsAndDates = new Pair<>(dateStringList, dateList);
        return dateStringsAndDates;
    }

    public List<String> getAllShowTimes(Context context) {
        if (dateList != null) {
            return dateList;
        }
        MovieShowRepository movieShowRepository = new MovieShowRepository(context);
        dateList = movieShowRepository.getAllMovieShow().blockingFirst();

        return dateList;
    }

    public Pair<List<String>, List<Long>> getAllCinemaNamesAndIds(Context context) {
        if (cinemaNamesAndIds != null)
            return cinemaNamesAndIds;
        CinemaRepository cinemaRepo = new CinemaRepository(context);

        List<String> cinemaNames = cinemaRepo.getAllCinemaNames();
        List<Long> cinemaIds = cinemaRepo.getAllCinemaIds();

        cinemaNamesAndIds = new Pair<>(cinemaNames, cinemaIds);
        return cinemaNamesAndIds;
    }

    public String getMovieImagePath() {
        return movieImagePath;
    }

    public void setMovieImagePath(String movieImagePath) {
        this.movieImagePath = movieImagePath;

    }

}