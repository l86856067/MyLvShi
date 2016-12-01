package com.example.administrator.mylvshi.view.fragments;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mylvshi.R;
import com.example.administrator.mylvshi.bean.HotUser;
import com.example.administrator.mylvshi.utils.HotUserLoad;
import com.example.administrator.mylvshi.view.style.StackAdapter;
import com.example.administrator.mylvshi.view.style.StackLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/17.
 */

public class HotFragment extends Fragment {

    @BindView(R.id.mine_hot_stack)
    StackLayout mine_hot_stack;
    @BindView(R.id.mine_hot_ani)
    View mine_hot_ani;
    AnimationDrawable background = null;

    private List<HotUser.ContentBean> mlist;
    StackAdapter adapter = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hot,container,false);//给Fragment一个视图

        ButterKnife.bind(this,view);

//        mine_hot_ani.setBackgroundResource(R.drawable.inganimation);
//        background = (AnimationDrawable) mine_hot_ani.getBackground();
//        background.setOneShot(false);
//        background.start();

        mlist = HotUserLoad.HomeDownLoad(getContext());

        adapter = new StackAdapter(getContext(),mlist);
        mine_hot_stack.setAdapter(adapter);

        return view;
        
    }

}
