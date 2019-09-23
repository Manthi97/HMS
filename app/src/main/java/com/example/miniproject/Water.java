package com.example.miniproject;

public class Water {

    private String Date;
    private String Glasses;

    public Water() {
    }

    public Water(String date, String glasses) {
        Date = date;
        Glasses = glasses;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getGlasses() {
        return Glasses;
    }

    public void setGlasses(String glasses) {
        Glasses = glasses;
    }
}
