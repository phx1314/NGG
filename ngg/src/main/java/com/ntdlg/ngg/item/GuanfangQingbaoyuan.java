//
//  GuanfangQingbaoyuan
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

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.frg.FrgFabu;
import com.udows.common.proto.MOfficeComment;
import com.udows.common.proto.MOfficeCommentAll;


public class GuanfangQingbaoyuan extends BaseItem {
    public TextView mTextView_tilte;
    public TextView mTextView_sn;
    public TextView mTextView_pj;
    public TextView mTextView_xinxi;
    public TextView mTextView_time;
    public ImageView mImageView_fabu;
    public MOfficeComment item;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_guanfang_qingbaoyuan, null);
        convertView.setTag(new GuanfangQingbaoyuan(convertView));
        return convertView;
    }

    public GuanfangQingbaoyuan(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_tilte = (TextView) contentview.findViewById(R.id.mTextView_tilte);
        mTextView_sn = (TextView) contentview.findViewById(R.id.mTextView_sn);
        mTextView_pj = (TextView) contentview.findViewById(R.id.mTextView_pj);
        mTextView_xinxi = (TextView) contentview.findViewById(R.id.mTextView_xinxi);
        mTextView_time = (TextView) contentview.findViewById(R.id.mTextView_time);
        mImageView_fabu = (ImageView) findViewById(R.id.mImageView_fabu);
        mImageView_fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(context, FrgFabu.class, TitleAct.class, "name", item.name);
            }
        });

    }

    public void set(MOfficeComment item) {
        this.item = item;
        mTextView_tilte.setText(item.name);
        mTextView_sn.setText(item.sn);
        mTextView_pj.setText(item.comment);
        mTextView_xinxi.setText("官方员信息：" + item.userName);
        mTextView_time.setText(item.time);
    }

    public void set(MOfficeCommentAll item) {
        mImageView_fabu.setVisibility(View.GONE);
        if (item.mini.size() > 0) {
            mTextView_tilte.setText(item.mini.get(0).name);
            mTextView_sn.setText(item.mini.get(0).sn);
            mTextView_pj.setText(item.mini.get(0).comment);
            mTextView_xinxi.setText("官方员信息：" + item.mini.get(0).userName);
            mTextView_time.setText(item.mini.get(0).time);
        }
    }


}