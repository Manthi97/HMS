package com.example.miniproject;

public class Weight {

    private String WeightAmount;
    private String WeightDate;

    public Weight() {
    }

    public Weight(String weightAmount, String weightDate) {
        WeightAmount = weightAmount;
        WeightDate = weightDate;
    }

    public String getWeightAmount() {
        return WeightAmount;
    }

    public void setWeightAmount(String weightAmount) {
        WeightAmount = weightAmount;
    }

    public String getWeightDate() {
        return WeightDate;
    }

    public void setWeightDate(String weightDate) {
        WeightDate = weightDate;
    }
}
