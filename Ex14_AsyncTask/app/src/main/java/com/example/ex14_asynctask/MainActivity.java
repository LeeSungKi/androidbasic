package com.example.ex14_asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/*
    AsyncTask 클래스 : 멀티 쓰레드 구현할 때 사용하는 클래스
     ==> Handler 보다 가독성이 좋아서 자주 사용
     ==> 네트워크 통신 (http) 시 주로 사용함

    ** 네트워크 통신은 메인스레드에서 할 수 없음
       (백그라운드에서 이루어져야 함)
        ---------> 멀티 스레드

    ** 네트워크 통신은 INTERNET 접근 권한이 필요함
        manifest.xml에서 설정


 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String CLIENT_ID = "VLLczzgK2LaCY0EVmZHW";
    private static final String CLIENT_SECRET = "UL6bdog2Pw";

    private static final String NAVER_URL =  "https://openapi.naver.com/v1/search/book.xml";

    private Button button;
    private TextView textView;

    // private Reader reader; // 지우고
    private StringBuilder response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn);
        textView = findViewById(R.id.text_view);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                new NaverAsyncTask().execute(1);

                break;
        }
    }

    // 네이버에 요청을 보낼 쓰레드 클래스
    class NaverAsyncTask extends AsyncTask<Integer, Void, Boolean>{
        @Override
        protected Boolean doInBackground(Integer... integers) {
            boolean result = false;
            try {
                String text = URLEncoder.encode("해리포터", "UTF-8");
                String apiURL = NAVER_URL + "?query=" + text // 검색어는 해리포터
                        + "&start=" + integers[0] // n번째 검색 결과부터
                        + "&display=5" // 5개만
                        + "&sort=count"; // 판매량 순

                //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
                con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);
                int responseCode = con.getResponseCode(); // 요청보낸 뒤에 응답코드를 수신 (200, 404, 500)
                BufferedReader br;
                if(responseCode==200) { // 정상 호출
                    result = true;
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                                                                // ==> 응답메시지와 연결되어있는 스트림

                } else {  // 에러 발생
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                Log.d("MY", response.toString());
            } catch (Exception e) {
                Log.e("MY", e.getMessage());
            }
            return result;
        } // doInBackground()

        @Override
        protected void onPostExecute(Boolean bool) {

            StringBuilder stringBuilder = new StringBuilder("---- 검색 결과 ----\n");

            if(!bool) {
                textView.setText("검색 결과 없음");
                return;
            }

            try {
                // 응답받은 xml을 parsing 하기
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();

                parser.setInput(new StringReader(response.toString()));
                // 네이버로부터 응답받은 xml 을 parsing할 parser

                int parserEvent = parser.getEventType();
                Log.d("My", "event : " + parserEvent);
                while(parserEvent != XmlPullParser.END_DOCUMENT) {
                    // END_DOCUMENT가 아닐 동안 반복 ==> element 를 읽어들임 <> </>
                    if(parserEvent == XmlPullParser.START_TAG){
                        // 현재 보고있는 텍스트가 시작 태그니?
                        String tagName = parser.getName(); // 태그 이름 가져옴

                        switch (tagName){
                            case "title":
                                stringBuilder.append("제목 : " + parser.nextText() + "\n");
                                break;
                            case "price":
                                stringBuilder.append("가격 : " + parser.nextText() + "원\n");
                                break;
                            case "image":
                                stringBuilder.append("책표지 위치 : " + parser.nextText() +"\n");
                                break;
                            case "author":
                                stringBuilder.append("저자 : " + parser.nextText() +"\n\n");
                        } // switch
                    } // if
                    parserEvent = parser.next();
                    // 다음 element로 넘어감 + 그 element의 이벤트 상황(int)을 return
                } // while

                String message = stringBuilder.toString();
                message = message.replace("<b>","");
                message = message.replace("</b>","");

                textView.setText( message );



            } catch(Exception e){
                Log.e("MY", e.getMessage());
            }
        }
    } // NaverAsyncTask 클래스
}
