package com.example.ex18_startactivityforresult;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Test02Activity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test02);

        findViewById(R.id.color_red).setOnClickListener(this);
        findViewById(R.id.color_orange).setOnClickListener(this);
        findViewById(R.id.color_green).setOnClickListener(this);
        findViewById(R.id.color_blue).setOnClickListener(this);
        findViewById(R.id.color_violet).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        Drawable background = view.getBackground();
        int colorCode = ((ColorDrawable) background).getColor();
        intent.putExtra("color", colorCode);
        setResult(RESULT_OK, intent);
        finish();
    }
}
