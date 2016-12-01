package com.example.administrator.mylvshi.view.style;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2016/11/29.
 */
public class StackLayout extends FrameLayout {

//    private int itemsMarginTop = dp2Px(8);
//    private int itemsMarginLeftRight = dp2Px(8);


    private StackAdapter adapter;
    private View[] viewsBuffer;

    private int mLimitTranslateX = 300;//限制移动距离，当超过这个距离的时候，删除该item

    //设置监听，在adapter发生变化时，可以监听到
    DataSetObserver dataSetObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            attachChildViews();
        }
    };


    public StackLayout(Context context) {
        this(context,null);
    }

    public StackLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StackLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        int screenWidth = getScreenWidth(getContext());

    }

    public void setAdapter(StackAdapter adapter){
        if (adapter == null){
            throw new IllegalArgumentException("adapter not null");
        }
        if (this.adapter != null){
            this.adapter.unregisterDataSetObserver(dataSetObserver);
        }

        this.adapter = adapter;
        //当Adapter发生变化时，在接口内可以监听到
        this.adapter.registerDataSetObserver(dataSetObserver);
        viewsBuffer = new View[adapter.getCount()];
        attachChildViews();
    }

    private void attachChildViews(){
        removeAllViews();
        for (int position = 0 ; position < adapter.getCount() ; position ++){
            Log.d("remove","===="+adapter.getCount());
            if (position < 3){
                viewsBuffer[position] = adapter.getView(position,viewsBuffer[position],this);
                addViewInLayout(viewsBuffer[position],0,viewsBuffer[position].getLayoutParams());
                initEvent(adapter.getView(position,viewsBuffer[position],this),position);
            }
        }
        requestLayout();//请求布局
    }

    private void initEvent(final View view, final int position) {

        view.setOnTouchListener(new OnTouchListener() {

            float touchX,distanceX;//手指按下时的X坐标以及手指在屏幕上移动的距离
            float touchY,distanceY;//手指按下时的Y坐标以及手指在屏幕上移动的距离

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        touchX = event.getRawX();
                        touchY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        distanceX = event.getRawX() - touchX;
                        distanceY = event.getRawY() - touchY;

                        view.setTranslationX(distanceX);
                        view.setTranslationY(distanceY);

                        break;
                    case MotionEvent.ACTION_UP:
                        if (Math.abs(distanceX) > mLimitTranslateX){
                            Log.d("remove",""+position);
                            adapter.remove(position);
                            view.setTranslationX(0);
                            view.setTranslationY(0);
                        }else {
                            collbackView(view,distanceX,distanceY);
//                            view.setTranslationX(0);
//                            view.setTranslationY(0);
                        }
                        break;
                }
                return true;
            }
        });
    }

    private void collbackView(final View view, final float distanceX, final float distanceY) {
        final Handler handler = new Handler(){
            float x = distanceX;
            float y = distanceY;
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                x = x - x/10;
                y = y - y/10;
                switch (msg.what){
                    case 0:
                        view.setTranslationX(x);
                        view.setTranslationY(y);
                        break;
                    case 1:
                        view.setTranslationX(0);
                        view.setTranslationY(0);
                        break;
                }
            }
        };
        new Thread(){
            @Override
            public void run() {
                super.run();
                for (int i = 0 ; i < 9 ; i ++){
                    SystemClock.sleep(10);
                    handler.sendEmptyMessage(0);
                }
                handler.sendEmptyMessage(1);
            }
        }.start();

    }


    public static int getScreenWidth(Context context){
        //声明窗口管理器
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //显示指标
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);//得到默认显示，得到指标
        return outMetrics.widthPixels;//返回像素宽度
    }

    public static int getScreenHeight(Context context){
        //声明窗口管理器
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //显示指标
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);//得到默认显示，得到指标
        return outMetrics.heightPixels;//返回像素高度
    }





}
