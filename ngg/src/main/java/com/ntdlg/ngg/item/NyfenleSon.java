//
//  NyfenleSon
//
//  Created by Administrator on 2017-04-08 12:56:49
//  Copyright (c) Administrator All rights reserved.


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

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.frg.FrgNyqshFlDetail;
import com.udows.common.proto.MCategory;


public class NyfenleSon extends BaseItem {
    public TextView mTextView;
    public MCategory item;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_nyfenle_son, null);
        convertView.setTag(new NyfenleSon(convertView));
        return convertView;
    }

    public NyfenleSon(View view) {
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
                if (!TextUtils.isEmpty(item.icon)) {
                    Helper.startActivity(context, FrgNyqshFlDetail.class, TitleAct.class, "url", item.icon);
                } else {
                    Helper.toast("暂无详情", context);
                }
            }
        });
    }

    public void set(MCategory item) {
        this.item = item;
        mTextView.setText(item.name);
    }


}