//
//  AddZuowuLeft
//
//  Created by df on 2017-03-10 13:50:16
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ntdlg.ngg.R;
import com.ntdlg.ngg.view.ModelZuoWuGaoJi;


public class AddZuowuLeft extends BaseItem {
    public TextView mTextView;
    public LinearLayout mLinearLayout;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_add_zuowu_left, null);
        convertView.setTag(new AddZuowuLeft(convertView));
        return convertView;
    }

    public AddZuowuLeft(View view) {
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
        mLinearLayout = (LinearLayout) findViewById(R.id.mLinearLayout);


    }

    public void set(ModelZuoWuGaoJi item, boolean isXuanZhong) {
        if (isXuanZhong) {
            mLinearLayout.setBackgroundResource(R.drawable.bt_chose_b);
            mLinearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
            mTextView.setTextColor(context.getResources().getColor(R.color.A));
        } else {
            mTextView.setTextColor(context.getResources().getColor(R.color.black));
            mLinearLayout.setBackgroundResource(0);
            mLinearLayout.setBackgroundColor(Color.parseColor("#EEEEEE"));
        }
        mTextView.setText(item.mMCategory.name + "(" + item.num + ")");
    }


}