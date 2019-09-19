package com.example.ex04_alertdialog;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(Main5Activity.this);
                dialog.setContentView(R.layout.rating); // 이 Dialog의 View는 res/layout/rating.xml 로 설정

                Button confirm_btn = dialog.findViewById(R.id.confirm_btn);
                final RatingBar rating_bar = dialog.findViewById(R.id.rating);

                rating_bar.setStepSize(1f); // 증가 간격 ( 0.5f ==> 반 칸씩 늘릴 수 있음 )
                confirm_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        float score = rating_bar.getRating();
                        Toast.makeText(Main5Activity.this, score + "점", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();

            } // onClick()
        }); // onClickListener()
    } // onCreate()
}
