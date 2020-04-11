//
//  SousuoZhuanjia
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

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.frg.FrgTiwenti;
import com.udows.common.proto.SUser;


public class SousuoZhuanjia extends BaseItem {
    public MImageView mMImageView;
    public TextView mTextView_name;
    public TextView mTextView_type;
    public TextView mTextView_remark;
    public TextView mTextView_tiwen;
    public SUser item;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_sousuo_zhuanjia, null);
        convertView.setTag(new SousuoZhuanjia(convertView));
        return convertView;
    }

    public SousuoZhuanjia(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mMImageView = (MImageView) contentview.findViewById(R.id.mMImageView);
        mTextView_name = (TextView) contentview.findViewById(R.id.mTextView_name);
        mTextView_type = (TextView) contentview.findViewById(R.id.mTextView_type);
        mTextView_remark = (TextView) contentview.findViewById(R.id.mTextView_remark);
        mTextView_tiwen = (TextView) findViewById(R.id.mTextView_tiwen);

        mTextView_tiwen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(context, FrgTiwenti.class, TitleAct.class, "fid", item.id);
            }
        });
    }

    public void set(SUser item) {
        this.item = item;
        mMImageView.setObj(item.headImg);
        mMImageView.setCircle(true);
        mTextView_name.setText(item.nickName);
        mTextView_type.setText(item.proType);
        mTextView_remark.setText(item.crop);
    }


}