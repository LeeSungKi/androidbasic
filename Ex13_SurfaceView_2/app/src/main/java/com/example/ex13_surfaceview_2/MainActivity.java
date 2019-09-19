package com.example.ex13_surfaceview_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(new MySurfaceView(this));
    }
}
