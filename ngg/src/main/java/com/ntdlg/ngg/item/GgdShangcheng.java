//
//  GgdShangcheng
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

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.frg.FrgShangchengDetail;
import com.ntdlg.ngg.view.ModelShangCheng;


public class GgdShangcheng extends BaseItem {
    public MImageView mMImageView_1;
    public MImageView mMImageView_2;
    public ModelShangCheng mModelShangCheng;
    public LinearLayout mLinearLayout1;
    public LinearLayout mLinearLayout2;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_ggd_shangcheng, null);
        convertView.setTag(new GgdShangcheng(convertView));
        return convertView;
    }

    public GgdShangcheng(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mMImageView_1 = (MImageView) contentview.findViewById(R.id.mMImageView_1);
        mMImageView_2 = (MImageView) contentview.findViewById(R.id.mMImageView_2);
        mLinearLayout1 = (LinearLayout) findViewById(R.id.mLinearLayout1);
        mLinearLayout2 = (LinearLayout) findViewById(R.id.mLinearLayout2);
        mMImageView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(context, FrgShangchengDetail.class,  TitleAct.class,"mMCreditGoods",mModelShangCheng.mMCreditGoods1);
            }
        });
        mLinearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(context, FrgShangchengDetail.class,  TitleAct.class,"mMCreditGoods",mModelShangCheng.mMCreditGoods2);
            }
        });

    }

    public void set(ModelShangCheng mModelShangCheng) {
        this.mModelShangCheng = mModelShangCheng;
        mMImageView_1.setObj(mModelShangCheng.mMCreditGoods1.img);
        if (mModelShangCheng.mMCreditGoods2 != null) {
            mLinearLayout2.setVisibility(View.VISIBLE);
            mMImageView_2.setObj(mModelShangCheng.mMCreditGoods2.img);
        } else {
            mLinearLayout2.setVisibility(View.INVISIBLE);
        }

    }


}