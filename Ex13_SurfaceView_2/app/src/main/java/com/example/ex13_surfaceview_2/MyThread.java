package com.example.ex13_surfaceview_2;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class MyThread extends Thread {

    private MySurfaceView view;
    private SurfaceHolder holder;
    private static final String TAG = "my";
    private boolean on = false;
    public void finish(){
        on = false;
    }

    public MyThread(MySurfaceView surfaceView){
        view = surfaceView;
        holder = view.getHolder();
    }

    @Override
    public void run() {
        on = true;
        try {
            while(on){
                Thread.sleep(50);
                if(view.back1_y >= view.totalHeight){
                    view.back1_y *= -1;
                    continue;
                }
                if(view.back2_y >= view.totalWidth){
                    view.back2_y *= -1;
                    continue;
                }
                view.back1_y += 5;
                view.back2_y += 5;

                Canvas canvas = holder.lockCanvas();
                view.draw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
        } catch (Exception e){
            Log.e(TAG, e.getMessage());
        }
    }
}
