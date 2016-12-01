package com.example.administrator.mylvshi.view.activitys;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.administrator.mylvshi.R;
import com.example.administrator.mylvshi.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.mine_help_back)
    ImageButton mine_help_back;
    @BindView(R.id.mine_help_send)
    Button mine_help_send;
    @BindView(R.id.mine_help_ani)
    View mine_help_ani;
    @BindView(R.id.mine_help_edit)
    EditText mine_help_edit;

    private AnimationDrawable drawable = null;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0 :
                    mine_help_ani.setVisibility(View.INVISIBLE);
                    drawable.stop();

                        Toast.makeText(HelpActivity.this, "感谢您的反馈！", Toast.LENGTH_SHORT).show();
                        mine_help_edit.setText("");
                    
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);

        initView();

        setListener();
    }

    private void setListener() {
        mine_help_back.setOnClickListener(this);
        mine_help_send.setOnClickListener(this);
    }

    private void initView() {
        mine_help_ani.setBackgroundResource(R.drawable.inganimation2);
        drawable = (AnimationDrawable) mine_help_ani.getBackground();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.mine_help_back:
                finish();
                break;
            case R.id.mine_help_send:
                mine_help_ani.setVisibility(View.VISIBLE);
                drawable.start();
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        SystemClock.sleep(2000);
                        handler.sendEmptyMessage(0);
                    }
                }.start();
                break;
        }
    }
}
