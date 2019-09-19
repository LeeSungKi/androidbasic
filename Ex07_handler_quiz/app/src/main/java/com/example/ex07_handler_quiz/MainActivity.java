package com.example.ex07_handler_quiz;

import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button start_button;
    private TextView number_textview;
    private TextView records_textview;

    private static final int STATUS_STOP = 0; // 현재 상태 정지
    private static final int STATUS_RUN = 1; // 현재 상태 진행 중
    private int status = STATUS_STOP; // 현재 상태가 무엇? (0이면 정지, 1이면 진행중)
    private ArrayList<RecordVo> list = new ArrayList<>();

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == STATUS_RUN) {
                int random = (int) (Math.random() * 99) + 1; // 1 ~ 100
                number_textview.setText(random + "");
                sendEmptyMessageDelayed(STATUS_RUN, 1);
            }
            else {

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.result_dialog);

                TextView result_textview = dialog.findViewById(R.id.result_textview);
                final EditText name_edittext = dialog.findViewById(R.id.name_edittext);
                Button save_button = dialog.findViewById(R.id.save_button);

                // 점수 : XX
                result_textview.setText( "점수 : " + number_textview.getText() );

                // 저장 버튼이 터치되었을 때
                save_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userName = name_edittext.getText().toString().trim();
                        if(userName.isEmpty()){
                            Toast.makeText(MainActivity.this, "이름을 입력하세요.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        RecordVo vo = new RecordVo(userName, Integer.parseInt(number_textview.getText().toString()));
                        list.add(vo);
                        Collections.sort(list);

                        String str = "---- 명예의 전당 ----\n";
                        for(int i = 0; i < list.size(); ++i){
                            RecordVo vo1 = list.get(i);
                            str += (i+1) + "등. " + vo1.getName() + " "
                                    + vo1.getScore() + "점 "
                                    + vo1.getDate() + "\n";
                        }
                        records_textview.setText(str);
                        dialog.dismiss();
                    }
                });
                dialog.show();
                removeMessages(STATUS_RUN); // 메시지큐의 STATUS_RUN으로 표시된 모든 메시지 삭제
            } // else
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start_button = findViewById(R.id.start_button);
        number_textview = findViewById(R.id.number_textview);
        records_textview = findViewById(R.id.records_textview);

        start_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (status){
            case STATUS_STOP: // 현재 상태 멈춤?
                status = STATUS_RUN; // 진행중 상태로 변경
                // 게임 시작
                start_button.setText("STOP");
                break;

            case STATUS_RUN: // 현재 상태 진행중?
                status = STATUS_STOP; // 정지로 상태 변경
                // 게임 종료
                start_button.setText("START");
                break;
        }
        handler.sendEmptyMessage(status);
    }
}
