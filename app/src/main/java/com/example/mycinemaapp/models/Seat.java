package com.example.mycinemaapp.models;

public class Seat {
    private int seatId;
    private int seatRow;
    private int seatCol;
    private String seatState;

    // Constructor
    public Seat(int seatId, int seatRow, int seatCol, String seatState) {
        this.seatId = seatId;
        this.seatRow = seatRow;
        this.seatCol = seatCol;
        this.seatState = seatState;
    }

    // Getter methods
    public int getSeatId() {
        return seatId;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public int getSeatCol() {
        return seatCol;
    }

    public String getSeatState() {
        return seatState;
    }

    public void setSeatState(String state) {
        seatState = state;
    }
}
