//
//  WodeXiaoxi
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
import android.widget.ImageView;
import android.widget.TextView;

import com.ntdlg.ngg.R;
import com.udows.common.proto.MNotify;


public class WodeXiaoxi extends BaseItem {
    public TextView mTextView_state;
    public TextView mTextView_time;
    public TextView mTextView_ddh;
    public ImageView mImageView;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_wode_xiaoxi, null);
        convertView.setTag(new WodeXiaoxi(convertView));
        return convertView;
    }

    public WodeXiaoxi(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_state = (TextView) contentview.findViewById(R.id.mTextView_state);
        mTextView_time = (TextView) contentview.findViewById(R.id.mTextView_time);
        mTextView_ddh = (TextView) contentview.findViewById(R.id.mTextView_ddh);
        mImageView = (ImageView) findViewById(R.id.mImageView);


    }

    public void set(MNotify item) {
        mTextView_state.setText(item.title);
        mTextView_time.setText(item.createTime);
        mTextView_ddh.setText(item.content);
        if (item.isRead == 0) {
            mTextView_state.setTextColor(context.getResources().getColor(R.color.black));
        } else {
            mTextView_state.setTextColor(context.getResources().getColor(R.color.gray));
        }
        switch (item.redirectType) {
            case 0:

                break;
            case 2:
                mImageView.setImageResource(R.drawable.ic_wdxx);
                break;
            case 3:
                mImageView.setImageResource(R.drawable.ic_xtxx);
                break;
            case 4:
                mImageView.setImageResource(R.drawable.ic_xtxx);
                break;
            case 1:
                mImageView.setImageResource(R.drawable.ic_tsxx);
                break;
        }
    }


}