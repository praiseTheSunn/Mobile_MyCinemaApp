package com.example.mycinemaapp.models;

import java.util.List;

public class SelectCinemaTimeItem {
    private Long cinemaId;
    private String cinemaName;
    private List<String> showTimes;

    public SelectCinemaTimeItem(Long cinemaId, String cinemaName, List<String> showTimes) {
        this.cinemaId = cinemaId;
        this.cinemaName = cinemaName;
        this.showTimes = showTimes;
    }

    public Long getCinemaId() {
        return cinemaId;
    }
    public String getCinemaName() {
        return cinemaName;
    }

    public List<String> getShowTimes() {
        return showTimes;
    }
}
