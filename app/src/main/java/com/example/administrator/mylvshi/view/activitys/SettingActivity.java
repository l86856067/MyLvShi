package com.example.administrator.mylvshi.view.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.mylvshi.R;
import com.example.administrator.mylvshi.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.mine_set_ani)
    View mine_set_ani;
    @BindView(R.id.mine_set_back)
    ImageButton mine_set_back;

    AnimationDrawable background = null;

    @BindView(R.id.mine_set_tixing)
    RelativeLayout mine_set_tixing;
    @BindView(R.id.mine_set_zidong)
    RelativeLayout mine_set_zidong;
    @BindView(R.id.mine_set_qingchu)
    RelativeLayout mine_set_qingchu;
    @BindView(R.id.mine_set_fenxiang)
    RelativeLayout mine_set_fenxiang;
    @BindView(R.id.mine_set_gengxin)
    RelativeLayout mine_set_gengxin;
    @BindView(R.id.mine_set_guanyu)
    RelativeLayout mine_set_guanyu;
    @BindView(R.id.mine_set_fankui)
    RelativeLayout mine_set_fankui;
    @BindView(R.id.mine_set_tuichu)
    Button mine_set_tuichu;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 0:
                    mine_set_ani.setVisibility(View.INVISIBLE);
                    background.stop();
                    Toast.makeText(SettingActivity.this, "当前已是最新版本", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        initView();

        setListener();

    }

    private void initView() {
        mine_set_ani.setBackgroundResource(R.drawable.inganimation);
        background = (AnimationDrawable) mine_set_ani.getBackground();
        background.setOneShot(false);
    }

    private void setListener() {
        mine_set_tixing.setOnClickListener(this);
        mine_set_zidong.setOnClickListener(this);
        mine_set_qingchu.setOnClickListener(this);
        mine_set_fenxiang.setOnClickListener(this);
        mine_set_gengxin.setOnClickListener(this);
        mine_set_guanyu.setOnClickListener(this);
        mine_set_fankui.setOnClickListener(this);
        mine_set_back.setOnClickListener(this);
        mine_set_tuichu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mine_set_tixing:
                break;
            case R.id.mine_set_zidong:
                break;
            case R.id.mine_set_qingchu:
                Toast.makeText(SettingActivity.this, "清楚缓存成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_set_fenxiang:
                startActivity(new Intent(this,FenxiangActivity.class));
                break;
            case R.id.mine_set_gengxin:
                mine_set_ani.setVisibility(View.VISIBLE);
                background.start();
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        SystemClock.sleep(3000);
                        handler.sendEmptyMessage(0);
                    }
                }.start();
                break;
            case R.id.mine_set_guanyu:
                startActivity(new Intent(this,AboutasActivity.class));
                break;
            case R.id.mine_set_fankui:
                startActivity(new Intent(this,HelpActivity.class));
                break;
            case R.id.mine_set_back:
                finish();
                break;
            case R.id.mine_set_tuichu:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("确定退出");
                builder.setMessage("是否确定退出登录？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Platform weibo = ShareSDK.getPlatform(SettingActivity.this, SinaWeibo.NAME);
                        if (weibo.isAuthValid()){
                            weibo.removeAccount(true);
                            setResult(4);
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("手滑了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();


                break;
        }
    }
}
