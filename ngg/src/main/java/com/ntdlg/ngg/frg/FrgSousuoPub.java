//
//  FrgSousuoPub
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfShouye;
import com.ntdlg.ngg.dataformat.DfSousuoJishu;
import com.ntdlg.ngg.dataformat.DfSousuoShangpin;
import com.ntdlg.ngg.dataformat.DfSousuoZhuanjia;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MRet;


public class FrgSousuoPub extends BaseFrg {

    public TextView clk_mTextView_sousuo;
    public RelativeLayout clk_mRelativeLayout_xiaoxi;
    public ImageView mImageView_xiaoxi;
    public ImageView mImageView_xiaoxi_dot;
    public MPageListView mMPageListView;
    public MPageListView mMPageListView_wenti;
    public int type;
    public String key;
    public ImageView mImageView_back;

    @Override
    protected void create(Bundle savedInstanceState) {
        type = getActivity().getIntent().getIntExtra("type", 1);
        key = getActivity().getIntent().getStringExtra("key");
        setContentView(R.layout.frg_sousuo_pub);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 1://消息
                ApisFactory.getApiMCountNotify().load(getContext(), this, "MCountNotify");
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        clk_mTextView_sousuo = (TextView) findViewById(R.id.clk_mTextView_sousuo);
        clk_mRelativeLayout_xiaoxi = (RelativeLayout) findViewById(R.id.clk_mRelativeLayout_xiaoxi);
        mImageView_xiaoxi = (ImageView) findViewById(R.id.mImageView_xiaoxi);
        mImageView_xiaoxi_dot = (ImageView) findViewById(R.id.mImageView_xiaoxi_dot);
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);
        mMPageListView_wenti = (MPageListView) findViewById(R.id.mMPageListView_wenti);
        mImageView_back = (ImageView) findViewById(R.id.mImageView_back);

        clk_mTextView_sousuo.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mRelativeLayout_xiaoxi.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mImageView_back.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void loaddata() {
        if (type == 1) {//问题
            mMPageListView.setApiUpdate(ApisFactory.getApiMSearchQues().set(key));
            mMPageListView.setDataFormat(new DfShouye());
            mMPageListView.pullLoad();
        } else if (type == 2) {//商品
            mMPageListView.setApiUpdate(ApisFactory.getApiMSearchGoods().set(key));
            mMPageListView.setDataFormat(new DfSousuoShangpin());
            mMPageListView.pullLoad();
        } else if (type == 3) {//技术
            mMPageListView.setApiUpdate(ApisFactory.getApiMCropTechList().set(null, 0.0, key));
            mMPageListView.setDataFormat(new DfSousuoJishu());
            mMPageListView.pullLoad();
        } else if (type == 4) {//专家
            mMPageListView.setApiUpdate(ApisFactory.getApiMSearchPro().set(key));
            mMPageListView.setDataFormat(new DfSousuoZhuanjia());
            mMPageListView.pullLoad();
        }
        ApisFactory.getApiMCountNotify().load(getContext(), this, "MCountNotify");
    }

    public void MCountNotify(Son s) {
        MRet mMRet = (MRet) s.getBuild();
        if (mMRet.code > 0) {
            mImageView_xiaoxi_dot.setVisibility(View.VISIBLE);
        } else {
            mImageView_xiaoxi_dot.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mTextView_sousuo == v.getId() || R.id.mImageView_back == v.getId()) {
            this.finish();
        } else if (R.id.clk_mRelativeLayout_xiaoxi == v.getId()) {
            Helper.startActivity(getContext(), FrgWodeXiaoxi.class, TitleAct.class);
        } else if (R.id.mImageView_back == v.getId()) {
            this.finish();
        }
    }

}