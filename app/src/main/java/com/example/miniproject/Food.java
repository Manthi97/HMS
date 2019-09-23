package com.example.miniproject;

public class Food {

    private String Date;
    private String Meal_Items;
    private String Meal_Type;

    public Food() {
    }

    public Food(String date, String meal_Items, String meal_Type) {
        Date = date;
        Meal_Items = meal_Items;
        Meal_Type = meal_Type;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getMeal_Items() {
        return Meal_Items;
    }

    public void setMeal_Items(String meal_Items) {
        Meal_Items = meal_Items;
    }

    public String getMeal_Type() {
        return Meal_Type;
    }

    public void setMeal_Type(String meal_Type) {
        Meal_Type = meal_Type;
    }
}
