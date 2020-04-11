//
//  FrgGongju
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

import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfGongju;
import com.ntdlg.ngg.item.GongjuTop;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MRet;


public class FrgGongju extends BaseFrg {

    public TextView clk_mTextView_sousuo;
    public RelativeLayout clk_mRelativeLayout_xiaoxi;
    public ImageView mImageView_xiaoxi;
    public ImageView mImageView_xiaoxi_dot;
    public MPageListView mMPageListView;
    public View view_top;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_gongju);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 1://消息
                ApisFactory.getApiMCountNotify().load(getContext(), this, "MCountNotify");
                break;
            case 2:
                ((GongjuTop)view_top.getTag()).set();
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
        view_top = GongjuTop.getView(getContext(), null);
        clk_mTextView_sousuo.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mRelativeLayout_xiaoxi.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void loaddata() {
        mMPageListView.addHeaderView(view_top);
        mMPageListView.setDataFormat(new DfGongju());
        mMPageListView.setApiUpdate(ApisFactory.getApiMGoodsList().set());
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
        }
    }

}