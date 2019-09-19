package com.example.ex16;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(Context context){
        super(context, "ex16.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // ex16.db 최초 생성할 때 호출됨
        sqLiteDatabase.execSQL("" +
                "CREATE TABLE tmp (" +
                "num INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "content TEXT, " +
                "date TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // 버전이 i에서 i1으로 변경되었을 때 호출되는 메서드
    }

    public boolean insert(String content){
        String date = new SimpleDateFormat("YYYY-MM-dd\nHH:ss:mm", Locale.KOREA)
                        .format(System.currentTimeMillis());
        String sql = "INSERT INTO tmp VALUES(null, '" + content + "', '" + date + "');";

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        //db.rawQuery("INSERT INTO tmp VALUES(null, ?, ?)", new String[]{content, date});
        db.close(); // commit
        return true;
    }

    public boolean delete(){
        return false;
    }
    public String getList(){
        String result = "";
        String sql = "SELECT * FROM tmp ORDER BY date DESC";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            result += cursor.getInt(0) + ". " + cursor.getString(1) + "\n" +
                      "[" + cursor.getString(2) + "]\n";

        }
        cursor.close();
        db.close();
        return result;
    }
}
