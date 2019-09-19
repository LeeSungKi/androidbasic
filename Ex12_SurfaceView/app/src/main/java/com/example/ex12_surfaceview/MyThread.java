package com.example.ex12_surfaceview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;

public class MyThread extends Thread {

    // 이 쓰레드가 다룰 SurfaceView
    MySurfaceView surfaceView;

    // 위의 surfaceView를 뷰홀더 패턴으로 화면에 inflate해주는 Holder
    SurfaceHolder holder;

    int dir_x = 1;
    int dir_y = 1;

    MyThread(MySurfaceView s, SurfaceHolder h){
        surfaceView = s;
        holder = h;
    }

    MyThread(MySurfaceView s){
        surfaceView = s;
        holder = s.getHolder();
    }

    @Override
    public void run() {
        Canvas canvas;
        int imageWidth = MySurfaceView.bitmap != null ?
                            MySurfaceView.bitmap.getWidth() : 0;
        int imageHeight = MySurfaceView.bitmap != null ?
                MySurfaceView.bitmap.getWidth() : 0;
        try {
            while(true){
                Thread.sleep(5);
                int width = surfaceView.getWidth();
                int height = surfaceView.getHeight();

                Log.d("My", "x : " + surfaceView.img_x);
                Log.d("My", "width - imageWidth : " + (width - imageWidth));
                if(surfaceView.img_x >= (width - imageWidth) || surfaceView.img_x < 0){
                    dir_x *= -1;
                }


                if(surfaceView.img_y >= (height - imageHeight) || surfaceView.img_y < 0){
                    dir_y *= -1;
                }

                surfaceView.img_x += 5 * dir_x; // 5dp 추가
                surfaceView.img_y += 5 * dir_y;

                canvas = holder.lockCanvas(); // Canvas를 잠깐 정지하고, 그 Canvas를 return
                surfaceView.draw(canvas); // canvas 수정
                holder.unlockCanvasAndPost(canvas); // Canvas 다시 활성화하고 canvas를 post함
                // Canvas 수정 순서 : lockCanvas() --> draw(canvas) --> unlockCanvasAndPost()
            }
        } catch (Exception e){
            Log.e("MY", e.getMessage());
        }
    }
}
