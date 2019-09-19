package com.example.ex02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText join_id;
    private EditText join_password;
    private EditText join_email_1;
    private Spinner join_email_2;
    private RadioGroup join_gender;
    private CheckBox[] join_subs = new CheckBox[8];
    private Button join_confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        join_id = findViewById(R.id.join_id);
        join_password = findViewById(R.id.join_password);
        join_email_1 = findViewById(R.id.join_email_1);
        join_email_2 = findViewById(R.id.join_email_2);
        join_gender = findViewById(R.id.join_gender);
        join_subs[0] = findViewById(R.id.join_sub1);
        join_subs[1] = findViewById(R.id.join_sub2);
        join_subs[2] = findViewById(R.id.join_sub3);
        join_subs[3] = findViewById(R.id.join_sub4);
        join_subs[4] = findViewById(R.id.join_sub5);
        join_subs[5] = findViewById(R.id.join_sub6);
        join_subs[6] = findViewById(R.id.join_sub7);
        join_subs[7] = findViewById(R.id.join_sub8);
        join_confirm = findViewById(R.id.join_confirm);

        join_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String id = join_id.getText().toString().trim();
        if(id.isEmpty()){
            Toast.makeText(this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
            join_id.requestFocus(); // 커서 포커스 요청
            return;
        }

        String password = join_password.getText().toString().trim();
        if(password.isEmpty()){
            Toast.makeText(this, "패스워드를 입력하세요.", Toast.LENGTH_SHORT).show();
            join_password.requestFocus(); // 커서 포커스 요청
            return;
        }
        String email1 = join_email_1.getText().toString().trim();
        if(password.isEmpty()){
            Toast.makeText(this, "이메일 아이디를\n입력하세요.", Toast.LENGTH_SHORT).show();
            join_email_1.requestFocus(); // 커서 포커스 요청
            return;
        }

        String email2 = join_email_2.getSelectedItem().toString();

        ArrayList<String> subject_list = new ArrayList<>();
        for(CheckBox box : join_subs){
            if(box.isChecked()){
                subject_list.add( box.getText().toString() );
            }
        }

        String gender = ((RadioButton)findViewById(join_gender.getCheckedRadioButtonId())).getText().toString();

        Intent intent = new Intent(this, JoinResultActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("password", password);
        intent.putExtra("email1", email1);
        intent.putExtra("email2", email2);
        intent.putExtra("gender", gender);
        intent.putStringArrayListExtra("subjects", subject_list);
        startActivity(intent);
    }
}
