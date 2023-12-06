package com.example.mycinemaapp.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.mycinemaapp.models.MovieEntity;
import com.example.mycinemaapp.models.MovieShowEntity;
import com.example.mycinemaapp.repositories.MovieRepository;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

// MovieViewModel.java
public class MoviePageViewModel extends ViewModel {
    public MovieShowEntity movieShow = new MovieShowEntity(null, "", -1, -1, -1);
    private String movieImagePath = null;


    private MaterialCardView previousSelectTimeCardView;

    public String getMovieImagePath() {
        return movieImagePath;
    }

    public void setMovieImagePath(String movieImagePath) {
        this.movieImagePath = movieImagePath;
    }

    public MaterialCardView getPreviousSelectTimeCardView() {
        return previousSelectTimeCardView;
    }

    public void setPreviousSelectTimeCardView(MaterialCardView previousSelectTimeCardView) {
        this.previousSelectTimeCardView = previousSelectTimeCardView;
    }

}
