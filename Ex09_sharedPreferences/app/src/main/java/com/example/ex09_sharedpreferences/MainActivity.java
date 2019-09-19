package com.example.ex09_sharedpreferences;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn);
        editText = findViewById(R.id.edit);

        // 액티비티 실행 시 사전에 저장해둔 문장이 있으면 꺼내기
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String data = pref.getString("ex09_data", ""); // 두번째 파라미터 : default value

        editText.setText(data);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 영구저장 시키기 (SharedPreferences)
                SharedPreferences pref =
                        PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                // 데이터를 쓰려면 Editor를 사용
                SharedPreferences.Editor editor = pref.edit();

                // 에디터를 사용하여 데이터 저장 ( Key - Value )
                editor.putString("ex09_data", editText.getText().toString());

                // 저장
                editor.apply(); // editor.commit()

                Toast.makeText(MainActivity.this, "저장 완료!", Toast.LENGTH_SHORT).show();
            }
        });
    } // onCreate()
}
