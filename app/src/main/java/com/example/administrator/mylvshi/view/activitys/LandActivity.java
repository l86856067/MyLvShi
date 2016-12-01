package com.example.administrator.mylvshi.view.activitys;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import com.example.administrator.mylvshi.R;
import com.example.administrator.mylvshi.base.BaseActivity;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

public class LandActivity extends BaseActivity {

    @BindView(R.id.land_vd)
    VideoView land_vd;

    private String userName = null;
    private String userIcon = null;

    private Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land);

        ButterKnife.bind(this);
        ShareSDK.initSDK(this);

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
                Platform qq = ShareSDK.getPlatform(this, QQ.NAME);
                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        Log.d("user", platform.getDb().getToken());
                        Log.d("user", platform.getDb().exportData());
                        Log.d("user", platform.getDb().getPlatformNname());
                        Log.d("user", platform.getDb().getTokenSecret());
                        Log.d("user", platform.getDb().getUserGender());
                        Log.d("user", platform.getDb().getUserIcon());
                        Log.d("user", platform.getDb().getUserId());
                        Log.d("user", platform.getDb().getUserName());
                        Log.d("user", platform.getDb().getExpiresIn() + "");
                        Log.d("user", platform.getDb().getExpiresTime() + "");
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
                qq.showUser(null);
                finish();
                break;
            case R.id.land_imgbtn_weibo:
                Platform weibo = ShareSDK.getPlatform(this, SinaWeibo.NAME);
                weibo.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        if (i == platform.ACTION_USER_INFOR){
                            PlatformDb pldb = platform.getDb();
                            userName = pldb.getUserName();
                            userIcon = pldb.getUserIcon();
                            Intent intent = new Intent();
                            intent.putExtra("name",userName);
                            intent.putExtra("icon",userIcon);
                            setResult(2,intent);
                            finish();
                        }
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
                weibo.showUser(null);
                break;
            case R.id.land_imgbtn_weixin:
                break;
        }
    }
}
