//
//  WodeGgd
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

import com.ntdlg.ngg.R;
import com.udows.common.proto.MCreditLog;


public class WodeGgd extends BaseItem {
    public TextView mTextView_title;
    public TextView mTextView_time;
    public TextView mTextView_num;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_wode_ggd, null);
        convertView.setTag(new WodeGgd(convertView));
        return convertView;
    }

    public WodeGgd(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_title = (TextView) contentview.findViewById(R.id.mTextView_title);
        mTextView_time = (TextView) contentview.findViewById(R.id.mTextView_time);
        mTextView_num = (TextView) contentview.findViewById(R.id.mTextView_num);


    }

    public void set(MCreditLog item) {
        mTextView_title.setText(item.title);
        mTextView_time.setText(item.time);
        if (item.type == 1) {
            mTextView_num.setText("+" + item.credit);
        } else {
            mTextView_num.setText("-" + item.credit);
        }
    }


}