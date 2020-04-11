//
//  ChaContent
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.ntdlg.ngg.R;


public class ChaContent extends BaseItem {
    public TextView mTextView;
    public int position;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_cha_content, null);
        convertView.setTag(new ChaContent(convertView));
        return convertView;
    }

    public ChaContent(View view) {
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
                Frame.HANDLES.sentAll("FrgZhenweichaxun", 0, position);
            }
        });
    }

    public void reFrash(int position) {
        if (this.position == position) {
            mTextView.setTextColor(Color.parseColor("#ffffff"));
            mTextView.setBackgroundResource(R.drawable.bt_blue_n);
        } else {
            mTextView.setTextColor(context.getResources().getColor(R.color.E4));
            mTextView.setBackgroundResource(R.drawable.bt_order_l_g_n);
        }
    }

    public void set(String item, int position) {
        mTextView.setText(item);
        this.position = position;
    }


}