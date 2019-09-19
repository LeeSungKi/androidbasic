package com.example.ex06_handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
    < 멀티쓰레드 구현 방법 >
    1. extends Thread
    2. implements Runnable

    ==> 기존의 Java 스타일
        단점! 쓰레드 간의 데이터 전달이 힘들다.
             UI는 별로의 쓰레드에서 변경이 불가능하다.
             (메인 스트림 (액티비티)에서만 UI 변경 가능)

    3. Handler 를 사용 (==> UI 변경 시 사용)
    4. AsyncTask 를 사용 (==> 통신에서 사용)
 =======================================================

    Activity 내부에는 MessageQueue 라는 버퍼가 있음
    MessageQueue는 Handler가 관리하는 메모리이다.
    MessageQueue에 메시지가 들어오면, Handler는 이 메시지를 받아서 메인스레드에게 전달한다.


 */
public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button startBtn, stopBtn;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0){
                int rand = (int)(Math.random()*10);
                textView.setText( rand + "" );
                sendEmptyMessageDelayed(0, 1000); // 1밀리초
            }
            else {
                removeMessages(0);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textview);
        startBtn = findViewById(R.id.start_btn);
        stopBtn = findViewById(R.id.stop_btn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.sendEmptyMessage(0);
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.sendEmptyMessage(1);
            }
        });
    }
}
