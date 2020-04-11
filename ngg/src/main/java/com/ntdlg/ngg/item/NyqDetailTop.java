//
//  NyqDetailTop
//
//  Created by df on 2017-02-10 14:01:15
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.framewidget.view.FixGridLayout;
import com.mdx.framework.dialog.PhotoShow;
import com.ntdlg.ngg.R;
import com.udows.common.proto.STopic;

import java.util.Arrays;


public class NyqDetailTop extends BaseItem {
    public TextView mTextView_name;
    public TextView mTextView_content;
    public FixGridLayout mFixGridLayout;
    public TextView mTextView_time;
    public TextView mTextView_kan;
    private String data[] = null;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_nyq_detail_top, null);
        convertView.setTag(new NyqDetailTop(convertView));
        return convertView;
    }

    public NyqDetailTop(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_name = (TextView) contentview.findViewById(R.id.mTextView_name);
        mTextView_content = (TextView) contentview.findViewById(R.id.mTextView_content);
        mFixGridLayout = (FixGridLayout) contentview.findViewById(R.id.mFixGridLayout);
        mTextView_time = (TextView) contentview.findViewById(R.id.mTextView_time);
        mTextView_kan = (TextView) contentview.findViewById(R.id.mTextView_kan);


    }

    public void set(STopic item) {
        mTextView_name.setText(item.lz.nickName);
        mTextView_content.setText(item.content);
        mTextView_time.setText(item.time + " " + item.address);
//        if (item.lz.id.equals(F.UserId)) {
//            mTextView_time.setText(item.time + " " + item.address + " 删除");
//        } else {
//            mTextView_time.setText(item.time + " " + item.address);
//        }
        if (!TextUtils.isEmpty(item.imgs)) {
            mFixGridLayout.removeAllViews();
            mFixGridLayout.setVisibility(View.VISIBLE);
            data = item.imgs.split(",");
            for (int i = 0; i < data.length; i++) {
                View view = ShouyeImg.getView(context, null);
                mFixGridLayout.addView(view);
                ((ShouyeImg) view.getTag()).set(data[i]);
                final int position = i;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        PhotoShow mPhotoShow = new PhotoShow(context, Arrays
                                .asList(data), data[position]);
                        mPhotoShow.show();
                    }
                });
            }
        } else {
            mFixGridLayout.setVisibility(View.GONE);
            data = null;
        }
//        mTextView_kan.setText(item.);
    }


}