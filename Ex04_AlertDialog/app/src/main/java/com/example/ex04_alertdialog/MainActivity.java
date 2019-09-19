package com.example.ex04_alertdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Dialog 창 띄우기
                /*AlertDialog.Builder builder = new AlertDialog.Builder(
                        MainActivity.this, android.R.style.Theme_DeviceDefault_Dialog_Alert);
                builder.setTitle("안녕하세요~!");
                builder.setMessage("ㅎㅇㅎㅇㅎㅇㅎㅇ");
                builder.show(); */

                /*
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        MainActivity.this, android.R.style.Theme_DeviceDefault_Dialog_Alert);
                builder.setTitle("안녕하세요~!")
                       .setMessage("ㅎㅇㅎㅇㅎㅇㅎㅇ")
                        .setCancelable(false) // 알림창 외부를 터치했을 때 창 종료되는 것을 방지
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "Positive!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "Negative!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("중립", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "Neutral!", Toast.LENGTH_SHORT).show();
                            }
                        })
                       .show();
                       */

                // "종료하시겠습니까?" ==> 예 --> 앱종료 ( MainActivity.this.finish(); )
                //                     아니오 --> 대화창만 닫음 ( 아무것도 안해도 됨 )
                // Activity.finish() : 현재 액티비티 종료 ( 메인액티비티라면 어플 종료 )
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        MainActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
                builder.setCancelable(true)
                        .setTitle("삭제하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MainActivity.this.finish();
                            }
                        })
                        .show();

            }
        });
    }
}
