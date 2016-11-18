package com.example.administrator.mylvshi.view.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;

import com.example.administrator.mylvshi.R;
import com.example.administrator.mylvshi.base.BaseActivity;
import com.example.administrator.mylvshi.utils.FragmentUtils;
import com.example.administrator.mylvshi.view.fragments.DestinationFragment;
import com.example.administrator.mylvshi.view.fragments.HomeFragment;
import com.example.administrator.mylvshi.view.fragments.HotFragment;
import com.example.administrator.mylvshi.view.fragments.MineFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private HomeFragment homeFragment;
    private DestinationFragment destinationFragment;
    private HotFragment hotFragment;
    private MineFragment mineFragment;

    @BindView(R.id.tab_rb_1)
    ImageButton tab_rb_1;
    @BindView(R.id.tab_rb_2)
    ImageButton tab_rb_2;
    @BindView(R.id.tab_rb_3)
    ImageButton tab_rb_3;
    @BindView(R.id.tab_rb_4)
    ImageButton tab_rb_4;
    @BindView(R.id.tab_rb_5)
    ImageButton tab_rb_5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        initAndShowFragment();
        setLisenter();

    }

    private void initAndShowFragment() {
        homeFragment = new HomeFragment();
        destinationFragment = new DestinationFragment();
        hotFragment = new HotFragment();
        mineFragment = new MineFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_main_fragments, homeFragment)
                .add(R.id.fl_main_fragments, destinationFragment)
                .hide(destinationFragment)
                .add(R.id.fl_main_fragments, hotFragment)
                .hide(hotFragment)
                .add(R.id.fl_main_fragments, mineFragment)
                .hide(mineFragment)
                .commit();



    }


    private void setLisenter() {

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    public void click(View view) {
        List<Fragment> fragments = fragmentManager.getFragments();
                for (Fragment f : fragments) {
                    if (!f.isHidden()) {
                        fragmentManager.beginTransaction()
                                .hide(f)
                                .commit();
                    }
                }
        switch (view.getId()){
            case R.id.tab_rb_1:
                tab_rb_1.setImageResource(R.mipmap.tab_index_on);
                tab_rb_2.setImageResource(R.mipmap.tab_destination_off);
                tab_rb_3.setImageResource(R.mipmap.tab_hot_off);
                tab_rb_4.setImageResource(R.mipmap.tab_mine_off);
                FragmentUtils.judgeToShow(fragmentManager, homeFragment);
                break;
            case R.id.tab_rb_2:
                tab_rb_1.setImageResource(R.mipmap.tab_index_off);
                tab_rb_2.setImageResource(R.mipmap.tab_destination_on);
                tab_rb_3.setImageResource(R.mipmap.tab_hot_off);
                tab_rb_4.setImageResource(R.mipmap.tab_mine_off);
                FragmentUtils.judgeToShow(fragmentManager, destinationFragment);
                break;
            case R.id.tab_rb_3:
                tab_rb_1.setImageResource(R.mipmap.tab_index_off);
                tab_rb_2.setImageResource(R.mipmap.tab_destination_off);
                tab_rb_3.setImageResource(R.mipmap.tab_hot_on);
                tab_rb_4.setImageResource(R.mipmap.tab_mine_off);
                FragmentUtils.judgeToShow(fragmentManager, hotFragment);
                break;
            case R.id.tab_rb_4:
                tab_rb_1.setImageResource(R.mipmap.tab_index_off);
                tab_rb_2.setImageResource(R.mipmap.tab_destination_off);
                tab_rb_3.setImageResource(R.mipmap.tab_hot_off);
                tab_rb_4.setImageResource(R.mipmap.tab_mine_on);
                FragmentUtils.judgeToShow(fragmentManager, mineFragment);
                break;
            case R.id.tab_rb_5:
                break;

        }
    }
}
