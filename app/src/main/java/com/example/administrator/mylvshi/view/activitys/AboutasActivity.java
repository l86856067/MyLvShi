package com.example.administrator.mylvshi.view.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.administrator.mylvshi.R;
import com.example.administrator.mylvshi.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutasActivity extends BaseActivity {

    @BindView(R.id.mine_set_guanyu_back)
    ImageButton mine_set_guanyu_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutas);
        ButterKnife.bind(this);

        mine_set_guanyu_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
