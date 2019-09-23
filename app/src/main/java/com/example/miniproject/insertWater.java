package com.example.miniproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class insertWater extends AppCompatActivity {

    private TextView mDate_editText;
    private EditText mGlasses_editText;
    private Button btnView;
    private Button btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_water);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.water_date_editText);
        textViewDate.setText(currentDate);

        mDate_editText =  findViewById(R.id.water_date_editText);

        mGlasses_editText= (EditText)findViewById(R.id.water_glasses_editText);

        btnadd = (Button)findViewById(R.id.btn_add_water);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(mGlasses_editText.getText().toString())){
                    mGlasses_editText.setError("Enter Number of Glasses");
                    mGlasses_editText.requestFocus();}
                else{



                    Water water = new Water();
                    water.setDate(mDate_editText.getText().toString());
                    water.setGlasses(mGlasses_editText.getText().toString());
                    new FirebaseDatabaseHelper().addWater(water, new FirebaseDatabaseHelper.DataStatusWater() {
                        @Override
                        public void DataIsLoaded(List<Water> waters, List<String> keys) {

                        }

                        @Override
                        public void DataIsInserted() {
                            Toast.makeText(insertWater.this,"Inserted",Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void DataIsUpdated() {

                        }

                        @Override
                        public void DataIsDeleted() {

                        }
                    });

                }}

        });

        btnView = (Button)findViewById(R.id.btn_view_water);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenWaterListActivity();

            }
        });
    }
    public void OpenWaterListActivity(){
        Intent intent = new Intent(this,WaterListActivity.class);
        startActivity(intent);
    }
}
