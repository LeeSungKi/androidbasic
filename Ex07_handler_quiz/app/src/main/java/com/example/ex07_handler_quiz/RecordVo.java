package com.example.ex07_handler_quiz;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class RecordVo implements Comparable<RecordVo>{
    private String name;
    private String date;
    private int score;

    private static SimpleDateFormat sdf =
            new SimpleDateFormat("[MM/dd HH:ss]", Locale.KOREA);

    RecordVo(String name, int score){
        setName(name);
        setScore(score);
        setDate();
    }

    @Override
    public int compareTo(RecordVo recordVo) {
        // this를 recordVo 보다 앞에 두고 싶으면 --> 음수 리턴
        //                     뒤에 두고 싶으면 --> 양수 리턴
        //                     같은 취급을 하고 싶으면(동등성) --> 0 리턴
        return recordVo.score - this.score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate() {
        this.date = sdf.format(System.currentTimeMillis());
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
