//
//  GuanfangQingbaoyuanTop
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

import com.mdx.framework.Frame;
import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.R;
import com.udows.common.proto.MOfficeUser;


public class GuanfangQingbaoyuanTop extends BaseItem {
    public ImageView mImageView_back;
    public MImageView mImageView_touxiang;
    public TextView mTextView_name;
    public ImageView mImageView_zhuanjia;
    public TextView mTextView_gongzuodanwei;
    public TextView mTextView_nianxiang;
    public TextView mTextView_chanpin;
    public TextView mTextView_jianli;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_guanfang_qingbaoyuan_top, null);
        convertView.setTag(new GuanfangQingbaoyuanTop(convertView));
        return convertView;
    }

    public GuanfangQingbaoyuanTop(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mImageView_back = (ImageView) contentview.findViewById(R.id.mImageView_back);
        mImageView_touxiang = (MImageView) contentview.findViewById(R.id.mImageView_touxiang);
        mTextView_name = (TextView) contentview.findViewById(R.id.mTextView_name);
        mImageView_zhuanjia = (ImageView) contentview.findViewById(R.id.mImageView_zhuanjia);
        mTextView_gongzuodanwei = (TextView) contentview.findViewById(R.id.mTextView_gongzuodanwei);
        mTextView_nianxiang = (TextView) contentview.findViewById(R.id.mTextView_nianxiang);
        mTextView_chanpin = (TextView) contentview.findViewById(R.id.mTextView_chanpin);
        mTextView_jianli = (TextView) contentview.findViewById(R.id.mTextView_jianli);
        mImageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Frame.HANDLES.sentAll("FrgGuanfangQingbaoyuan", 0, null);
            }
        });

    }

    public void set(MOfficeUser item) {
        mImageView_touxiang.setObj(item.headImg);
        mImageView_touxiang.setCircle(true);
        mTextView_name.setText(item.name);
        mTextView_gongzuodanwei.setText("工作单位：" + item.company);
        mTextView_nianxiang.setText("从业" + item.year + "年");
        mTextView_chanpin.setText("报价产品：" + item.product);
        mTextView_jianli.setText(item.personInfo);
    }


}