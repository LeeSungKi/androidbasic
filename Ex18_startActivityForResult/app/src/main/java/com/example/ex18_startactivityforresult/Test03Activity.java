package com.example.ex18_startactivityforresult;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/*
    펜 색상 바꾸기

 */
public class Test03Activity extends AppCompatActivity implements View.OnClickListener{
    private View view;
    private Button btn;
    private static final int MY_REQUEST_CODE2 = 2;
    private static Paint paint = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test03);
        view = findViewById(R.id.view);
        btn = findViewById(R.id.button2);
        btn.setOnClickListener(this);

        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);

    }

    @Override
    public void onClick(View view) {
        startActivityForResult(new Intent(Test03Activity.this, Test02Activity.class), MY_REQUEST_CODE2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        int color = data.getIntExtra("color", 0);
        MyView.paint.setColor(color);
    }

}
