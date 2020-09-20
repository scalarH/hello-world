package com.example.songpay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    Button uButton;
    Button oButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        uButton = (Button) findViewById(R.id.userlogin);
        oButton = (Button) findViewById(R.id.ownerlogin);

        uButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), URegisterActivity.class);
                startActivity(intent);
            }
        });

        oButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ORegisterActivity.class);
                startActivity(intent);
            }
        });
        }


    }
