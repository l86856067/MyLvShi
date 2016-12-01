package com.example.administrator.mylvshi.view.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.mylvshi.R;
import com.example.administrator.mylvshi.base.RectImageView;
import com.example.administrator.mylvshi.view.activitys.LandActivity;
import com.example.administrator.mylvshi.view.activitys.SettingActivity;
import com.zxing.activity.CaptureActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/17.
 */

public class MineFragment extends Fragment implements View.OnClickListener {



    @BindView(R.id.mine_top_layout)
    RelativeLayout mine_top_layout;
    @BindView(R.id.mine_top_layout_mess)
    ImageButton mine_top_layout_mess;
    @BindView(R.id.mine_top_layout_head)
    ImageButton mine_top_layout_head;
    @BindView(R.id.mine_top_layout_guanzhu)
    TextView mine_top_layout_guanzhu;
    @BindView(R.id.mine_top_layout_fans)
    TextView mine_top_layout_fans;
    @BindView(R.id.mine_top_layout_load)
    TextView mine_top_layout_load;

    @BindView(R.id.mine_mid_layout_collection)
    RelativeLayout mine_mid_layout_collection;
    @BindView(R.id.mine_mid_layout_draft)
    RelativeLayout mine_mid_layout_draft;
    @BindView(R.id.mine_mid_layout_vip)
    RelativeLayout mine_mid_layout_vip;
    @BindView(R.id.mine_mid_layout_where)
    RelativeLayout mine_mid_layout_where;
    @BindView(R.id.mine_bottom_layout_sweep)
    RelativeLayout mine_bottom_layout_sweep;
    @BindView(R.id.mine_bottom_layout_bind)
    RelativeLayout mine_bottom_layout_bind;
    @BindView(R.id.mine_bottom_layout_help)
    RelativeLayout mine_bottom_layout_help;
    @BindView(R.id.mine_bottom_layout_setting)
    RelativeLayout mine_bottom_layout_setting;

    private final static int LAND = 2;
    private final static int ZXING = 1;
    private String muserName = null;
    private String muserIcon = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mine,container,false);//给Fragment一个视图

        ButterKnife.bind(this,view);

        setListener();

        return view;
    }

    private void setListener() {
        mine_top_layout.setOnClickListener(this);
        mine_top_layout_mess.setOnClickListener(this);
        mine_top_layout_head.setOnClickListener(this);
        mine_top_layout_guanzhu.setOnClickListener(this);
        mine_top_layout_fans.setOnClickListener(this);
        mine_top_layout_load.setOnClickListener(this);

        mine_mid_layout_collection.setOnClickListener(this);
        mine_mid_layout_draft.setOnClickListener(this);
        mine_mid_layout_vip.setOnClickListener(this);
        mine_mid_layout_where.setOnClickListener(this);
        mine_bottom_layout_sweep.setOnClickListener(this);
        mine_bottom_layout_bind.setOnClickListener(this);
        mine_bottom_layout_help.setOnClickListener(this);
        mine_bottom_layout_setting.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ZXING){
            String str = data.getStringExtra("result");
            String s = str.substring(0,4);
            if ("http".equals(s)){
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("结果");
                builder.setMessage(str);
                builder.setNegativeButton("打开", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("复制", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }else {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("结果");
                builder.setMessage(str);
                builder.setNegativeButton("打开", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("复制", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        }
        if (requestCode == LAND){
            muserName = data.getStringExtra("name");
            muserIcon = data.getStringExtra("icon");
            mine_top_layout_load.setText(muserName);
            Glide.with(getActivity()).load(muserIcon).transform(new RectImageView(getContext())).into(mine_top_layout_head);
        }
        if (requestCode == 4){
            mine_top_layout_load.setText("点击登录");
            mine_top_layout_head.setImageResource(R.mipmap.bg_head_oval);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        if (true){
            switch (v.getId()){
                case R.id.mine_bottom_layout_sweep:
                    startActivityForResult(new Intent(getActivity(), CaptureActivity.class),ZXING);
                    break;
                case R.id.mine_bottom_layout_setting:
                    startActivityForResult(new Intent(getActivity(),SettingActivity.class),4);
                    break;
                case R.id.mine_mid_layout_vip:
                    startActivity(new Intent(getActivity(), SettingActivity.class));
                    break;
                default:
                    startActivityForResult(new Intent(getActivity(),LandActivity.class),LAND);
                    break;
//            case R.id.mine_top_layout:
//                startActivity(new Intent(getActivity(), LandActivity.class));
//                break;
//            case R.id.mine_top_layout_mess:
//                startActivity(new Intent(getActivity(), LandActivity.class));
//                break;
//            case R.id.mine_top_layout_head:
//                startActivity(new Intent(getActivity(), LandActivity.class));
//                break;
//            case R.id.mine_top_layout_guanzhu:
//                startActivity(new Intent(getActivity(), LandActivity.class));
//                break;
//            case R.id.mine_top_layout_fans:
//                startActivity(new Intent(getActivity(), LandActivity.class));
//                break;
//            case R.id.mine_top_layout_load:
//                startActivity(new Intent(getActivity(), LandActivity.class));
//                break;
            }
        }else {
            switch (v.getId()){
                case R.id.mine_bottom_layout_sweep:
                    startActivityForResult(new Intent(getActivity(), CaptureActivity.class),1);
                    break;
                case R.id.mine_bottom_layout_setting:
                    startActivity(new Intent(getActivity(), SettingActivity.class));
                    break;
                default:
                    startActivity(new Intent(getActivity(), LandActivity.class));
                    break;
//            case R.id.mine_top_layout:
//                startActivity(new Intent(getActivity(), LandActivity.class));
//                break;
//            case R.id.mine_top_layout_mess:
//                startActivity(new Intent(getActivity(), LandActivity.class));
//                break;
//            case R.id.mine_top_layout_head:
//                startActivity(new Intent(getActivity(), LandActivity.class));
//                break;
//            case R.id.mine_top_layout_guanzhu:
//                startActivity(new Intent(getActivity(), LandActivity.class));
//                break;
//            case R.id.mine_top_layout_fans:
//                startActivity(new Intent(getActivity(), LandActivity.class));
//                break;
//            case R.id.mine_top_layout_load:
//                startActivity(new Intent(getActivity(), LandActivity.class));
//                break;
            }
        }

    }

}
