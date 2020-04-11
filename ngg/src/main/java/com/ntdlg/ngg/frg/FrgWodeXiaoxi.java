//
//  FrgWodeXiaoxi
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.mdx.framework.server.api.Son;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfWodeXiaoxi;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MRet;


public class FrgWodeXiaoxi extends BaseFrg {

    public TextView mTextView_wuliu;
    public TextView mTextView_xiaoxi;
    public MPageListView mMPageListView;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_wode_xiaoxi);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 1://消息
                ApisFactory.getApiMCountNotify().load(getContext(), this, "MCountNotify1", 1.0);
                ApisFactory.getApiMCountNotify().load(getContext(), this, "MCountNotify2", 2.0);
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_wuliu = (TextView) findViewById(R.id.mTextView_wuliu);
        mTextView_xiaoxi = (TextView) findViewById(R.id.mTextView_xiaoxi);
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);

        mTextView_wuliu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView_wuliu.setTextColor(getContext().getResources().getColor(R.color.A));
                mTextView_xiaoxi.setTextColor(getContext().getResources().getColor(R.color.E4));
                mMPageListView.setApiUpdate(ApisFactory.getApiMNotifyList().set(2.0, 2.0));
                mMPageListView.setDataFormat(new DfWodeXiaoxi());
                mMPageListView.pullLoad();
            }
        });
        mTextView_xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView_wuliu.setTextColor(getContext().getResources().getColor(R.color.E4));
                mTextView_xiaoxi.setTextColor(getContext().getResources().getColor(R.color.A));
                mMPageListView.setApiUpdate(ApisFactory.getApiMNotifyList().set(1.0, 2.0));
                mMPageListView.setDataFormat(new DfWodeXiaoxi());
                mMPageListView.pullLoad();
            }
        });
    }

    public void loaddata() {
        ApisFactory.getApiMCountNotify().load(getContext(), this, "MCountNotify1", 1.0);
        ApisFactory.getApiMCountNotify().load(getContext(), this, "MCountNotify2", 2.0);
        mMPageListView.setApiUpdate(ApisFactory.getApiMNotifyList().set(1.0, 2.0));
        mMPageListView.setDataFormat(new DfWodeXiaoxi());
        mMPageListView.pullLoad();
    }

    public void MCountNotify1(Son s) {
        MRet mMRet = (MRet) s.getBuild();
        if (!TextUtils.isEmpty(mMRet.msg) && !mMRet.msg.equals("0")) {
            mTextView_xiaoxi.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.rm_red, 0);
        } else {
            mTextView_xiaoxi.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
    }

    public void MCountNotify2(Son s) {
        MRet mMRet = (MRet) s.getBuild();
        if (!TextUtils.isEmpty(mMRet.msg) && !mMRet.msg.equals("0")) {
            mTextView_wuliu.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.rm_red, 0);
        } else {
            mTextView_wuliu.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("我的消息");
    }
}