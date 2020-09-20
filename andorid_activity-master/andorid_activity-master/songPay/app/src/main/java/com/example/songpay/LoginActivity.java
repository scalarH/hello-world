package com.example.songpay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.songpay.util.NetworkTask;

import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btncheck;

        EditText idText = (EditText) findViewById(R.id.idText);
        EditText passText = (EditText) findViewById(R.id.passwordText);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        TextView registerButton = (TextView) findViewById(R.id.registerButton);
        btncheck = (Button)findViewById(R.id.btncheck);


        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(loginIntent);
            }

        });

        // 계좌확인
        btncheck.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Log.i("msg","Brrrrrrrrrrrrrrrr");

                String url = "http://172.30.1.33:3001/coocon/checkAccount";//여기 민지아이피로바꾸기 포트는 그대로

                JSONObject object = new JSONObject();

                try{
                    object.put("u_id","bono4");
                }
                catch (Exception e){
                    Log.e("error",e.getMessage());
                }
                Log.i("msg",object.toString());

                // AsyncTask를 통해 HttpURLConnection 수행.
                NetworkTask networkTask = new NetworkTask(url, object,"POST");
                networkTask.execute();
            }
        });

    }
}
