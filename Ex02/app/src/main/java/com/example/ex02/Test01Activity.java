package com.example.ex02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Test01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test01);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        long userId = intent.getLongExtra("user_id", 0L);
        TextView textView = (TextView)findViewById(R.id.text_view1);
        textView.setText("환영합니다.\n" + name + "님!");
    }
}
