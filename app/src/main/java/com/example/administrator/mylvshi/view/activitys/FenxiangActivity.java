package com.example.administrator.mylvshi.view.activitys;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.administrator.mylvshi.R;
import com.example.administrator.mylvshi.base.BaseActivity;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

public class FenxiangActivity extends BaseActivity implements View.OnClickListener,PlatformActionListener {

    @BindView(R.id.mine_set_share_weibo)
    ImageButton mine_set_share_weibo;
    @BindView(R.id.mine_set_share_qq)
    ImageButton mine_set_share_qq;
    @BindView(R.id.mine_set_share_qz)
    ImageButton mine_set_share_qz;
    @BindView(R.id.mine_set_share_weixin)
    ImageButton mine_set_share_weixin;
    @BindView(R.id.mine_set_share_wxpy)
    ImageButton mine_set_share_wxpy;
    @BindView(R.id.mine_set_share_more)
    ImageButton mine_set_share_more;
    @BindView(R.id.mine_set_share_back)
    ImageButton mine_set_share_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenxiang);
        ButterKnife.bind(this);
        ShareSDK.initSDK(this);

        setListener();
    }

    private void setListener() {
        mine_set_share_weibo.setOnClickListener(this);
        mine_set_share_qq.setOnClickListener(this);
        mine_set_share_qz.setOnClickListener(this);
        mine_set_share_weixin.setOnClickListener(this);
        mine_set_share_wxpy.setOnClickListener(this);
        mine_set_share_more.setOnClickListener(this);
        mine_set_share_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mine_set_share_weibo:
                SinaWeibo.ShareParams sp = new SinaWeibo.ShareParams();
                sp.setText("旅视，换种方式看世界 \n" +
                        "旅视，让我们像旅行一样去生活，发现你身边精彩好玩的旅行去处，" +
                        "让你的目的地更清晰、更可感！http://www.lvshiv.com/app.html");
//              sp.setImagePath("http://h.hiphotos.baidu.com/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=d5bca6330124ab18f41be96554938da8/8b82b9014a90f603f457df523112b31bb151edc4.jpg");
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                weibo.SSOSetting(true);
                weibo.setPlatformActionListener(this); // 设置分享事件回调

                weibo.share(sp);// 执行图文分享
                break;
            case R.id.mine_set_share_qq:
                QQ.ShareParams qqsp = new QQ.ShareParams();
                qqsp.setTitle("旅视，换种方式看世界");
                qqsp.setText("旅视，让我们像旅行一样去生活，发现你身边精彩好玩的旅行去处，" +
                        "让你的目的地更清晰、更可感！http://www.lvshiv.com/app.html");
                qqsp.setImageData(BitmapFactory.decodeResource(getResources(),R.drawable.btn_share_weibo_off));
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(this);
                qq.share(qqsp);
                break;
            case R.id.mine_set_share_qz:
                QZone.ShareParams qzsp = new QZone.ShareParams();
                qzsp.setTitle("旅视，换种方式看世界");
                qzsp.setTitleUrl("http://www.lvshiv.com/app.html"); // 标题的超链接
                qzsp.setText("旅视，让我们像旅行一样去生活，发现你身边精彩好玩的旅行去处，" +
                        "让你的目的地更清晰、更可感！");
                qzsp.setImageUrl("http://h.hiphotos.baidu.com/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50" +
                        "/sign=d5bca6330124ab18f41be96554938da8/8b82b9014a90f603f457" +
                        "df523112b31bb151edc4.jpg");
                qzsp.setSite("发布分享的网站名称");
                qzsp.setSiteUrl("发布分享网站的地址");

                Platform qzone = ShareSDK.getPlatform (QZone.NAME);
                qzone. setPlatformActionListener(this);
                 // 设置分享事件回调
                // 执行图文分享
                qzone.share(qzsp);
                break;
            case R.id.mine_set_share_weixin:
                Wechat.ShareParams wsp = new Wechat.ShareParams();
                wsp.setTitle("旅视，换种方式看世界");
                wsp.setText("旅视，让我们像旅行一样去生活，发现你身边精彩好玩的旅行去处，" +
                        "让你的目的地更清晰、更可感！");
                wsp.setImageData(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
                wsp.setUrl("http://www.lvshiv.com/app.html");

                Platform weixin = ShareSDK.getPlatform(Wechat.NAME);
                weixin.setPlatformActionListener(this);
                weixin.share(wsp);
                break;
            case R.id.mine_set_share_wxpy:
                WechatMoments.ShareParams wmsp = new WechatMoments.ShareParams();
                wmsp.setImageData(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
                wmsp.setTitle("旅视，换种方式看世界");
                wmsp.setText("旅视，让我们像旅行一样去生活，发现你身边精彩好玩的旅行去处，" +
                        "让你的目的地更清晰、更可感！");
                Platform wxpy = ShareSDK.getPlatform(WechatMoments.NAME);
                wxpy.setPlatformActionListener(this);
                wxpy.share(wmsp);
                break;
            case R.id.mine_set_share_more:
                OnekeyShare oks = new OnekeyShare();
                oks.disableSSOWhenAuthorize();//关闭sso授权
                oks.setTitle("旅视，换种方式看世界");//标题，印象笔记，邮箱，信息，微信，人人网，qq空间使用
                oks.setTitleUrl("http://www.lvshiv.com/app.html");//标题的网络链接，在人人网和qq空间使用
                oks.setText("旅视，让我们像旅行一样去生活，发现你身边精彩好玩的旅行去处，让你的目的地更清晰、更可感！");//分享文本，所有平台都需要这个
//                oks.setImageUrl("");//分享网络图片，新浪微博使用；
                oks.setUrl("http://www.lvshiv.com/app.html");//仅在微信，包括好友和朋友圈中使用
                oks.setComment("不错的软件");//是我对这条分享的评论，仅在人人网和qq空间中使用
                oks.setSite("旅视");//分享此内容的网站名称，仅在qq空间使用
                oks.setSiteUrl("http://www.lvshiv.com/app.html");//分享此内容的网站地址，仅在qq空间使用
                oks.show(this);
                break;
            case R.id.mine_set_share_back:
                finish();
                break;
        }
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Toast.makeText(FenxiangActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Toast.makeText(FenxiangActivity.this, "出错", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel(Platform platform, int i) {
        Toast.makeText(FenxiangActivity.this, "您已取消分享", Toast.LENGTH_SHORT).show();
    }
}
