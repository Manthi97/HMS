package com.example.miniproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class ViewDrinks extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drinks);

        mRecyclerView=(RecyclerView) findViewById(R.id.recyclerView_Drink);
        new FirebaseDatabaseHelper().readDrinks(new FirebaseDatabaseHelper.DataStatusDrink() {
            @Override
            public void DataIsLoaded(List<Drink> drinks, List<String> keys) {
                new RecyclerView_Config_Drink().setConfig(mRecyclerView,ViewDrinks.this,drinks,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
