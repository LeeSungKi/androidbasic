package com.example.ex02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JoinResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_result);

        Intent intent = getIntent();

        String message = "ID : " + intent.getStringExtra("id") + "\n"
                        + "PW : " + intent.getStringExtra("password") + "\n"
                        + "EMAIL : " + intent.getStringExtra("email1") + "@"
                        + intent.getStringExtra("email2") + '\n'
                        + "GENDER : " + intent.getStringExtra("gender") + "\n"
                        + "SUBJECTS : " +  intent.getStringArrayListExtra("subjects").toString();

        ((TextView)findViewById(R.id.join_result_textview)).setText(message);
    }
}
