package com.example.miniproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class WaterListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_list);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView_water);
        new FirebaseDatabaseHelper().readWater(new FirebaseDatabaseHelper.DataStatusWater() {
            @Override
            public void DataIsLoaded(List<Water> waters, List<String> keys) {
                new RecyclerView_Config_Water().setConfig(mRecyclerView,WaterListActivity.this,waters,keys);
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
