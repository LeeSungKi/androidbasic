package com.example.ex12_surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    int img_x; // 이미지의 x 좌표를 넣을 것임 ( 조금씩 증가 ==> 오른쪽으로 조금씩 이동 )
    int img_y;
    MyThread thread; // MySurfaceView를 제어할 쓰레드 (지웠다가 다시 그리기를 담당하는 쓰레드)
    static Bitmap bitmap; // 이미지를 객체화 할 때 사용

    public MySurfaceView(Context context){
        super(context);

        // Holder(Callee) 에게 Caller는 this임을 등록
        getHolder().addCallback(this);

        // SurfaceView의 크기 너비 지정
        getHolder().setFixedSize(getWidth(), getHeight());

        // 쓰레드 생성
        thread = new MyThread(this, getHolder());
    } // MySurfaceView()

    /*
        SurfaceHolder.Callback 인터페이스
        추상메서드 3개
     */

    ///////////////////////////////////////////////////
    // SurfaceHolder.Callback
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.start(); // 쓰레드 시작
        Log.d("MY", "SurfaceView 생성됨!");
    }

    ///////////////////////////////////////////////////
    // SurfaceHolder.Callback
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("MY", "SurfaceView 변경됨!");
    }

    ///////////////////////////////////////////////////
    // SurfaceHolder.Callback
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread = null; // 쓰레드 종료 (GC 컬렉터에 의해 객체가 소멸되도록)
        Log.d("MY", "SurfaceView 종료됨!");
    }

    ///////////////////////////////////////////////////
    // View
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Log.d("MY", "draw 호출됨!");

        // image.jpg 를 Bitmap 객체화
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image);

        int resizedWidth = (int)(bitmap.getWidth() * 0.3);
                        // = 200;  // (200dp)
        int resizedHeight = (int)(bitmap.getHeight() * 0.3);

        // 변경된 사이즈로 bitmap 재생성
        bitmap = Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);

        canvas.drawBitmap(bitmap, img_x, img_y, null);
    }
}










