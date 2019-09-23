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

public class WeightMain extends AppCompatActivity {
    private EditText mWeight_amount_editTxt;
    private Button button1;
    private Button button2;
    private EditText mDate_CalendarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_main);

        mWeight_amount_editTxt = (EditText) findViewById(R.id.weight_amount_edittext);
        mDate_CalendarView = (EditText) findViewById(R.id.Weight_date);
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.Weight_date);
        textViewDate.setText(currentDate);

        mDate_CalendarView = (EditText)findViewById(R.id.Weight_date);

        button1 = (Button)findViewById(R.id.btnwieghtview);
        button2 = (Button)findViewById(R.id.btn2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openweightlistview();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(mWeight_amount_editTxt.getText())){
                    mWeight_amount_editTxt.setError("Enter your weight");
                    mWeight_amount_editTxt.requestFocus(); }

                else{
                    Weight weight = new Weight();
                    weight.setWeightAmount(mWeight_amount_editTxt.getText().toString());
                    weight.setWeightDate(mDate_CalendarView.getText().toString());
                    new FirebaseDatabaseHelper().addweight(weight, new FirebaseDatabaseHelper.DataStatusWeight() {
                        @Override
                        public void DataIsLoaded(List<Weight> weights, List<String> keys) {

                        }

                        @Override
                        public void DataIsInserted() {
                            Toast.makeText(WeightMain.this,"Successfully inserted",Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void DataisUpdated() {

                        }

                        @Override
                        public void DataIsDeleted() {

                        }
                    });
                }}
        });

    }

    public void openweightlistview(){

        Intent intent = new Intent(this,weightlistview.class);
        startActivity(intent);
    }
}
