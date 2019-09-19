package com.example.ex16;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
    < SQLite >
    - 안드로이드 디바이스에 내장되어있는 경량화 DBMS
    - 일정 관리, 가계부, 다이어리 등의 어플 만들 때
    - SQLiteOpenHelper 클래스를 사용함

 */
public class MainActivity extends AppCompatActivity {

    private Button saveBtn;
    private Button listBtn;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveBtn = findViewById(R.id.save_btn);
        listBtn = findViewById(R.id.list_btn);
        editText = findViewById(R.id.edit_text);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = editText.getText().toString().trim();
                if(s.isEmpty()){
                    Toast.makeText(MainActivity.this, "내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // s를 DB에 저장 (insert)
                MyDBHelper dao = new MyDBHelper(MainActivity.this);
                dao.insert(s);
                editText.setText("");
            }
        });

        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });
    }
}
