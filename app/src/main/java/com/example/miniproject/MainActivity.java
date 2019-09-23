package com.example.miniproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btnfood);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInsertFood();
            }
        });


        button1 = (Button) findViewById(R.id.btndrinks);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrink1();
            }
        });

        button3=(Button)findViewById(R.id.btnweight);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightViewShow();
            }
        });

    }

    public void openInsertFood() {

        Intent intent = new Intent(this, InsertFood.class);
        startActivity(intent);
    }

    public void openinsertWater() {

        Intent intent = new Intent(this, insertWater.class);
        startActivity(intent);
    }

    public void openDrink1() {

        Intent intent = new Intent(this, Drink1.class);
        startActivity(intent);

    }
    public void  weightViewShow() {

        Intent intent=new Intent(this,weightViewShow.class);
        startActivity(intent);
    }
}
