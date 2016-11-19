package com.example.administrator.mylvshi.view.activitys;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import com.example.administrator.mylvshi.R;
import com.example.administrator.mylvshi.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LandActivity extends BaseActivity {

    @BindView(R.id.land_vd)
    VideoView land_vd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land);

        ButterKnife.bind(this);

        initView();

        setListener();

    }

    private void setListener() {
        land_vd.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                land_vd.start();
            }
        });
    }

    private void initView() {
        land_vd.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video));
        land_vd.start();
    }

    public void land(View view) {
        switch (view.getId()){
            case R.id.land_imgbtn_shouji:
                break;
            case R.id.land_imgbtn_qq:
                break;
            case R.id.land_imgbtn_weibo:
                break;
            case R.id.land_imgbtn_weixin:
                break;
        }
    }
}
