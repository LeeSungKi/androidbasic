package com.example.ex18_startactivityforresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.result_btn);
        button.setOnClickListener(this);
    } // onCreate()

    @Override
    public void onClick(View view) {
        String s = editText.getText().toString().trim();

        Intent intent = new Intent(); // 텍스트를 전달할 인텐트 생성
                                      // 액->액 데이터 전달할 때도 Intent를 사용
        intent.putExtra("btn_tag", s); // 데이터 저장
        setResult(RESULT_OK, intent); // 액티비티의 실행결과 세팅 (이 액티비티를 끝낸 후 실행될 액티비티에 전달함)
                              // Activity.RESULT_OK : 액티비티 정상 종료
        finish(); // 이 액티비티 종료
    }
}
