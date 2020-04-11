//
//  FrgShouye
//
//  Created by df on 2016-12-29 15:28:00
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfShouye;
import com.ntdlg.ngg.item.ShouyeTop;
import com.ntdlg.ngg.view.PopShowShouye;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MRet;


public class FrgShouye extends BaseFrg {

    public TextView clk_mTextView_sousuo;
    public RelativeLayout clk_mRelativeLayout_xiaoxi;
    public ImageView mImageView_xiaoxi;
    public ImageView mImageView_xiaoxi_dot;
    public ImageView mImageView_more;
    public MPageListView mMPageListView;
    public View view_top;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_shouye);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                ((ShouyeTop) view_top.getTag()).reload();
                break;
            case 1://消息
                Frame.HANDLES.sentAll("FrgGongju", 1, null);
                ApisFactory.getApiMCountNotify().load(getContext(), this, "MCountNotify");
                break;
            case 2://问题筛选
                Helper.startActivity(getContext(), FrgWendalistAll.class, TitleAct.class, "type", obj);
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
        mImageView_more = (ImageView) findViewById(R.id.mImageView_more);
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);

        clk_mTextView_sousuo.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mRelativeLayout_xiaoxi.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mImageView_more.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void loaddata() {
        view_top = ShouyeTop.getView(getContext(), null);
        mMPageListView.addHeaderView(view_top);
        mMPageListView.setDataFormat(new DfShouye());
        mMPageListView.setApiUpdate(ApisFactory.getApiSTopicList().set(3.0, "", F.latitude + "", F.longitude + ""));
        mMPageListView.pullLoad();

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
        if (R.id.clk_mTextView_sousuo == v.getId()) {
            Helper.startActivity(getContext(), FrgSousuo.class, NoTitleAct.class);
        } else if (R.id.clk_mRelativeLayout_xiaoxi == v.getId()) {
            Helper.startActivity(getContext(), FrgWodeXiaoxi.class, TitleAct.class);
        } else if (R.id.mImageView_more == v.getId()) {
            PopShowShouye mPopShowShouye = new PopShowShouye(getContext(), mImageView_more);
            mPopShowShouye.show();
        }
    }

}