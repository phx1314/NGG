//
//  GongjuTop
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

import com.framewidget.view.DfCirleCurr;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.ada.AdaBanner;
import com.ntdlg.ngg.frg.FrgFujin;
import com.ntdlg.ngg.frg.FrgNyHqfenlei;
import com.ntdlg.ngg.frg.FrgNyq;
import com.ntdlg.ngg.frg.FrgTiwenti;
import com.ntdlg.ngg.frg.FrgTuwennongji;
import com.ntdlg.ngg.frg.FrgXinwenzixun;
import com.ntdlg.ngg.frg.FrgZacaoList;
import com.ntdlg.ngg.frg.FrgZhenweichaxun;
import com.ntdlg.ngg.frg.FrgZhuanjiaShangmen;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MFocusList;


public class GongjuTop extends BaseItem {
    public TextView mTextView_1;
    public TextView mTextView_2;
    public TextView mTextView_3;
    public TextView mTextView_4;
    public TextView mTextView_5;
    public TextView mTextView_6;
    public TextView mTextView_7;
    public TextView mTextView_8;
    public LinearLayout clk_mLinearLayout_wzh;
    public LinearLayout clk_mLinearLayout_kstw;
    public LinearLayout clk_mLinearLayout_chaxun;
    public DfCirleCurr mDfCirleCurr;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_gongju_top, null);
        convertView.setTag(new GongjuTop(convertView));
        return convertView;
    }

    public GongjuTop(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_1 = (TextView) contentview.findViewById(R.id.mTextView_1);
        mTextView_2 = (TextView) contentview.findViewById(R.id.mTextView_2);
        mTextView_3 = (TextView) contentview.findViewById(R.id.mTextView_3);
        mTextView_4 = (TextView) contentview.findViewById(R.id.mTextView_4);
        mTextView_5 = (TextView) contentview.findViewById(R.id.mTextView_5);
        mTextView_6 = (TextView) contentview.findViewById(R.id.mTextView_6);
        mTextView_7 = (TextView) contentview.findViewById(R.id.mTextView_7);
        mTextView_8 = (TextView) contentview.findViewById(R.id.mTextView_8);
        clk_mLinearLayout_wzh = (LinearLayout) contentview.findViewById(R.id.clk_mLinearLayout_wzh);
        clk_mLinearLayout_kstw = (LinearLayout) contentview.findViewById(R.id.clk_mLinearLayout_kstw);
        clk_mLinearLayout_chaxun = (LinearLayout) contentview.findViewById(R.id.clk_mLinearLayout_chaxun);
        mDfCirleCurr = (DfCirleCurr) contentview.findViewById(R.id.mDfCirleCurr);

        clk_mLinearLayout_wzh.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout_kstw.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout_chaxun.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_1.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_2.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_3.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_4.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_5.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_6.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_7.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_8.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
    }

    public void MFocusList(Son s) {
        MFocusList mMFocusList = (MFocusList) s.getBuild();
        mDfCirleCurr.setAdapter(new AdaBanner(context, mMFocusList.list));
    }

    public void set() {
        ApisFactory.getApiMFocusList().load(context, this, "MFocusList", null, 2.0);
    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mLinearLayout_wzh == v.getId()) {
            Helper.startActivity(context, FrgZhuanjiaShangmen.class, TitleAct.class);
        } else if (R.id.clk_mLinearLayout_kstw == v.getId()) {
            Helper.startActivity(context, FrgTiwenti.class, TitleAct.class);
        } else if (R.id.clk_mLinearLayout_chaxun == v.getId()) {
            Helper.startActivity(context, FrgZhenweichaxun.class, TitleAct.class);
        } else if (R.id.mTextView_1 == v.getId()) {
            Helper.startActivity(context, FrgFujin.class, NoTitleAct.class, "type", 1, "mlat", F.latitude + "", "mlng", F.longitude + "");
        } else if (R.id.mTextView_2 == v.getId()) {
            Helper.startActivity(context, FrgXinwenzixun.class, TitleAct.class, "type", 2.0);
        } else if (R.id.mTextView_3 == v.getId()) {
            Helper.startActivity(context, FrgNyHqfenlei.class, TitleAct.class, "title", "农药全书");
        } else if (R.id.mTextView_4 == v.getId()) {
            Helper.toast("开发中",context);
//            Helper.startActivity(context, FrgNzHq.class, NoTitleAct.class);
        } else if (R.id.mTextView_5 == v.getId()) {
            Helper.startActivity(context, FrgTuwennongji.class,  TitleAct.class);
        } else if (R.id.mTextView_6 == v.getId()) {
            Helper.startActivity(context, FrgNyq.class, NoTitleAct.class);
        } else if (R.id.mTextView_7 == v.getId()) {
            Helper.startActivity(context, FrgNyHqfenlei.class, TitleAct.class, "title", "农药专利");
        } else if (R.id.mTextView_8 == v.getId()) {
            Helper.startActivity(context, FrgZacaoList.class, TitleAct.class);
        }
    }

}