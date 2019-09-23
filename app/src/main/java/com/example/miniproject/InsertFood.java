package com.example.miniproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class InsertFood extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button button;
    private EditText mDate_CalendarView;
    private EditText mMeal_Items_EditText;
    private Spinner mFood_Type_Spinner;
    private Button mAdd_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_food);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.Date_Viewdate);
        textViewDate.setText(currentDate);

        mDate_CalendarView = (EditText)findViewById(R.id.Date_Viewdate);
        mMeal_Items_EditText = (EditText)findViewById(R.id.Meal_Items_Txt);
        mFood_Type_Spinner = (Spinner)findViewById(R.id.Meals);
        mAdd_btn = (Button)findViewById(R.id.btnAdd);

        mAdd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(mMeal_Items_EditText.getText())){
                    mMeal_Items_EditText.setError("Enter your Meals");
                    mMeal_Items_EditText.requestFocus(); }
                    else{
                Food food =new Food();
                food.setDate(mDate_CalendarView.getText().toString());
                food.setMeal_Items(mMeal_Items_EditText.getText().toString());
                food.setMeal_Type(mFood_Type_Spinner.getSelectedItem().toString());
                new FirebaseDatabaseHelper().addfood(food, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Food> foods, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                        Toast.makeText(InsertFood.this,"Information Inserted",Toast.LENGTH_SHORT).show();

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

        button = (Button) findViewById(R.id.btnViewMeals);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewMeals();
            }
        });



        Spinner spinner =findViewById(R.id.Meals);
        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this,R.array.Meals, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String  text =parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text, Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void openViewMeals(){

        Intent intent = new Intent(this, ViewMeals.class);
        startActivity(intent);
    }
}
