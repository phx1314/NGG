//
//  FrgNyq
//
//  Created by df on 2017-02-10 13:54:39
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfNyq;
import com.udows.common.proto.ApisFactory;


public class FrgNyq extends BaseFrg {

    public RelativeLayout clk_mRelativeLayout_1;
    public TextView mTextView_1;
    public ImageView mImageView_1;
    public RelativeLayout clk_mRelativeLayout_2;
    public TextView mTextView_2;
    public ImageView mImageView_2;
    public ImageView mImageView_wode;
    public MPageListView mMPageListView;
    public ImageView mImageView_back;
    public Runnable run;
    public Handler mHandler = new Handler();
    public double type = 2;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_nyq);
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
        mImageView_wode = (ImageView) findViewById(R.id.mImageView_wode);
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);
        mImageView_back = (ImageView) findViewById(R.id.mImageView_back);

        clk_mRelativeLayout_1.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mRelativeLayout_2.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mImageView_wode.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mImageView_back.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void loaddata() {
        run = new Runnable() {
            @Override
            public void run() {
                mMPageListView.setApiUpdate(ApisFactory.getApiSFriendCircles().set(type));
                mMPageListView.setDataFormat(new DfNyq((int)type));
                mMPageListView.pullLoad();
            }
        };
        mHandler.postDelayed(run, 400);
    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mRelativeLayout_1 == v.getId()) {
            type = 2;
            mHandler.removeCallbacks(run);
            mHandler.postDelayed(run, 400);
            mImageView_2.setVisibility(View.GONE);
            mImageView_1.setVisibility(View.VISIBLE);
        } else if (R.id.clk_mRelativeLayout_2 == v.getId()) {
            type = 3;
            mHandler.removeCallbacks(run);
            mHandler.postDelayed(run, 400);
            mImageView_2.setVisibility(View.VISIBLE);
            mImageView_1.setVisibility(View.GONE);
        } else if (R.id.mImageView_wode == v.getId()) {
            Helper.startActivity(getActivity(), FrgWodeDt.class, TitleAct.class);
        } else if (R.id.mImageView_back == v.getId()) {
            this.finish();
        }
    }

}