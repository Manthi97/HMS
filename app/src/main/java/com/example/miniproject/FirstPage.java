package com.example.miniproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class FirstPage extends AppCompatActivity {

        private Button start;

        Timer timer;

        private EditText uName;
        private Button sign;
        private EditText pwd;
        private Button login;
        private TextView info;
        private int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

            //Buttons
            //sign = (Button) findViewById(btnLogSignIn);
            login = (Button)findViewById(R.id.btnLogIn);
            //Edit Texts
            uName = (EditText)findViewById(R.id.etUserName);
            pwd = (EditText)findViewById(R.id.etpassword);
            info = (TextView)findViewById(R.id.atmpts) ;

            info.setText("Remaining no of attempts  : 3");
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validate(uName.getText().toString(), pwd.getText().toString());
                }
            });

            sign = (Button) findViewById(R.id.btnLogSignIn);
            sign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SignIn();
                }
            });

        }
        public void SignIn() {

            Intent intent = new Intent(this, SignIn.class);
            startActivity(intent);
        }

        public void validate(String userName, String userPassword){

            if((userName.equals ("Admin")) && (userPassword.equals("1234"))){
                Intent intent = new Intent(FirstPage.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(FirstPage.this,"Login Successfully!",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(FirstPage.this,"UserName or Password Incorrect",Toast.LENGTH_SHORT).show();
                counter--;
                info.setText("Remaining no of attempts  :  " + String.valueOf(counter));
                if (counter == 0) {
                    login.setEnabled(false);
                }


            }


        }



}
