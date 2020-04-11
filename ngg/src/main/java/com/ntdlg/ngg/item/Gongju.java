//
//  Gongju
//
//  Created by Administrator on 2017-02-04 18:35:42
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.frg.FrgGoodDetail;
import com.ntdlg.ngg.view.ModelGongJu;


public class Gongju extends BaseItem {
    public LinearLayout clk_mLinearLayout1;
    public MImageView mMImageView1;
    public TextView mTextView_title1;
    public TextView mTextView_price1;
    public LinearLayout clk_mLinearLayout2;
    public MImageView mMImageView2;
    public TextView mTextView_title2;
    public TextView mTextView_price2;
    public ModelGongJu modelGongJu;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_gongju, null);
        convertView.setTag(new Gongju(convertView));
        return convertView;
    }

    public Gongju(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        clk_mLinearLayout1 = (LinearLayout) contentview.findViewById(R.id.clk_mLinearLayout1);
        mMImageView1 = (MImageView) contentview.findViewById(R.id.mMImageView1);
        mTextView_title1 = (TextView) contentview.findViewById(R.id.mTextView_title1);
        mTextView_price1 = (TextView) contentview.findViewById(R.id.mTextView_price1);
        clk_mLinearLayout2 = (LinearLayout) contentview.findViewById(R.id.clk_mLinearLayout2);
        mMImageView2 = (MImageView) contentview.findViewById(R.id.mMImageView2);
        mTextView_title2 = (TextView) contentview.findViewById(R.id.mTextView_title2);
        mTextView_price2 = (TextView) contentview.findViewById(R.id.mTextView_price2);

        clk_mLinearLayout1.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout2.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void set(ModelGongJu item) {
        this.modelGongJu = item;
        if (!TextUtils.isEmpty(item.mMScGoods.img)) {
            mMImageView1.setObj(item.mMScGoods.img.split(",")[0]);
        } else {
            mMImageView1.setObj("");
        }
        mTextView_title1.setText(item.mMScGoods.title);
        mTextView_price1.setText(item.mMScGoods.guige);
        if (item.mMScGoods2 != null) {
            if (!TextUtils.isEmpty(item.mMScGoods2.img)) {
                mMImageView2.setObj(item.mMScGoods2.img.split(",")[0]);
            } else {
                mMImageView2.setObj("");
            }
            mTextView_title2.setText(item.mMScGoods2.title);
            mTextView_price2.setText(item.mMScGoods2.guige);
            clk_mLinearLayout2.setVisibility(View.VISIBLE);
        } else {
            clk_mLinearLayout2.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mLinearLayout1 == v.getId()) {
            Helper.startActivity(context, FrgGoodDetail.class, NoTitleAct.class, "id", modelGongJu.mMScGoods.id);
        } else if (R.id.clk_mLinearLayout2 == v.getId()) {
            Helper.startActivity(context, FrgGoodDetail.class, NoTitleAct.class, "id", modelGongJu.mMScGoods2.id);
        }
    }

}