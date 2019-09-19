package com.example.ex13_surfaceview_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    private SurfaceHolder holder;
    private MyThread thread;

    private Bitmap background_img1;
    private Bitmap background_img2;
    private Bitmap unit;
    private static final String TAG = "my";
    Context context;

    int unit_x, unit_y; // 비행기 x, y 좌표 값

    int back1_y, back2_y; // 배경 이미지 2개의 각각 y 좌표값

    int totalWidth;
    int totalHeight;

    public MySurfaceView(Context context){
        super(context);
        this.context = context;

        holder = getHolder();
        holder.addCallback(this);
        thread = new MyThread(this);

        background_img1 = BitmapFactory.decodeResource(getResources(), R.drawable.ex03_space);
        background_img2 = BitmapFactory.decodeResource(getResources(), R.drawable.ex03_space);



        unit = BitmapFactory.decodeResource(getResources(), R.drawable.ex13_gunship);


        Log.d(TAG, "unit X : " + unit_x + ", unit Y : " + unit_y);



    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);

        totalHeight = dm.heightPixels; // 디바이스 창의 높이 (픽셀단위)
        totalWidth = dm.widthPixels;   // 디바이스 창의 너비 (픽셀단위)

        // 이미지(배경)의 사이즈를 윈도우에 맞게 설정
        background_img1 = Bitmap.createScaledBitmap(background_img1, totalWidth, totalHeight, false);
        background_img2 = Bitmap.createScaledBitmap(background_img2, totalWidth, totalHeight, false);

        back1_y = 0;
        back2_y = -totalHeight;

        unit_x = (totalWidth - unit.getWidth())/2; // 전체너비/2 - 비행선너비/2
        unit_y = totalHeight - 300;

        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.finish();
        thread = null;
    }

    // 배경 2개가 번갈아가면서 화면에 나옴

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawBitmap(background_img1, 0, back1_y, null);
        canvas.drawBitmap(background_img2, 0, back2_y, null);
        canvas.drawBitmap(unit, unit_x, unit_y, null);
        Log.d(TAG, "unit X : " + unit_x + ", unit Y : " + unit_y);
    }

    // Surview가 터치되었을 때
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int user_x = (int)event.getX();
        int user_y = (int)event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN: // 최초 터치
                Rect r = new Rect();

                // r.set(left, top, right, bottom)
                // left : unit_x
                r.set(unit_x, unit_y, unit_x+unit.getWidth(), unit_y+unit.getHeight());
                if(!r.contains(user_x, user_y)){
                    return false;
                }
            case MotionEvent.ACTION_MOVE: // 누른 채로 이동
                unit_x = user_x - unit.getWidth()/2;
                unit_y = user_y - unit.getHeight()/2;
        }
        return true;
    }
    /*public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG+"2", "onTouchEvent()");
        // return true; // ACTION_DOWN(터치) : 호출됨 ACTION_MOVE(드래그) : 호출됨
        return false; // ACTION_DOWN(터치) : 호출됨 ACTION_MOVE(드래그) : 호출 안됨
    }*/
}












