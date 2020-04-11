//
//  ShouyeTop
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.framewidget.frg.FrgPtDetail;
import com.framewidget.view.DfCirleCurr;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.ada.AdaBanner;
import com.ntdlg.ngg.frg.FrgTiwenti;
import com.ntdlg.ngg.frg.FrgWenZhuanjia;
import com.ntdlg.ngg.frg.FrgWendalistAll;
import com.ntdlg.ngg.frg.FrgXinwenzixun;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MFocusList;
import com.udows.common.proto.MNews;
import com.udows.common.proto.MNewsList;
import com.udows.common.proto.MNotify;
import com.udows.common.proto.MNotifyList;
import com.udows.common.proto.apis.ApiMNewsList;


public class ShouyeTop extends BaseItem {
    public DfCirleCurr mDfCirleCurr;
    public LinearLayout clk_mLinearLayout_wzj;
    public LinearLayout clk_mLinearLayout_kstw;
    public LinearLayout mLinearLayout_zixun;
    public LinearLayout mLinearLayout_rm;
    public ViewFlipper mViewFlipper;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_shouye_top, null);
        convertView.setTag(new ShouyeTop(convertView));
        return convertView;
    }

    public ShouyeTop(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    public void reload() {
//        ApisFactory.getApiMIndexNewsList().load(context, this, "MIndexNewsList");
        ApiMNewsList mApiMNewsList = ApisFactory.getApiMNewsList();
        mApiMNewsList.setHasPage(true);
        mApiMNewsList.setPageSize(Integer.MAX_VALUE);
        mApiMNewsList.load(context, this, "MNewsList", 1.0);
        ApisFactory.getApiMFocusList().load(context, this, "MFocusList", null, 1.0);
    }

    public void MIndexNewsList(Son s) {
        MNotifyList mMNotifyList = (MNotifyList) s.getBuild();
        for (final MNotify mMNotify : mMNotifyList.notify) {
            TextView mTextView = new TextView(context);
            mTextView.setText(mMNotify.title);
            mTextView.setSingleLine();
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (mMNotify.redirectType) {
                        case 0:

                            break;
                        case 1:
                            Helper.startActivity(context, FrgPtDetail.class,
                                    NoTitleAct.class, "url",
                                    mMNotify.redirectContent);
                            break;
                    }
                }
            });
            mViewFlipper.addView(mTextView);
        }
    }

    public void MNewsList(Son s) {
        MNewsList mMNotifyList = (MNewsList) s.getBuild();
        for (final MNews mMNotify : mMNotifyList.news) {
            TextView mTextView = new TextView(context);
            mTextView.setText(mMNotify.title);
            mTextView.setSingleLine();
            mViewFlipper.addView(mTextView);
        }
    }

    public void MFocusList(Son s) {
        MFocusList mMFocusList = (MFocusList) s.getBuild();
        mDfCirleCurr.setAdapter(new AdaBanner(context, mMFocusList.list));
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mDfCirleCurr = (DfCirleCurr) contentview.findViewById(R.id.mDfCirleCurr);
        clk_mLinearLayout_wzj = (LinearLayout) contentview.findViewById(R.id.clk_mLinearLayout_wzj);
        clk_mLinearLayout_kstw = (LinearLayout) contentview.findViewById(R.id.clk_mLinearLayout_kstw);
        mLinearLayout_zixun = (LinearLayout) contentview.findViewById(R.id.mLinearLayout_zixun);
        mLinearLayout_rm = (LinearLayout) contentview.findViewById(R.id.mLinearLayout_rm);
        mViewFlipper = (ViewFlipper) findViewById(R.id.mViewFlipper);
        mDfCirleCurr.setFillColor(context.getResources().getColor(R.color.A));
        mDfCirleCurr.setPageColor(context.getResources().getColor(R.color.gray));
        clk_mLinearLayout_wzj.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout_kstw.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mLinearLayout_zixun.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mLinearLayout_rm.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
    }

    public void set(String item) {

    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mLinearLayout_wzj == v.getId()) {
            Helper.startActivity(context, FrgWenZhuanjia.class, NoTitleAct.class);
        } else if (R.id.clk_mLinearLayout_kstw == v.getId()) {
            Helper.startActivity(context, FrgTiwenti.class, TitleAct.class);
        } else if (R.id.mLinearLayout_zixun == v.getId()) {
            Helper.startActivity(context, FrgXinwenzixun.class, TitleAct.class);
        } else if (R.id.mLinearLayout_rm == v.getId()) {
            Helper.startActivity(context, FrgWendalistAll.class, TitleAct.class);
        }
    }

}