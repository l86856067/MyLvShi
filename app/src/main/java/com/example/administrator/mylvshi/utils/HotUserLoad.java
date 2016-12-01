package com.example.administrator.mylvshi.utils;

import android.content.Context;
import android.util.Log;

import com.example.administrator.mylvshi.R;
import com.example.administrator.mylvshi.bean.HotUser;
import com.example.administrator.mylvshi.interfaces.HotUserLoadInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */
public class HotUserLoad {

    public static void getHotUserInfo(String url, final HotUserLoadInterface hotinter){

        final List<HotUser.ContentBean> list = new ArrayList<>();

        Log.d("load","调用方法");

        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("load","请求成功");
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray array = jsonObject.getJSONArray("content");
                    if (array != null){
                        HotUser.ContentBean contentBean;
                        HotUser.ContentBean.PoiBean poiBean;
                        HotUser.ContentBean.UserBean userBean;
                        for (int i = 0 ;i < array.length() ; i ++){
                            contentBean = new HotUser.ContentBean();
                            JSONObject obj = array.getJSONObject(i);
                            contentBean.setId(obj.getInt("id"));
                            contentBean.setCreatedDate(obj.getLong("createdDate"));
                            contentBean.setVideoHref(obj.getString("videoHref"));
                            contentBean.setImageHref(obj.getString("imageHref"));
                            contentBean.setSummary(obj.getString("summary"));

                            JSONObject poiobj = obj.getJSONObject("poi");
                            poiBean = new HotUser.ContentBean.PoiBean();
                            poiBean.setPoiName(poiobj.getString("琉璃路"));

                            JSONObject userobj = obj.getJSONObject("user");
                            userBean = new HotUser.ContentBean.UserBean();
                            userBean.setNickname(userobj.getString("nickname"));
                            userBean.setImageHref(userobj.getString("imageHref"));

                            contentBean.setPoi(poiBean);
                            contentBean.setUser(userBean);

                            list.add(contentBean);
                            Log.d("load","正在下载"+list.size());
                        }
                        hotinter.getHotUserInfo(list);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("load","错误"+ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d("load","onCancelled");
            }

            @Override
            public void onFinished() {
                Log.d("load","onFinished");
            }
        });

    }

    public static List<HotUser.ContentBean> HomeDownLoad(Context context){

        final List<HotUser.ContentBean> list = new ArrayList<>();
        InputStream inputStream = context.getResources().openRawResource(R.raw.index);
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(inputStream,"gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(isr);
        StringBuffer sb = new StringBuffer();
        String line;
        try {
            while ((line = br.readLine()) != null){
                sb.append(line);
                sb.append("\n");
            }
            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray array = jsonObject.getJSONArray("content");
            if (array != null){
                HotUser.ContentBean contentBean;
                HotUser.ContentBean.PoiBean poiBean;
                HotUser.ContentBean.UserBean userBean;
                for (int i = 0 ;i < array.length() ; i ++){
                    contentBean = new HotUser.ContentBean();
                    JSONObject obj = array.getJSONObject(i);
                    contentBean.setId(obj.getInt("id"));
                    contentBean.setCreatedDate(obj.getLong("createdDate"));
                    contentBean.setVideoHref(obj.getString("videoHref"));
                    contentBean.setImageHref(obj.getString("imageHref"));
                    contentBean.setSummary(obj.getString("summary"));

                    JSONObject poiobj = obj.getJSONObject("poi");
                    poiBean = new HotUser.ContentBean.PoiBean();
                    poiBean.setPoiName(poiobj.getString("poiName"));

                    JSONObject userobj = obj.getJSONObject("user");
                    userBean = new HotUser.ContentBean.UserBean();
                    userBean.setNickname(userobj.getString("nickname"));
                    userBean.setImageHref(userobj.getString("imageHref"));

                    contentBean.setPoi(poiBean);
                    contentBean.setUser(userBean);

                    list.add(contentBean);
                    Log.d("load","正在下载"+list.size());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return list;
    }

}
