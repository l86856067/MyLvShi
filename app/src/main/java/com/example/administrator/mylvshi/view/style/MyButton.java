package com.example.administrator.mylvshi.view.style;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.mylvshi.R;

/**
 * 自定义开关按钮
 * Created by Administrator on 2016/11/25.
 */
public class MyButton extends View {

    private Bitmap mbgoff;//底部灰色图
    private Bitmap mbgon;//地图粉色图

    private Bitmap mballoff;//灰色的球
    private Bitmap mballon;//红色的球

    private float mcurrentx = 0;

    private boolean mbtnon = true;//开关是否开启，默认为开启
    private int movelength;//最大移动距离
    private float mlastx = 0;//第一次按下的有效区域
    private Rect mdest = null;//绘制的目标区域大小

    private int movedeltx = 0;//移动偏移量
    private Paint paint = null;//画笔工具
    private OnMyButtonChangedListener mybuttonlistener = null;//状态监听接口
    private boolean mflag = false;//
    private boolean menabled = true;//
    private final int MAX_ALPHA = 255;//最大透明度
    private int malpha = MAX_ALPHA;//当前透明度
    private boolean mscorr = false;//判断是否在拖动



    public MyButton(Context context) {
        this(context,null);
    }

    public MyButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化方法
     */
    private void initView() {
        mbgoff = BitmapFactory.decodeResource(getResources(), R.drawable.bgoff);
        mbgon = BitmapFactory.decodeResource(getResources(),R.drawable.bgon);
        mballoff = BitmapFactory.decodeResource(getResources(),R.drawable.balloff);
        mballon = BitmapFactory.decodeResource(getResources(),R.drawable.ballon);

        movelength = mbgoff.getWidth() - mballoff.getWidth();
        //
        mdest = new Rect(0,0,mbgoff.getWidth(),mbgoff.getHeight());

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setAlpha(255);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mbgon.getWidth(), mbgon.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*System.out.println("---onDraw()---mMoveDeltX= " + mMoveDeltX +
                "  mSwitchBgUnseleted.getWidth()= " + mSwitchBgUnseleted.getWidth() +
                "  mSwitchBallSeleted.getWidth()= " + mSwitchBallSeleted.getWidth() +
                "  mMoveLength = " + mMoveLength);*/

        canvas.saveLayerAlpha(new RectF(mdest), malpha, Canvas.MATRIX_SAVE_FLAG
                | Canvas.CLIP_SAVE_FLAG | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG
                | Canvas.FULL_COLOR_LAYER_SAVE_FLAG
                | Canvas.CLIP_TO_LAYER_SAVE_FLAG);
        //如果是关闭的
        if(!mbtnon){
            if(movedeltx > 0){//向右滑动了
                if(movedeltx < movelength/2){//滑动距离小于一半
                    canvas.drawBitmap(mbgoff, 0, 0, null); //灰色背景
                    canvas.drawBitmap(mballoff, movedeltx, 0, null); //灰色按钮
                }else{//滑动距离大于一半
                    canvas.drawBitmap(mbgon, 0, 0, null); //绿色背景
                    canvas.drawBitmap(mballon, movedeltx, 0, null); //绿色按钮
                }
            }else{
                canvas.drawBitmap(mbgoff, 0, 0, null); //灰色背景
                canvas.drawBitmap(mballoff, 0, 0, null); //灰色按钮
            }
        }else{
            if(movedeltx < 0){//向右滑动了
                if(Math.abs(movedeltx) < movelength/2){//滑动距离小于一半
                    canvas.drawBitmap(mbgon, 0, 0, null); //绿色背景
                    canvas.drawBitmap(mballon, mbgon.getWidth() - mbgon.getWidth() + movedeltx, 0, null); //绿色按钮
                }else{//滑动距离大于一半
                    canvas.drawBitmap(mbgoff, 0, 0, null); //灰色背景
                    canvas.drawBitmap(mballoff, mbgon.getWidth() - mbgon.getWidth() + movedeltx, 0, null); //灰色按钮
                }
            }else{
                canvas.drawBitmap(mbgon, 0, 0, null); //绿色背景
                canvas.drawBitmap(mballon, movelength, 0, null); //绿色按钮
            }
        }

        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //如果Enabled属性设定为true,触摸效果才有效
        if(!menabled){
            return true;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mlastx = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                mcurrentx = event.getX();
                movedeltx = (int) (mcurrentx - mlastx);
                //System.out.println("===============" + mMoveDeltX);
                if(movedeltx > 3){
                    //设置了3这个误差距离，可以更好的实现点击效果
                    mscorr = true;
                }
                // 如果开关开着向右滑动，或者开关关着向左滑动（这时候是不需要处理的）
                if ((mbtnon && movedeltx > 0) || (!mbtnon && movedeltx < 0)) {
                    mflag = true;
                    movedeltx = 0;
                }

                if (Math.abs(movedeltx) > movelength) {
                    movedeltx = movedeltx > 0 ? movelength : -movelength;
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                //如果没有滑动过，就看作一次点击事件
                if(!mscorr){
                    movedeltx = mbtnon ? movelength : -movelength;
                    mbtnon = !mbtnon;
                    if (mybuttonlistener != null) {
                        mybuttonlistener.onMyButtonChange(this, mbtnon);
                    }
                    invalidate();
                    movedeltx = 0;
                    break;
                }
                mscorr = false;
                if (Math.abs(movedeltx) > 0 && Math.abs(movedeltx) < movelength / 2) {
                    movedeltx = 0;
                    invalidate();
                } else if (Math.abs(movedeltx) > movelength / 2
                        && Math.abs(movedeltx) <= movelength) {
                    movedeltx = movedeltx > 0 ? movelength : -movelength;
                    mbtnon = !mbtnon;
                    if (mybuttonlistener != null) {
                        mybuttonlistener.onMyButtonChange(this, mbtnon);
                    }
                    invalidate();
                    movedeltx = 0;
                } else if (movedeltx == 0 && mflag) {
                    // 这时候得到的是不需要进行处理的，因为已经move过了
                    movedeltx = 0;
                    mflag = false;
                }
            default:
                break;
        }
        invalidate();
        return true;
    }

    /**
     * 设置button状态监听
     * @param listener
     */
    public void setOnChangListener(OnMyButtonChangedListener listener){
        mybuttonlistener = listener;
    }

    /**
     * button开关监听接口
     */
    public interface OnMyButtonChangedListener{
        public void onMyButtonChange(MyButton mybutton, boolean isChecked);
    }

    @Override
    public void setEnabled(boolean enabled) {
        menabled = enabled;
        malpha = enabled? MAX_ALPHA : MAX_ALPHA / 2;
        super.setEnabled(enabled);
        invalidate();
    }

    /**
     * 自动判断切换至相反的属性
     */
    public void toggle(){
        setChecked(!mbtnon);
    }

    /**
     * 设置选中的状态
     * @param checked 选中的状态 true 选中，false 未选中
     */
    public void setChecked(boolean checked){
        mbtnon = checked;
        invalidate();
    }

}
