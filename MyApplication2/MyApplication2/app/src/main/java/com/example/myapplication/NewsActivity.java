package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    //이걸 왜넣어줘야하는거야?걍데이터던져주는거임?
    //={"1","2"}이런식으로값넣어주면 값이어댑터로넘어감
    private String[] mDataset = {"1","2"};
    //RequestQueue 를초기화
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerView = findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);




        //RequestQueue 를초기화 queue 에담아서 들어온대로내보내는거
       queue = Volley.newRequestQueue(this);
       getNews();
        //1.화면이 로딘 -> 뉴스 정보를 받아온다. ========
        //2.정보 -> 어댑터 넘겨준다
        //3.어뎁터 -> 셋팅
    }
    //1.화면이 로딘 -> 뉴스 정보를 받아온다.
    public void getNews(){

        // Instantiate the RequestQueue.
//url 이부분에 api 주소 key값넣어줌
        String url ="https://newsapi.org/v2/top-headlines?country=kr&apiKey=50b9e579161d4b798a53e179496bb487";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //Log.d("NEWS",response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                           JSONArray arrayArticles = jsonObject.getJSONArray("articles");

                            //response -> NewsData Class 분류
                            final List<NewsData> news = new ArrayList<>();

                           for (int i =0, j=arrayArticles.length(); i < j; i++){
                              JSONObject object = arrayArticles.getJSONObject(i);
                              //확인해보자
                               Log.d("NEWS",object.toString());

                               NewsData newsData = new NewsData();
                               newsData.setTitle(object.getString("title"));
                               newsData.setUrlToImage(object.getString("urlToImage"));
                               newsData.setDescription(object.getString("description"));
                               news.add(newsData);
                           }

                            // specify an adapter (see also next example)
                            //데이터넘겨줌어뎁터로
                          /*  mAdapter = new MyAdapter(news, NewsActivity.this, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Object obj = v.getTag();
                                    if(obj != null) {
                                        int position =(int)obj;
                                        ((MyAdapter)mAdapter).getNews(position).getDescription();
                                       //좀더 생각해보자
                                        *//* Intent intent = new Intent(,);*//*
                                        //1. 본문만넘기기
                                        //2. 전체다넘기기
                                            //2-1. 본문넘기듯이 하나씩다넘기기
                                            //2-2. 한번에다넘기기??어케함??
                                        *//*startActivity(intent);*//*

                                    }
                                }
                            });
                            */recyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                            }
        });

        // Add the request to the RequestQueue 에 요청추가한다
        queue.add(stringRequest);

    }
}