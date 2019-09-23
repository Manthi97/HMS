package com.example.miniproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class WaterDetailsActivity extends AppCompatActivity {

    private EditText mDate_editText;
    private EditText mGlasses_editText;

    private Button btnUpdate;
    private Button btnDelete;

    private  String key;
    private String Date;
    private String Glass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_details);

        key = getIntent().getStringExtra("key");
        Date = getIntent().getStringExtra("Date");
        Glass = getIntent().getStringExtra("Glasses");

        mDate_editText = (EditText) findViewById(R.id.Update_water_date_editText);
        mDate_editText.setText(Date);
        mGlasses_editText = (EditText)findViewById(R.id.Update_water_glasses_editText);
        mGlasses_editText.setText(Glass);

        btnUpdate = (Button) findViewById(R.id.btn_Update_water);
        btnDelete = (Button)findViewById(R.id.btn_Delete_water);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Water water = new Water();
                water.setDate(mDate_editText.getText().toString());
                water.setGlasses(mGlasses_editText.getText().toString());

                new FirebaseDatabaseHelper().updateWater(key, water, new FirebaseDatabaseHelper.DataStatusWater() {
                    @Override
                    public void DataIsLoaded(List<Water> waters, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(WaterDetailsActivity.this, "Updated", Toast.LENGTH_LONG).show();
                        finish();
                        return;

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelper().DeleteWater(key, new FirebaseDatabaseHelper.DataStatusWater() {
                    @Override
                    public void DataIsLoaded(List<Water> waters, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(WaterDetailsActivity.this,"Deleted",Toast.LENGTH_LONG).show();
                        finish();return;

                    }
                });
            }
        });
    }


}
