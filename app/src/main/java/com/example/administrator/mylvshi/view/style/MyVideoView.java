package com.example.administrator.mylvshi.view.style;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by Administrator on 2016/11/30.
 */
public class MyVideoView extends VideoView {


    public MyVideoView(Context context) {
        super(context);
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
              //Log.i("@@@@", "onMeasure");
               int width = getDefaultSize(0, widthMeasureSpec);
               int height = getDefaultSize(0, heightMeasureSpec);
        /**//*if (mVideoWidth > 0 && mVideoHeight > 0) {
7            if ( mVideoWidth * height  > width * mVideoHeight ) {
8                //Log.i("@@@", "image too tall, correcting");
9                height = width * mVideoHeight / mVideoWidth;
10            } else if ( mVideoWidth * height  < width * mVideoHeight ) {
11                //Log.i("@@@", "image too wide, correcting");
12                width = height * mVideoWidth / mVideoHeight;
13            } else {
14                //Log.i("@@@", "aspect ratio is correct: " +
15                        //width+"/"+height+"="+
16                        //mVideoWidth+"/"+mVideoHeight);
17            }
18        }*/
               //Log.i("@@@@@@@@@@", "setting size: " + width + 'x' + height);
              setMeasuredDimension(width,height);
    }
}
