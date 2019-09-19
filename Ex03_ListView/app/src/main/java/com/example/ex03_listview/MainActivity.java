package com.example.ex03_listview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private  int[] images = {
            R.drawable.ex03_1,
            R.drawable.ex03_2,
            R.drawable.ex03_3,
            R.drawable.ex03_4,
            R.drawable.ex03_5,
            R.drawable.ex03_6,
            R.drawable.ex03_7,
            R.drawable.ex03_8
    };
    private ArrayList<ItemVo> list;
    private BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            MyHolder holder;
            if(view == null){

                view = View.inflate(MainActivity.this, R.layout.item, null);

                holder = new MyHolder();
                holder.imageView = view.findViewById(R.id.item_image);
                holder.title = view.findViewById(R.id.test01_title);
                holder.content = view.findViewById(R.id.test01_content);

                view.setTag(holder);
            }
            else {
                holder = (MyHolder)view.getTag();
            }
            holder.imageView.setImageResource( list.get(i).getImage() );
            holder.title.setText( list.get(i).getTitle());
            holder.content.setText( list.get(i).getContent());

            return view;
        }

        class MyHolder {
            ImageView imageView;
            TextView title;
            TextView content;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
//        list.add(new ItemVo2("111", "111ㅋㅋㅋㅋㅋㅋㅋ\nㅎㅎㅎㅎㅎㅎㅎㅎ"));
//        list.add(new ItemVo2("222", "222ㅋㅋㅋㅋㅋㅋㅋ\nㅎㅎㅎㅎㅎㅎㅎㅎ"));
//        list.add(new ItemVo2("333", "333ㅋㅋㅋㅋㅋㅋㅋ\nㅎㅎㅎㅎㅎㅎㅎㅎ"));
//        list.add(new ItemVo2("444", "444ㅋㅋㅋㅋㅋㅋㅋ\nㅎㅎㅎㅎㅎㅎㅎㅎ"));
//        list.add(new ItemVo2("555", "555ㅋㅋㅋㅋㅋㅋㅋ\nㅎㅎㅎㅎㅎㅎㅎㅎ"));

        ListView listView = findViewById(R.id.test01_listview);
        listView.setAdapter(adapter);

        final EditText titleEdit = findViewById(R.id.test01_input_title);
        final EditText contentEdit = findViewById(R.id.test01_input_content);
        Button addButton = findViewById(R.id.test01_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEdit.getText().toString().trim();
                String content = contentEdit.getText().toString().trim();
                if(title.isEmpty() || content.isEmpty()){
                    Toast.makeText(MainActivity.this,"제목 혹은 내용을 입력하세요." , Toast.LENGTH_SHORT).show();
                    return;
                }
                list.add(new ItemVo(title, content,images[ (int) (Math.random()*8) ] ));

                adapter.notifyDataSetChanged();
                // adapter에게 DataSet(아이템 셋)이 업데이트 되었음을 알림

                titleEdit.setText("");
                contentEdit.setText("");

                //가상키보드를 화면에서 제거
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });


    }
}
