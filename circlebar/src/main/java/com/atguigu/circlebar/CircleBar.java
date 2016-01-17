package com.atguigu.circlebar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xfzhang on 2015/12/29.
 */
public class CircleBar extends View {

    private int progress; //当前进度
    private int max = 100; //最大进度
    private int roundColor = Color.GRAY; //圆环的颜色
    private int roundProgressColor = Color.RED; //圆环进度的颜色
    private float roundWidth = 10; //圆环的宽度
    private float textSize = 20; //文字的大小
    private int textColor = Color.GREEN; //文字的颜色

    private float viewWidth; //视图的宽度
    private Paint paint;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        //强制重绘
        //invalidate();//不可以, 不可以在分线程执行
        postInvalidate();
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getRoundColor() {
        return roundColor;
    }

    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
    }

    public int getRoundProgressColor() {
        return roundProgressColor;
    }

    public void setRoundProgressColor(int roundProgressColor) {
        this.roundProgressColor = roundProgressColor;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public CircleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setAntiAlias(true); //使平滑
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //1. 绘制背景圆环
        float radius = viewWidth/2-roundWidth/2;
        paint.setColor(roundColor);
        paint.setStrokeWidth(roundWidth); //指定画笔的宽度
        paint.setStyle(Paint.Style.STROKE); //空心
        canvas.drawCircle(viewWidth/2, viewWidth/2,radius, paint);
        //2. 绘制进度圆弧
        RectF rectF = new RectF(roundWidth/2, roundWidth/2, viewWidth-roundWidth/2,viewWidth-roundWidth/2);
        paint.setColor(roundProgressColor);
        canvas.drawArc(rectF,0, progress*360/max, false, paint);
        //3. 绘制进度文本
        String text = progress*100/max+"%";
        paint.setStrokeWidth(0);
        paint.setTextSize(textSize);
        //用矩形去框进度文本, 文本显示的宽高就是矩形的宽高
        Rect bounds = new Rect(); //此时里面没有数据
        paint.getTextBounds(text, 0, text.length(),bounds);//bounds就有宽高
        float textWidth = bounds.width();
        float textHeight = bounds.height();
        float textX = viewWidth/2-textWidth/2;
        float textY = viewWidth/2+textHeight/2;

        canvas.drawText(text, textX, textY, paint);
    }
}
