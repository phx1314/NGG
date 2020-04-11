//
//  Xinwenzixun
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.R;
import com.udows.common.proto.MNews;


public class Xinwenzixun extends BaseItem {
    public MImageView mImageView;
    public TextView mTextView_title;
    public TextView mTextView_src;
    public TextView mTextView_time;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_xinwenzixun, null);
        convertView.setTag(new Xinwenzixun(convertView));
        return convertView;
    }

    public Xinwenzixun(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mImageView = (MImageView) contentview.findViewById(R.id.mImageView);
        mTextView_title = (TextView) contentview.findViewById(R.id.mTextView_title);
        mTextView_src = (TextView) contentview.findViewById(R.id.mTextView_src);
        mTextView_time = (TextView) contentview.findViewById(R.id.mTextView_time);


    }

    public void set(MNews item) {
        mImageView.setObj(item.img);
        mTextView_title.setText(item.title);
        mTextView_src.setText("作者：" + item.author);
        mTextView_time.setText(item.createTime);
    }


}