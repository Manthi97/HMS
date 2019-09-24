package com.example.miniproject;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FoodUpdate extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText mDate_EditTxt;
    private EditText mMealItems_EditTxt;
    private Spinner mMeal_Type_Spinner;

    private Button mUpdate_btn;
    private Button mDelete_btn;

    private String key;
    private String date;
    private String meal_items;
    private String meal_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_update);

        key =getIntent().getStringExtra("key");
        date = getIntent().getStringExtra("Date");
        meal_items=getIntent().getStringExtra("Meal_Items");
        meal_type =getIntent().getStringExtra("Meal_Type");

        mDate_EditTxt = (EditText) findViewById(R.id.Date_UpdateEditTxt);
        mDate_EditTxt.setText(date);
        mMealItems_EditTxt = (EditText) findViewById(R.id.UpMeal_Items_Txt);
        mMealItems_EditTxt.setText(meal_items);
        mMeal_Type_Spinner = (Spinner)findViewById(R.id.Meals);
        mMeal_Type_Spinner.setSelection(getIndex_SpinnerItem(mMeal_Type_Spinner,meal_type));

        mUpdate_btn = (Button) findViewById(R.id.btnUpdateFood);
        mDelete_btn = (Button) findViewById(R.id.btnDeleteFood);

        mUpdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Food food = new  Food();
                food.setDate(mDate_EditTxt.getText().toString());
                food.setMeal_Items(mMealItems_EditTxt.getText().toString());
                food.setMeal_Type(mMeal_Type_Spinner.getSelectedItem().toString());

                new FirebaseDatabaseHelper().updateFood(key, food, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Food> foods, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FoodUpdate.this);
                        builder.setMessage("Are you want to sure Update Data").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(FoodUpdate.this,"Successfully Updated",Toast.LENGTH_LONG).show();
                            }
                        }).setNegativeButton("Cancel",null);

                        AlertDialog alert = builder.create();
                        alert.show();

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });
        mDelete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelper().deleteFood(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Food> foods, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FoodUpdate.this);
                        builder.setMessage("Are you want to sure Delete Data").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(FoodUpdate.this,"Successfully Deleted",Toast.LENGTH_LONG).show();
                            }
                        }).setNegativeButton("Cancel",null);

                        AlertDialog alert = builder.create();
                        alert.show();
                    }


                });
            }
        });

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.Date_UpdateEditTxt);
        textViewDate.setText(currentDate);

        Spinner spinner =findViewById(R.id.Meals);
        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this,R.array.Meals, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private int getIndex_SpinnerItem(Spinner spinner, String items){
        int index = 0;
        for (int i = 0; i< spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(items)){
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String  text =parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text, Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
