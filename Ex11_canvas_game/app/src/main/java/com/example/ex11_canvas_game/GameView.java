package com.example.ex11_canvas_game;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;


public class GameView extends View {

    private static final int PAUSE = 0; // 대기 상태
    private static final int PLAY = 1; // 게임 진행 중 (사용자가 도형 터치 중)
    private static final int DELAY = 1500; // 도형 생성 중

    private ArrayList<ShapeVo> shapeList = new ArrayList<>(); // 현재까지 생성되었던 모든 도형을 모아둠

    private int status; // 현재 상태

    private Context context; // MainActivity

    private static final Paint paint = new Paint();

    private int lastCorrectIndex = 0;

    class ShapeVo {
        final static int RECT = 0;
        final static int CIRCLE = 1;
        final static int TRIANGLE = 2;

        int shape; // 0, 1, 2 중 1개를 저장
        int color;
        Rect rect;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            addNewShape(); // 새 도형 추가
            status = PLAY;
            lastCorrectIndex = 0;
            invalidate();
            String title = "stage : " + shapeList.size();
            ((MainActivity) context).setTitle(title);
        }
    };


    public GameView(Context context) {
        super(context);
        this.context = context;
        handler.sendEmptyMessageDelayed(0, DELAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

                // 바탕색 검정색으로
                canvas.drawColor(Color.DKGRAY);

                if (status == PAUSE) {
                    return;
                }

                for (int i = 0; i < shapeList.size(); ++i) {
                    ShapeVo vo = shapeList.get(i);
                    paint.setColor(vo.color);

                        Rect r = vo.rect;
                        switch (vo.shape) {
                            case ShapeVo.CIRCLE:
                                canvas.drawCircle(
                                        (float) (r.left + r.width() / 2.0),
                                        (float) (r.top + r.height() / 2.0),
                                        (float) (r.width() / 2), paint);
                                break;
                            case ShapeVo.RECT:
                                canvas.drawRect(r, paint);
                                break;
                            case ShapeVo.TRIANGLE:
                                Path path = new Path();
                                path.moveTo((float) (r.left + r.width() / 2.0), r.top);
                                path.lineTo(r.left, r.bottom);
                                path.lineTo(r.right, r.bottom);
                                canvas.drawPath(path, paint);
                                break;
            } // switch
        }// for
    } // onDraw()

    private void addNewShape() {
        ShapeVo vo = new ShapeVo();

        // 크기 지정
        vo.rect = new Rect();
        int size = (int) (Math.random() * 101) + 50; // 50 ~ 150

        re:
        while (true) {
            // 사각형의 4변 위치 지정
            vo.rect.left = (int) (Math.random() * (getWidth() - size)); // 0 ~ (뷰 전체 너비 - 사각형 너비)
            vo.rect.right = vo.rect.left + size;
            vo.rect.top = (int) (Math.random() * (getHeight() - size));
            vo.rect.bottom = vo.rect.top + size;

            // 다른 도형과 겹치니?
            for (int j = 0; j < shapeList.size(); ++j) {
                if (vo.rect.intersect(shapeList.get(j).rect)) {
                    continue re;
                }
            }
            break;
        }

        // 도형 설정 (원, 사각형, 삼각형 랜덤 선택)
        vo.shape = (int) (Math.random() * 3);

        // 색상 설정
        switch ((int) (Math.random() * 5)) {
            case 0:
                vo.color = Color.RED;
                break;
            case 1:
                vo.color = Color.BLUE;
                break;
            case 2:
                vo.color = Color.CYAN;
                break;
            case 3:
                vo.color = Color.WHITE;
                break;
            case 4:
                vo.color = Color.YELLOW;
                break;
        }
        shapeList.add(vo);
    } // addNewShape()

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() != MotionEvent.ACTION_DOWN) {
            return true;
        }
        int select = 0;
        select = findShapeIndex(event.getX(), event.getY());

        if(select == -1){ // 아무 도형도 터치하지 않았을 경우
            return true;
        }

        if(select == shapeList.size()){ // 마지막 도형을 선택했을 경우
            status = PAUSE;
            invalidate(); // 화면 지웠다가 onDraw() 실행
            handler.sendEmptyMessageDelayed(0, DELAY);
            return true;
        }
        if(select != -2){
            return true;
        }
        // 잘못된 도형을 선택했을 경우
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setCancelable(false)
                .setMessage("restart?")
                .setTitle("GAME OVER")
                .setPositiveButton("RESTART", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 게임 처음부터 시작
                        shapeList.clear(); // 리스트 안의 모든 도형 삭제
                        status = PAUSE;
                        invalidate();
                        handler.sendEmptyMessageDelayed(0, DELAY);
                    }
                })
                .setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((MainActivity)context).finish(); // 액티비티 종료
                    }
                })
                .show();
        return false;
    } // onTouchEvent

    int findShapeIndex(float x, float y){

        /*if(shapeList.get(lastCorrectIndex).rect.contains((int)x, (int)y)){
            //shapeList.remove(lastCorrectIndex); // 도형 삭제
            //invalidate(); // 화면 재구성
            ++lastCorrectIndex;
            return lastCorrectIndex;
        }*/
        for(int i = 0; i < shapeList.size(); ++i){
            if(shapeList.get(i).rect.contains((int)x, (int)y)){
                if(i == lastCorrectIndex){
                    return ++lastCorrectIndex;
                }
                else {
                    return -2;
                }
            }
        }
        return -1;
    } // findShapeIndex()

}











