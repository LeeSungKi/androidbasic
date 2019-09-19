package com.example.ex10_canvas_paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class MyCanvas extends View {
    public MyCanvas(Context context){
        super(context);

    }

    @Override
    protected void onDraw(Canvas canvas) {//화면에 해당 뷰가 그려질때
        super.onDraw(canvas);
        //canvas 에 그림을 그려줄 붓 역활
        Paint paint = new Paint();

        //
        paint.setColor(Color.rgb(255,0,0));

        canvas.drawRect(100,200,300,250,paint);

        //paint.setStyle(Paint.Style.FILL);//내부 채우기
        paint.setStyle((Paint.Style.STROKE));//실선그리기
        paint.setStrokeWidth(30);
        canvas.drawCircle(200 ,120,50,paint);
        canvas.drawCircle(200 ,330,50,paint);

        Rect rect = new Rect();
        rect.set(250,550,300,650);
        paint.setColor(Color.argb(50,160,50,50));
        canvas.drawRect(rect,paint);

        rect.set(150,650,400,700);
        paint.setColor(Color.argb(50,160,50,50));
        canvas.drawRect(rect,paint);

    }
}
