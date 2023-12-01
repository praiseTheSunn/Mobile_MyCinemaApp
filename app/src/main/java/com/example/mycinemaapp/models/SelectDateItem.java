package com.example.mycinemaapp.models;

public class SelectDateItem {
    private String weekday;
    private int day;

    public SelectDateItem(String weekday, int day) {
        this.weekday = weekday;
        this.day = day;
    }

    public String getWeekday() {
        return weekday;
    }

    public int getDay() {
        return day;
    }
}
