package com.example.miniproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class weightlistview extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weightlistview);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview_weight);
        new FirebaseDatabaseHelper().readweight(new FirebaseDatabaseHelper.DataStatusWeight() {
            @Override
            public void DataIsLoaded(List<Weight> weights, List<String> keys) {
                new RecyclerView_Config_Weight().setConfig(mRecyclerView,weightlistview.this,weights,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataisUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

    }

}