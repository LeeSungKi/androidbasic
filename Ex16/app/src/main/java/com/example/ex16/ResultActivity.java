package com.example.ex16;

import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        MyDBHelper dao = new MyDBHelper(ResultActivity.this);
        String s = dao.getList();

        ((TextView)findViewById(R.id.result_textview)).setText(s);

    }
}
