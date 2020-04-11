//
//  FrgWentiDetail
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfWenti;
import com.ntdlg.ngg.item.WentiTop;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.STopic;

import static android.R.attr.id;


public class FrgWentiDetail extends BaseFrg {

    public MPageListView mMPageListView;
    public LinearLayout clk_mLinearLayout_jieda;
    public String mid;
    public static STopic mSTopicDetail;
    public View view_top;
    public TextView mTextView_jieda;

    @Override
    protected void create(Bundle savedInstanceState) {
        mid = getActivity().getIntent().getStringExtra("mid");
        setContentView(R.layout.frg_wenti_detail);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                mMPageListView.pullLoad();
                break;
            case 1:
//                ApisFactory.getApiSTopicDetail().load(getContext(), this, "STopicDetail", mid);
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);
        clk_mLinearLayout_jieda = (LinearLayout) findViewById(R.id.clk_mLinearLayout_jieda);
        mTextView_jieda = (TextView) findViewById(R.id.mTextView_jieda);
        view_top = WentiTop.getView(getContext(), null);
        clk_mLinearLayout_jieda.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void loaddata() {
        mMPageListView.addHeaderView(view_top);
        mMPageListView.setDataFormat(new DfWenti());
        mMPageListView.setApiUpdate(ApisFactory.getApiSTopicComments().set(mid, 1.0, null));
        ApisFactory.getApiSTopicDetail().load(getContext(), this, "STopicDetail", mid);
    }

    public void STopicDetail(Son s) {
        mSTopicDetail = (STopic) s.getBuild();
        ((WentiTop) view_top.getTag()).set(mSTopicDetail);
        if (mSTopicDetail.lz.id.equals(F.UserId)) {
            mTextView_jieda.setText("我来补充");
        }
        mHeadlayout.setTitle(mSTopicDetail.lz.nickName + "的问题");
        if (mSTopicDetail.isCollect == 1) {
            mHeadlayout.setRight2Bacgroud(R.drawable.bt_shoucang_w_h);
        } else {
            mHeadlayout.setRight2Bacgroud(R.drawable.bt_shoucang_w_n);
        }
        mMPageListView.pullLoad();
    }

    public void SCollectTopic(Son s) {
        mSTopicDetail.isCollect = 1;
        mHeadlayout.setRight2Bacgroud(R.drawable.bt_shoucang_w_h);
        Frame.HANDLES.sentAll("FrgWodeShoucang", 0, null);
    }

    public void SCollectTopicCancel(Son s) {
        mSTopicDetail.isCollect = 0;
        mHeadlayout.setRight2Bacgroud(R.drawable.bt_shoucang_w_n);
        Frame.HANDLES.sentAll("FrgWodeShoucang", 0, null);
    }


    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mLinearLayout_jieda == v.getId()) {
            Helper.startActivity(getContext(), FrgJieda.class, TitleAct.class, "mid", mid);
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
//        mHeadlayout.setTitle(name + "的问题");
        mHeadlayout.setRightBacgroud(R.drawable.bt_fenxiang_w_n);
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.framewidget.F.getShare(getContext(), "", BaseConfig.getUri() + "/m/share/baike/" + id
                        , "", "");
            }
        });
        mHeadlayout.setRight2Onclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSTopicDetail.isCollect == 1) {
                    ApisFactory.getApiSCollectTopic().load(getContext(), FrgWentiDetail.this, "SCollectTopicCancel", mSTopicDetail.id, 0.0);
                } else {
                    ApisFactory.getApiSCollectTopic().load(getContext(), FrgWentiDetail.this, "SCollectTopic", mSTopicDetail.id, 1.0);
                }
            }
        });
    }


}