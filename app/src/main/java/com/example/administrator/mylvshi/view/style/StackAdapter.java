package com.example.administrator.mylvshi.view.style;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.mylvshi.R;
import com.example.administrator.mylvshi.base.RectImageView;
import com.example.administrator.mylvshi.bean.HotUser;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public class StackAdapter extends BaseAdapter {

    private Context context;
    private List<HotUser.ContentBean> list;

    private final Object mLock = new Object();

    public StackAdapter(Context context , List<HotUser.ContentBean> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_hot_item,parent,false);
        }
        MyVideoView videoView = (MyVideoView) convertView.findViewById(R.id.mine_hot_stack_video);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.mine_hot_stack_head);
        TextView name = (TextView) convertView.findViewById(R.id.mine_hot_stack_name);
        TextView more = (TextView) convertView.findViewById(R.id.mine_hot_stack_more);
        TextView addr = (TextView) convertView.findViewById(R.id.mine_hot_stack_locatext);
        MediaController mediaController = new MediaController(context);
        mediaController.setPadding(50,100,50,100);

        HotUser.ContentBean contentBean = list.get(position);

        videoView.setVideoURI(Uri.parse(contentBean.getVideoHref()));
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
        videoView.requestFocus();
        if (position == 0){
            videoView.start();
        }

        Glide.with(context).load(contentBean.getImageHref()).transform(new RectImageView(context)).into(imageView);
        name.setText(contentBean.getUser().getNickname());
        more.setText(contentBean.getSummary());
        addr.setText(contentBean.getPoi().getPoiName());
        return convertView;

    }

    public void remove(int position){
        list.remove(position);
        notifyDataSetChanged();
    }

}
