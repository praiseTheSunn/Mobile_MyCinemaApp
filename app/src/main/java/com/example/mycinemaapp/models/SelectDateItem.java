package com.example.mycinemaapp.models;

import java.util.Date;

public class SelectDateItem {
    private String weekday;
    private int day;
    Date date;

    public SelectDateItem(String weekday, int day, Date date) {
        this.weekday = weekday;
        this.day = day;
        this.date = date;
    }

    public String getWeekday() {
        return weekday;
    }

    public int getDay() {
        return day;
    }

    public Date getDate() {
        return date;
    }
}
