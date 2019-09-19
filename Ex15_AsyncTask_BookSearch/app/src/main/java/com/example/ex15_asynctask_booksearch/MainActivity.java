package com.example.ex15_asynctask_booksearch;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  TextView.OnEditorActionListener, View.OnClickListener{

    private static final String CLIENT_ID = "EYUYDlLJlVco_OknId7D";
    private static final String CLIENT_SECRET = "DtZmekfbZ_";
    private static final String BOOK_SEARCH_URL = "https://openapi.naver.com/v1/search/book.json";

    // query : 검색어
    // display : 5
    // start : 시작인덱스 (전체 검색 결과의 n번째 결과부터)
    // sort : 정렬 기준 (판매량 : count, 유사도 : sim, 출간일 : date)

    private Button search_btn; // 검색 버튼
    private EditText search_edit; // 검색 입력창
    private ListView list_view; // 검색결과 목록창
    private TextView add_btn; // 추가 로드 버튼
    private BaseAdapter adapter;
    private ProgressBar progressBar;

    private int idx = 1;

    private ArrayList<BookVo> list = new ArrayList<>();

    class BookVo {
        String title;
        String img_path;
        String author;
        String price;
        String description;
        String link; // 상세 보기 링크
        public String toString(){
            return "제목 : " + title + ", 가격 : " + price + "원, 저자 : " + author;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_btn = findViewById(R.id.search_btn);
        search_edit = findViewById(R.id.search_edit_text);
        list_view = findViewById(R.id.list_view);
        add_btn = findViewById(R.id.search_tab);

        search_btn.setOnClickListener(this);
        search_edit.setOnEditorActionListener(this);
        add_btn.setOnClickListener(this);

        adapter = new MyAdapter();
        list_view.setAdapter(adapter);

        progressBar = findViewById( R.id.progress_circular );
    }

    class BookAsyncTask extends AsyncTask<Integer, Integer, Void>{

        String query;
        boolean check = true;

        @Override
        protected void onProgressUpdate(Integer... gauge) {
            progressBar.setProgress(gauge[0]);
        }

        @Override
        protected void onPreExecute() {
            // 검색어 가져오기 (query)
            query = search_edit.getText().toString().trim();
            if(query.isEmpty()) {
                Toast.makeText(MainActivity.this, "검색어를 입력하세요.", Toast.LENGTH_SHORT).show();
                check = false;
                return;
            }
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Integer... n) {
            // 네이버에 요청하기
            if(!check){
                return null;
            }

            try {
                publishProgress(10);
                /// 요청 준비
                query = URLEncoder.encode(query, "UTF-8");
                String url = BOOK_SEARCH_URL + "?query=" + query + "&start=" + n[0] +"&display=5&sort=count";
                URL request_url = new URL(url);
                HttpURLConnection con = (HttpURLConnection)request_url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("X-naver-client-id", CLIENT_ID);
                con.setRequestProperty("X-naver-client-secret", CLIENT_SECRET);
                /// 요청 준비 끝

                publishProgress(20);
                /// 요청 보냄 --> 응답오길 기다림 --> 응답코드(int)를 리턴
                int resultCode = con.getResponseCode();

                /// 요청 결과가 정상이 아니면 바로 작업 종료
                if(resultCode != 200){
                    check = false;
                    return null;
                }


                publishProgress(30);
                /// 응답 메시지의 바디(content)를 String으로 변환하기
                BufferedReader br = null;
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder responseStr = new StringBuilder();
                String tmp;
                while( (tmp = br.readLine()) != null){
                    responseStr.append(tmp);
                }
                br.close();
                Log.d("MY", responseStr.toString());

                String rawStr = responseStr.toString();
                JSONObject rtObj = new JSONObject(rawStr);

                publishProgress(70);
                JSONArray itemArr = (JSONArray)rtObj.get("items");
                for(int i = 0; i < itemArr.length(); ++i){
                    JSONObject o = (JSONObject)itemArr.get(i);
                    BookVo vo = new BookVo();
                    vo.title = (String)o.get("title");
                    vo.author = (String)o.get("author");
                    vo.description = (String)o.get("description");
                    vo.img_path = (String)o.get("image");
                    vo.price = (String)o.get("price");
                    vo.link = (String)o.get("link");


                    vo.title = vo.title.replace("<b>", "");
                    vo.title = vo.title.replace("</b>", "");

                    list.add(vo);
                    Log.d("VO", vo.toString());
                }
                publishProgress(100);
                idx += 5;
            } catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // 요청 결과를 ListView에 띄우기
            //           ------------------> 어댑터가 하는 일임.
            // 따라서 어댑터에게 데이터 상태가 변경되었음을 알림
            progressBar.setVisibility(View.GONE);
            if(!check){
                return;
            }
            adapter.notifyDataSetChanged();
        }
    }







    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_btn: // 상단 '검색' 버튼
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                list.clear();
                new BookAsyncTask().execute(1);
                break;
            case R.id.search_tab: // 하단 '추가로드' 버튼
                new BookAsyncTask().execute(idx);
                break;
            case -1: // 책 정보들

                final int idx = ((MyAdapter.ViewHolder)(view.getTag())).idx;

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
                builder.setCancelable(false);
                builder.setMessage(list.get( idx ).description);
                builder.setPositiveButton("상세보기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 상세보기 페이지로 (브라우저 실행)
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(list.get( idx ).link));
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("닫기", null);
                builder.show();
        }
    }

    class MyAdapter extends BaseAdapter{

        class ViewHolder {
            ImageView imageView; // 표지 view
            TextView title; // 책제목 view
            TextView content; // 책 저자, 가격, 출판사 view
/*new*/     int idx; // 이 아이템의 인덱스
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final ViewHolder holder;

            if(view == null){
                holder = new ViewHolder();
                view = View.inflate(MainActivity.this, R.layout.book, null);
/*new*/         view.setClickable(true); // layout 전체에 이벤트가 감지되도록
/*new*/         view.setOnClickListener(MainActivity.this); // layout 전체에 onClick 리스너 등록
                holder.title = view.findViewById(R.id.book_title);
                holder.imageView = view.findViewById(R.id.book_image);
                holder.content = view.findViewById(R.id.book_content);
/*new*/         holder.idx = i;
                view.setTag(holder);
            }
            else {
                holder = (ViewHolder) view.getTag();
            }

            String s = new DecimalFormat("###,###").format(Integer.parseInt(list.get(i).price));
            holder.title.setText( list.get(i).title );
            holder.content.setText( "저자 : " + list.get(i).author +"\n가격 : " + s);
            Glide
                    .with(MainActivity.this)
                    .asBitmap()
                    .load(list.get(i).img_path)
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            holder.imageView.setImageBitmap(resource);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                        }
                    });
            return view;
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
/*new*/      return -1;
        }
    }

    ////////////////////////////////////////////
    // TextView.OnEditorActionListener
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (EditorInfo.IME_ACTION_SEARCH == actionId) {
            search_btn.performClick(); // 검색 버튼 클릭해라
        } else {
            return false;
        }
        return true;
    }
}
