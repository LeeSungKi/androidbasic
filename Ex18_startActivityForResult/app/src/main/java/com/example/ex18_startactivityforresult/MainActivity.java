package com.example.ex18_startactivityforresult;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    } // onCreate()

    // startActivityForResult()를 통해 다른 액티비티를 실행한 후,
    // 그 액티비티가 finish()되면 이쪽으로 다시 넘어오면서 자동으로 onActivityResult()가 호출됨
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // requestCode : startActivityForResult() 때 넣어주었던 requestCode
        // resultCode : 이전 액티비티의 실행 결과 (이전액티비티가 setResult()를 통해 저장한 정수)
        // Intent : 이전 액티비티가 전달한 Intent (여기에 extra 가 들어있음)
        if(requestCode == 1 && resultCode == RESULT_OK){
            btn.setText( data.getStringExtra("btn_tag") );
        }
    }
}
