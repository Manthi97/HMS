package com.example.miniproject;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class delete_update extends AppCompatActivity {

    private EditText mweightAmount_editText;
    private EditText mweightDate_editText;
    private Button btndelete;
    private Button btnupdate;


    private String key;
    private String WeightAmount;
    private String WeightDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_update);

        key = getIntent().getStringExtra("key");
        WeightAmount = getIntent().getStringExtra("WeightAmount");
        WeightDate = getIntent().getStringExtra("WeightDate");

        mweightAmount_editText =(EditText) findViewById(R.id.updateweight_editTxt);
        mweightAmount_editText.setText(WeightAmount);
        mweightDate_editText = (EditText) findViewById(R.id.dateupdateweight_editTxt);
        mweightDate_editText.setText(WeightDate);
        btndelete = (Button)findViewById(R.id.btndelete);
        btnupdate = (Button)findViewById(R.id.btnupdate);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Weight weight = new Weight();
                weight.setWeightAmount(mweightAmount_editText.getText().toString());
                weight.setWeightDate(mweightDate_editText.getText().toString());

                new FirebaseDatabaseHelper().updateWeight(key, weight, new FirebaseDatabaseHelper.DataStatusWeight() {
                    @Override
                    public void DataIsLoaded(List<Weight> weights, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataisUpdated() {

                        AlertDialog.Builder builder = new AlertDialog.Builder(delete_update.this);
                        builder.setMessage("Are you want to sure Update Data").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(delete_update.this,"Successfully Updated",Toast.LENGTH_LONG).show();
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



        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new FirebaseDatabaseHelper().deleteWeight(key, new FirebaseDatabaseHelper.DataStatusWeight() {
                    @Override
                    public void DataIsLoaded(List<Weight> weights, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {


                    }
                    @Override
                    public void DataisUpdated() {

                    }


                    @Override
                    public void DataIsDeleted() {

                AlertDialog.Builder builder = new AlertDialog.Builder(delete_update.this);
                builder.setMessage("Are you want to sure Delete Data").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(delete_update.this,"Successfully Deleted",Toast.LENGTH_LONG).show();
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
