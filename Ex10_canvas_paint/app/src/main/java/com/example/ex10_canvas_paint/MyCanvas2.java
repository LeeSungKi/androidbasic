package com.example.ex10_canvas_paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyCanvas2 extends View {

    private static final Paint paint = new Paint();
    private static final Path path = new Path();
    private int x;//사용자가 드래그한 마지막 x축 값
    private int y;//사용자가 드래그한 마지막 y축 값

    public MyCanvas2(Context context){
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.DKGRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4.0f);
        canvas.drawPath(path,paint);
    }

    @Override //이 뷰 ( MyCanvas2 전체)를 터치했을 때 호출될 콜백 함수
    public boolean onTouchEvent(MotionEvent event) {
        // 현재 터치된 x,y좌표를 얻어옴
        x = (int) event.getX();
        y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN://화면에 터치한 거임?
                Log.d("a","x"+x+"y:"+y+"(ACTION_DOWN)");
                path.moveTo(x,y);//path를 시작할 지점을 정한다.
                break;
            case MotionEvent.ACTION_MOVE://
                Log.d("b","x"+x+"y:"+y+"(ACTION_MOVE)");
                path.lineTo(x,y);//이전 x,y 와 현재 x,y을 이은 선을 그린다.
                break;
        }

        invalidate();//canvas2 의 함수 (view에서 상속받음) (View 를 갱신하기 위한 onDraw()를 호출함)

        return true;// false 를 리턴하면 onTouchEvent가 연속으로 실행되지 않음(1번만 실행됨)
    }
}
