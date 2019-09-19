package com.example.ex04_alertdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    TextView textView;
    CharSequence[] genders = {"남성", "여성"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textView = findViewById(R.id.quiz_textview);

        findViewById(R.id.quiz_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        QuizActivity.this, R.style.Theme_AppCompat_Light_Dialog_Alert);
                builder.setSingleChoiceItems(genders, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        textView.setText("당신의 성별 : " + genders[i]);
                        dialogInterface.dismiss();
                    }
                })
                .setCancelable(false)
                .show();

            } // onClick()
        });
    } // onCreate()
}
