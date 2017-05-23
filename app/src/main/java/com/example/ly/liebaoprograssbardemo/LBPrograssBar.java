package com.example.ly.liebaoprograssbardemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by txw_pc on 2017/5/23.
 * 仿猎豹首页的进度条
 */

public class LBPrograssBar extends View{
    public LBPrograssBar(Context context) {
        super(context);

        init();
    }

    public LBPrograssBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);init();
    }

    public LBPrograssBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);init();
    }

    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            invalidate();
        }
    };

    private void init(){
        whitePaint = new Paint();
        whitePaint.setColor(Color.BLUE);
        whitePaint.setAntiAlias(true);
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.BLACK);
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(lineWidth);
        backgroundPaint.setAntiAlias(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (sweepAngle<=270) {
                    sweepAngle++;
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(1);
                }
            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);


    }

    private int sweepAngle = 1;

    private final int startAngle = 120;//起始角度
    private final int viewRadius = 120;//控件半径
    private final int lineWidth = 20;


    private Paint whitePaint;
    private Paint backgroundPaint;
    private void drawBackground(Canvas canvas){

//        圆点坐标：(x0,y0)
//        半径：r
//        角度：a0
//
//        则圆上任一点为：（x1,y1）
//        x1   =   x0   +   r   *   cos(ao   *   3.14   /180   )
//        y1   =   y0   +   r   *   sin(ao   *   3.14   /180   )
        int ao = sweepAngle+startAngle;
        RectF rectF = new RectF(0,0,240,240);
        canvas.drawArc(rectF,startAngle,sweepAngle,false,backgroundPaint);

        canvas.drawCircle((float) (120+(120*Math.cos(ao*3.14/180))),
                (float) (120+(120*Math.sin(ao*3.14/180))),10,
                whitePaint);
        canvas.drawCircle((float) (120+(120*Math.cos(startAngle*3.14/180))),
                (float) (120+(120*Math.sin(startAngle*3.14/180))),
                10,whitePaint);
    }
    private void drawPrograss(Canvas canvas){

    }



}
