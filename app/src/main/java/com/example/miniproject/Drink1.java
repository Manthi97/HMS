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

public class Drink1 extends AppCompatActivity {
    private TextView mDate_CalendarView;
    private Button btnView;
    private Button btnAdd;
    private EditText mDbrand_edit;
    private EditText mDname_edit;
    private EditText mDtime_edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink1);


        mDbrand_edit =(EditText)findViewById(R.id.Dbrand_editTxt);
        mDname_edit =(EditText)findViewById(R.id.Dname_editTxt);
        mDtime_edit = (EditText) findViewById(R.id.Dtime_editTxt);

        btnAdd = (Button)findViewById(R.id.btn_add_drink);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(mDbrand_edit.getText().toString())){
                    mDbrand_edit.setError("Enter Brand Name");
                    mDbrand_edit.requestFocus();}else{



                    if(TextUtils.isEmpty(mDname_edit.getText().toString())){
                        mDname_edit.setError("Enter Name");
                        mDname_edit.requestFocus();}else{



                        if(TextUtils.isEmpty(mDtime_edit.getText().toString())){
                            mDtime_edit.setError("Enter Number of Glasses");
                            mDtime_edit.requestFocus();}else{



                            Drink drink = new Drink();
                            drink.setDbrand(mDbrand_edit.getText().toString());
                            drink.setDdate(mDate_CalendarView.getText().toString());
                            drink.setDname(mDname_edit.getText().toString());
                            drink.setDtime(mDtime_edit.getText().toString());
                            new FirebaseDatabaseHelper().addDrink(drink, new FirebaseDatabaseHelper.DataStatusDrink() {
                                @Override
                                public void DataIsLoaded(List<Drink> drinks, List<String> keys) {

                                }

                                @Override
                                public void DataIsInserted() {
                                    Toast.makeText(Drink1.this,"Inserted",Toast.LENGTH_LONG).show();

                                }

                                @Override
                                public void DataIsUpdated() {

                                }

                                @Override
                                public void DataIsDeleted() {

                                }
                            });
                        }}}}
        });

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.Ddate_editTxt);
        textViewDate.setText(currentDate);

        mDate_CalendarView = (TextView)findViewById(R.id.Ddate_editTxt);

        btnView=(Button)findViewById(R.id.btn_view);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewDrinks();
            }
        });
    }

    public void openViewDrinks(){
        Intent intent = new Intent(this, ViewDrinks.class);
        startActivity(intent);
    }
}
