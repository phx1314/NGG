//
//  NyfenleLeft
//
//  Created by Administrator on 2017-04-08 12:41:48
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

import com.mdx.framework.Frame;
import com.ntdlg.ngg.R;
import com.udows.common.proto.MCategory;


public class NyfenleLeft extends BaseItem {
    public TextView mTextView;
    public MCategory item;
    public int i;
    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_nyfenle_left, null);
        convertView.setTag(new NyfenleLeft(convertView));
        return convertView;
    }

    public NyfenleLeft(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView = (TextView) contentview.findViewById(R.id.mTextView);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Frame.HANDLES.sentAll("FrgNyHqfenlei", 0, i);
            }
        });

    }

    public void set(MCategory item, int position, int i) {
        this.item = item;
        this.i = i;
        mTextView.setText(item.name);
        if (i == position) {
            mTextView.setTextColor(context.getResources().getColor(R.color.B));
        } else {
            mTextView.setTextColor(context.getResources().getColor(R.color.black));
        }
    }


}