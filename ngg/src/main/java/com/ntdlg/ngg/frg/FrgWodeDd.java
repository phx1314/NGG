//
//  FrgWodeDd
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfWodeDd;
import com.udows.common.proto.ApisFactory;


public class FrgWodeDd extends BaseFrg {

    public RelativeLayout clk_mRelativeLayout_1;
    public TextView mTextView_1;
    public ImageView mImageView_1;
    public RelativeLayout clk_mRelativeLayout_2;
    public TextView mTextView_2;
    public ImageView mImageView_2;
    public RelativeLayout clk_mRelativeLayout_3;
    public TextView mTextView_3;
    public ImageView mImageView_3;
    public RelativeLayout clk_mRelativeLayout_4;
    public TextView mTextView_4;
    public ImageView mImageView_4;
    public MPageListView mMPageListView;

    public Handler mHandler = new Handler();
    public Runnable mRunnable;
    public double type = 0;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_wode_dd);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                mMPageListView.pullLoad();
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        clk_mRelativeLayout_1 = (RelativeLayout) findViewById(R.id.clk_mRelativeLayout_1);
        mTextView_1 = (TextView) findViewById(R.id.mTextView_1);
        mImageView_1 = (ImageView) findViewById(R.id.mImageView_1);
        clk_mRelativeLayout_2 = (RelativeLayout) findViewById(R.id.clk_mRelativeLayout_2);
        mTextView_2 = (TextView) findViewById(R.id.mTextView_2);
        mImageView_2 = (ImageView) findViewById(R.id.mImageView_2);
        clk_mRelativeLayout_3 = (RelativeLayout) findViewById(R.id.clk_mRelativeLayout_3);
        mTextView_3 = (TextView) findViewById(R.id.mTextView_3);
        mImageView_3 = (ImageView) findViewById(R.id.mImageView_3);
        clk_mRelativeLayout_4 = (RelativeLayout) findViewById(R.id.clk_mRelativeLayout_4);
        mTextView_4 = (TextView) findViewById(R.id.mTextView_4);
        mImageView_4 = (ImageView) findViewById(R.id.mImageView_4);
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);

        clk_mRelativeLayout_1.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mRelativeLayout_2.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mRelativeLayout_3.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mRelativeLayout_4.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void loaddata() {
        mMPageListView.setDataFormat(new DfWodeDd());
        mMPageListView.setApiUpdate(ApisFactory.getApiMMyOrderList().set(type));
        mMPageListView.pullLoad();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                mMPageListView.setApiUpdate(ApisFactory.getApiMMyOrderList().set(type));
                mMPageListView.pullLoad();
            }
        };
    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mRelativeLayout_1 == v.getId()) {
            type = 0.0;
            mHandler.removeCallbacks(mRunnable);
            mHandler.postDelayed(mRunnable, 400);
            mTextView_1.setTextColor(getResources().getColor(R.color.A));
            mTextView_2.setTextColor(getResources().getColor(R.color.E4));
            mTextView_3.setTextColor(getResources().getColor(R.color.E4));
            mTextView_4.setTextColor(getResources().getColor(R.color.E4));
            mImageView_1.setVisibility(View.VISIBLE);
            mImageView_2.setVisibility(View.INVISIBLE);
            mImageView_3.setVisibility(View.INVISIBLE);
            mImageView_4.setVisibility(View.INVISIBLE);
        } else if (R.id.clk_mRelativeLayout_2 == v.getId()) {
            type = 1.0;
            mHandler.removeCallbacks(mRunnable);
            mHandler.postDelayed(mRunnable, 400);
            mTextView_1.setTextColor(getResources().getColor(R.color.E4));
            mTextView_2.setTextColor(getResources().getColor(R.color.A));
            mTextView_3.setTextColor(getResources().getColor(R.color.E4));
            mTextView_4.setTextColor(getResources().getColor(R.color.E4));
            mImageView_1.setVisibility(View.INVISIBLE);
            mImageView_2.setVisibility(View.VISIBLE);
            mImageView_3.setVisibility(View.INVISIBLE);
            mImageView_4.setVisibility(View.INVISIBLE);
        } else if (R.id.clk_mRelativeLayout_3 == v.getId()) {
            type = 2.0;
            mHandler.removeCallbacks(mRunnable);
            mHandler.postDelayed(mRunnable, 400);
            mTextView_1.setTextColor(getResources().getColor(R.color.E4));
            mTextView_2.setTextColor(getResources().getColor(R.color.E4));
            mTextView_3.setTextColor(getResources().getColor(R.color.A));
            mTextView_4.setTextColor(getResources().getColor(R.color.E4));
            mImageView_1.setVisibility(View.INVISIBLE);
            mImageView_2.setVisibility(View.INVISIBLE);
            mImageView_3.setVisibility(View.VISIBLE);
            mImageView_4.setVisibility(View.INVISIBLE);
        } else if (R.id.clk_mRelativeLayout_4 == v.getId()) {
            type = 3.0;
            mHandler.removeCallbacks(mRunnable);
            mHandler.postDelayed(mRunnable, 400);
            mTextView_1.setTextColor(getResources().getColor(R.color.E4));
            mTextView_2.setTextColor(getResources().getColor(R.color.E4));
            mTextView_3.setTextColor(getResources().getColor(R.color.E4));
            mTextView_4.setTextColor(getResources().getColor(R.color.A));
            mImageView_1.setVisibility(View.INVISIBLE);
            mImageView_2.setVisibility(View.INVISIBLE);
            mImageView_3.setVisibility(View.INVISIBLE);
            mImageView_4.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("我的订单");
    }
}