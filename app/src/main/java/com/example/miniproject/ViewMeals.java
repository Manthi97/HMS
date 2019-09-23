package com.example.miniproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewMeals extends AppCompatActivity {

//    private Button update_button;

private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_meals);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_food);
        new FirebaseDatabaseHelper().readfood(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Food> foods, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView,ViewMeals.this,
                        foods,keys);
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

//        update_button =(Button)findViewById(R.id.btnEdit);
//        update_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openDialogbox();
//            }
//        });


    }

//    public void openDialogbox(){
//
//        Intent intent = new Intent(this,UpdateMeal.class);
//        startActivity(intent);
//    }
}
