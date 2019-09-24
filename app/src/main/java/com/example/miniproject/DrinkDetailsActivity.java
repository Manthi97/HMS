package com.example.miniproject;

import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class DrinkDetailsActivity extends AppCompatActivity {

    private EditText mDbrand_editText;
    private EditText mDdate_editText;
    private EditText mDname_editText;
    private EditText mDtime_editText;

    private Button btnUpdate;
    private Button btnDelete;

    private String key;
    private String Dbrand;
    private String Ddate;
    private String Dname;
    private String Dtime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_details);

        key = getIntent().getStringExtra("key");
        Dbrand =getIntent().getStringExtra("Dbrand");
        Ddate = getIntent().getStringExtra("Ddate");
        Dtime = getIntent().getStringExtra("Dtime");
        Dname = getIntent().getStringExtra("Dname");

        mDbrand_editText=(EditText)findViewById(R.id.Dbrand_Update_editTxt);
        mDbrand_editText.setText(Dbrand);
        mDdate_editText = (EditText)findViewById(R.id.Ddate_Update_editTxt);
        mDdate_editText.setText(Ddate);
        mDname_editText = (EditText)findViewById(R.id.Dname_Update_editTxt);
        mDname_editText.setText(Dname);
        mDtime_editText = (EditText) findViewById(R.id.Dtime_Update_editTxt);
        mDtime_editText.setText(Dtime);

        btnUpdate = (Button)findViewById(R.id.btn_Update_drink);
        btnDelete = (Button)findViewById(R.id.btn_Delete_Drink);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drink drink = new Drink();
                drink.setDbrand(mDbrand_editText.getText().toString());
                drink.setDdate(mDdate_editText.getText().toString());
                drink.setDname(mDname_editText.getText().toString());
                drink.setDtime(mDtime_editText.getText().toString());

                new FirebaseDatabaseHelper().updateDrink(key, drink, new FirebaseDatabaseHelper.DataStatusDrink() {
                    @Override
                    public void DataIsLoaded(List<Drink> drinks, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DrinkDetailsActivity.this);
                        builder.setMessage("Are you want to sure Update Data").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DrinkDetailsActivity.this,"Successfully Updated",Toast.LENGTH_LONG).show();
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

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelper().deleteDrink(key, new FirebaseDatabaseHelper.DataStatusDrink() {
                    @Override
                    public void DataIsLoaded(List<Drink> drinks, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DrinkDetailsActivity.this);
                        builder.setMessage("Are you want to sure Delete Data").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DrinkDetailsActivity.this,"Successfully Deleted",Toast.LENGTH_LONG).show();
                            }
                        }).setNegativeButton("Cancel",null);

                        AlertDialog alert = builder.create();
                        alert.show();

                    }
                });
            }
        });

    }


}
