package com.example.ex04_alertdialog;

import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.Theme_AppCompat_Light_Dialog_Alert);

        String html = "<h1 style = \"color:#FF0000\">Hi~</h1>";
        Spanned h;

        if(Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N){
            // Nouget 버전 미만이니?
            h = Html.fromHtml(html);
        }
        else {
            h = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY); // API 24 미만은 이 코드 실행 불가
        }

        builder.setTitle("이곳은 타이틀")
                .setMessage(h)
                .setPositiveButton("예", null)
                .show();
    }
}
