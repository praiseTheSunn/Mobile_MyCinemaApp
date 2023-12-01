package com.example.mycinemaapp.models;

import java.util.List;

public class SelectCinemaTimeItem {
    private String cinemaName;
    private List<String> showTimes;

    public SelectCinemaTimeItem(String cinemaName, List<String> showTimes) {
        this.cinemaName = cinemaName;
        this.showTimes = showTimes;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public List<String> getShowTimes() {
        return showTimes;
    }
}
