package com.example.administrator.mylvshi.view.activitys;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;

import com.example.administrator.mylvshi.R;
import com.example.administrator.mylvshi.base.BaseActivity;
import com.example.administrator.mylvshi.utils.FragmentUtils;
import com.example.administrator.mylvshi.view.fragments.DestinationFragment;
import com.example.administrator.mylvshi.view.fragments.HomeFragment;
import com.example.administrator.mylvshi.view.fragments.HotFragment;
import com.example.administrator.mylvshi.view.fragments.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    //所有需要授予运行时权限的的权限
    private String[] needPermissions = {Manifest.permission.CAMERA};
    private boolean isneed = true;//判断是否需要授权，防止不断的跳出授权提示框；
    private static final int PERMISSION_REQUESTCODE = 1;

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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

    @Override
    protected void onResume() {
        super.onResume();
        if (isneed){
            checkPermissions(needPermissions);
        }
    }

    private void checkPermissions(String... Permissions){
        List<String> deniedPermissions = findDeniedPermissions(Permissions);
        if (null != deniedPermissions && deniedPermissions.size() > 0){
            ActivityCompat.requestPermissions(this,deniedPermissions.toArray(new String[deniedPermissions.size()]),PERMISSION_REQUESTCODE);
        }
    }

    /**
     * 获取需要申请权限的列表
     * @param permissions
     * @return
     */
    private List<String> findDeniedPermissions(String[] permissions){

        List<String> needRequestPermissionsList = new ArrayList<>();
        for (String permiss : permissions){
            if (ContextCompat.checkSelfPermission(this,permiss) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this,permiss)){
                    needRequestPermissionsList.add(permiss);
            }
        }

        return needRequestPermissionsList;

    }

    /**
     * 检测是否所有的权限是否都已经被授权
     * @param grantResults
     * @return
     */
    private boolean verifyPermissions(int[] grantResults){
        for (int result : grantResults){
            if (result != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUESTCODE){
            if (!verifyPermissions(grantResults)){
                showMissingPermissinoDialog();
                isneed = false;
            }
        }
    }

    /**
     * 显示提示信息
     */
    private void showMissingPermissinoDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("title");
        builder.setMessage("message");
        builder.setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setPositiveButton("允许", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startAppSetting();
            }
        });
        builder.setCancelable(false);//设置可取消
        builder.show();
    }

    /**
     * 启动应用的设置
     */
    private void startAppSetting(){
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:"+getPackageName()));
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
