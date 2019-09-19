package com.example.ex16;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import java.util.ArrayList;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Update;
import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class MoneybookActivity extends AppCompatActivity {


    private static final String TAG = "my";

    @BindView(R.id.main_pager_view) // butterknife
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moneybook);

        ButterKnife.bind(this);
    }


    @Entity(tableName = "moneybook") // Room
    @NoArgsConstructor //lombok
    @Getter //lombok
    @Setter //lombok
    @ToString //lombok
    class Vo {
        public static final int CODE_INCOME = 0;
        public static final int CODE_EXPENDITURE = 1;

        @PrimaryKey
        @NonNull
        private int id;
        private String info;
        private int code;
        private int year;
        private int month;
        private int date;
    }

    @Dao // room
    public interface  MoneybookDao {
        @Insert
        void insertItems(Vo vo);

        @Delete
        void deleteItems(int no);

        @Update
        void updateItems(int no, Vo vo);

        @Query("SELECT * FROM moneybook")
        ArrayList<Vo> selectAllItems();

        @Query("SELECT * FROM moneybook WHERE id = :id")
        ArrayList<Vo> selectAllItems(int id);

    }
    class MoneyDao extends SQLiteOpenHelper {
        private static final String FILE = "ex16.db";
        MoneyDao(){
            super(MoneybookActivity.this, FILE, null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d(TAG, "ex16.db onCreate()");
            String sql = "CREATE TABLE moneybook ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "code INTEGER, "
                        + "info TEXT, "
                        + "year INTEGER, "
                        + "month INTEGER, "
                        + "date INTEGER "
                        +")";
            sqLiteDatabase.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        }
    }

}
