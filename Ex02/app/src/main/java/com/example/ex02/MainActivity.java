package com.example.ex02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // xml에 선언한 버튼을 객체화 하기
        editText = (EditText) findViewById(R.id.edit_text1);
        Button b = (Button) findViewById(R.id.btn1); // 해당 id에 부합하는 View가 없으면 null을 반환
        // R ==> res 폴더의 파일/디렉토리를 자바 클래스에서 편하게 사용할 수 있도록 만들어진 클래스
        // R.layout ==> res/layout 폴더
        // R.id ==> res의 모든 xml 파일에서 해당 아이디 값을 가진 View
        // R.drawables ==> res/drawable 폴더 ( png, jpg, gif 등의 이미지 파일, xml 파일 )

        // onClickListener 객체 생성
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editText.getText().toString();

                // Test01Activity 로 화면 전환
                Intent intent = new Intent(MainActivity.this, Test01Activity.class);

                            //  new Intent ( Context , Class )
                            //  new Intent ( 출발지 액티비티, 도착지 클래스 )
                            // Context ( Activity의 조상클래스 )

                intent.putExtra("name", name);
                intent.putExtra("user_id", 1235434534534L);
                startActivity(intent);
            }
        };
        b.setOnClickListener(clickListener);
    }
}

