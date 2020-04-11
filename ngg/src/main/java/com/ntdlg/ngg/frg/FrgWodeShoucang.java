//
//  FrgWodeShoucang
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
import com.ntdlg.ngg.dataformat.DfShouye;
import com.ntdlg.ngg.dataformat.DfSousuoJishu;
import com.ntdlg.ngg.dataformat.DfSousuoShangpin;
import com.udows.common.proto.ApisFactory;


public class FrgWodeShoucang extends BaseFrg {

    public RelativeLayout clk_mRelativeLayout_wen;
    public TextView mTextView_wen;
    public ImageView mImageView_1;
    public RelativeLayout clk_mRelativeLayout_da;
    public TextView mTextView_da;
    public ImageView mImageView_2;
    public RelativeLayout clk_mRelativeLayout_shangpin;
    public TextView mTextView_shp;
    public ImageView mImageView_3;
    public MPageListView mMPageListView;

    public Handler mHandler = new Handler();
    public Runnable mRunnable;
    public int type = 1;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_wode_shoucang);
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
        clk_mRelativeLayout_wen = (RelativeLayout) findViewById(R.id.clk_mRelativeLayout_wen);
        mTextView_wen = (TextView) findViewById(R.id.mTextView_wen);
        mImageView_1 = (ImageView) findViewById(R.id.mImageView_1);
        clk_mRelativeLayout_da = (RelativeLayout) findViewById(R.id.clk_mRelativeLayout_da);
        mTextView_da = (TextView) findViewById(R.id.mTextView_da);
        mImageView_2 = (ImageView) findViewById(R.id.mImageView_2);
        clk_mRelativeLayout_shangpin = (RelativeLayout) findViewById(R.id.clk_mRelativeLayout_shangpin);
        mTextView_shp = (TextView) findViewById(R.id.mTextView_shp);
        mImageView_3 = (ImageView) findViewById(R.id.mImageView_3);
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);

        clk_mRelativeLayout_wen.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mRelativeLayout_da.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mRelativeLayout_shangpin.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void loaddata() {
        mMPageListView.setDataFormat(new DfShouye());
        mMPageListView.setApiUpdate(ApisFactory.getApiMUserCollectTopicList().set());
        mMPageListView.pullLoad();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                if (type == 1) {
                    mMPageListView.setDataFormat(new DfShouye());
                    mMPageListView.setApiUpdate(ApisFactory.getApiMUserCollectTopicList().set());
                    mMPageListView.pullLoad();
                } else if (type == 2) {
                    mMPageListView.setDataFormat(new DfSousuoJishu());
                    mMPageListView.setApiUpdate(ApisFactory.getApiMUserCollectArticleList().set());
                    mMPageListView.pullLoad();
                } else {
                    mMPageListView.setDataFormat(new DfSousuoShangpin());
                    mMPageListView.setApiUpdate(ApisFactory.getApiMUserCOllectGoodsList().set());
                    mMPageListView.pullLoad();
                }
            }
        };
    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mRelativeLayout_wen == v.getId()) {
            type = 1;
            mHandler.removeCallbacks(mRunnable);
            mHandler.postDelayed(mRunnable, 400);
            mTextView_wen.setTextColor(getResources().getColor(R.color.A));
            mTextView_da.setTextColor(getResources().getColor(R.color.E4));
            mTextView_shp.setTextColor(getResources().getColor(R.color.E4));
            mImageView_1.setVisibility(View.VISIBLE);
            mImageView_2.setVisibility(View.INVISIBLE);
            mImageView_3.setVisibility(View.INVISIBLE);
        } else if (R.id.clk_mRelativeLayout_da == v.getId()) {
            type = 2;
            mHandler.removeCallbacks(mRunnable);
            mHandler.postDelayed(mRunnable, 400);
            mTextView_wen.setTextColor(getResources().getColor(R.color.E4));
            mTextView_da.setTextColor(getResources().getColor(R.color.A));
            mTextView_shp.setTextColor(getResources().getColor(R.color.E4));
            mImageView_1.setVisibility(View.INVISIBLE);
            mImageView_2.setVisibility(View.VISIBLE);
            mImageView_3.setVisibility(View.INVISIBLE);
        } else if (R.id.clk_mRelativeLayout_shangpin == v.getId()) {
            type = 3;
            mHandler.removeCallbacks(mRunnable);
            mHandler.postDelayed(mRunnable, 400);
            mTextView_wen.setTextColor(getResources().getColor(R.color.E4));
            mTextView_da.setTextColor(getResources().getColor(R.color.E4));
            mTextView_shp.setTextColor(getResources().getColor(R.color.A));
            mImageView_1.setVisibility(View.INVISIBLE);
            mImageView_2.setVisibility(View.INVISIBLE);
            mImageView_3.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("我的收藏");
    }
}