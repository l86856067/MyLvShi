package com.example.administrator.mylvshi.interfaces;

import com.example.administrator.mylvshi.bean.HotUser;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */
public interface HotUserLoadInterface {

    void getHotUserInfo(List<HotUser.ContentBean> list);

}
