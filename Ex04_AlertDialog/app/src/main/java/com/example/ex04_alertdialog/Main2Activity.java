package com.example.ex04_alertdialog;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// 자동 줄 정렬 : ctrl + alt + L

public class Main2Activity extends AppCompatActivity {

    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);

        final CharSequence[] items = {"RED", "BLUE", "GREEN", "MAGENTA", "GRAY"};
        final int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.GRAY};
        // Color.rgb(int r, int g, int b) ==> int
        // Color.argb(int alpha, int r, int g, int b) ==> int

        final AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.Theme_AppCompat_Light_Dialog_Alert);
        builder.setTitle("색상을 선택하세요.")
                .setSingleChoiceItems(items, 3, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        layout.setBackgroundColor(colors[i]);
                        dialogInterface.dismiss();
                    }
                })
//                .setMultiChoiceItems(R.array.colors, new boolean[]{true, true, false, false, true}, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
//                        Toast.makeText(Main2Activity.this, items[i]+(b?" 선택됨!":" 해제됨!"), Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setItems(items, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(Main2Activity.this, items[i]+" 선택됨!", Toast.LENGTH_SHORT).show();
//                    }
//                })
                .setCancelable(false)
                .setPositiveButton("예", null);

        Button b = findViewById(R.id.btn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.show();
            }
        });

    }
}
