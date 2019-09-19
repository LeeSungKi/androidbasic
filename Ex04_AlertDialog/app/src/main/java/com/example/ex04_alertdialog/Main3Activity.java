package com.example.ex04_alertdialog;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main3Activity extends AppCompatActivity {

    private int year;
    private int month;
    private int date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar cal = GregorianCalendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        date = cal.get(Calendar.DATE);


        final DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Toast.makeText(Main3Activity.this, i+"년 "+(i1+1)+"월 " + i2 + "일", Toast.LENGTH_SHORT).show();
            }
        };

        Button b = findViewById(R.id.btn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        Main3Activity.this,
                        R.style.Theme_AppCompat_Light_Dialog_Alert,
                        dateListener, year, month, date);
                dialog.setCancelable(false);
                dialog.show();

            }
        });


    } // onCreate()

}
