package com.example.songpay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class URegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uregister);

        EditText idText = (EditText) findViewById(R.id.emailText);
        EditText passText = (EditText) findViewById(R.id.passwordText);
        EditText nameText =(EditText) findViewById(R.id.nameText);

        Button registerButton = (Button) findViewById(R.id.resgister);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(registerIntent);
            }
        });





    }
}
