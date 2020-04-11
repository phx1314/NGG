//
//  FrgChanxunErjiDetail
//
//  Created by Administrator on 2017-04-27 20:37:09
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.mdx.framework.server.api.Son;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MPesticideStandardSearch;


public class FrgChanxunErjiDetail extends BaseFrg {

    public TextView mTextView_changjia;
    public TextView mTextView_dj;
    public TextView mTextView_cf;
    public TextView mTextView_dx;
    public TextView mTextView_jx;
    public TextView mTextView_time;
    public TextView mTextView_1;
    public TextView mTextView_2;
    public TextView mTextView_3;
    public String id;

    @Override
    protected void create(Bundle savedInstanceState) {
        id = getActivity().getIntent().getStringExtra("id");
        setContentView(R.layout.frg_chanxun_erji_detail);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_changjia = (TextView) findViewById(R.id.mTextView_changjia);
        mTextView_dj = (TextView) findViewById(R.id.mTextView_dj);
        mTextView_cf = (TextView) findViewById(R.id.mTextView_cf);
        mTextView_dx = (TextView) findViewById(R.id.mTextView_dx);
        mTextView_jx = (TextView) findViewById(R.id.mTextView_jx);
        mTextView_time = (TextView) findViewById(R.id.mTextView_time);
        mTextView_1 = (TextView) findViewById(R.id.mTextView_1);
        mTextView_2 = (TextView) findViewById(R.id.mTextView_2);
        mTextView_3 = (TextView) findViewById(R.id.mTextView_3);


    }

    public void loaddata() {
        ApisFactory.getApiMPesticideStandardSearch().load(getContext(), this, "MPesticideStandardSearch", id);
    }

    public void MPesticideStandardSearch(Son s) {
        MPesticideStandardSearch mMPesticideStandardSearch = (MPesticideStandardSearch) s.getBuild();
        mTextView_changjia.setText(mMPesticideStandardSearch.name);
        mTextView_dj.setText(mMPesticideStandardSearch.standard);
        mTextView_cf.setText(mMPesticideStandardSearch.code);
        mTextView_dx.setText(mMPesticideStandardSearch.proType);
        mTextView_jx.setText(mMPesticideStandardSearch.goodsType);
        mTextView_time.setText(mMPesticideStandardSearch.firmName);
        mTextView_1.setText(mMPesticideStandardSearch.province);
        mTextView_2.setText(mMPesticideStandardSearch.beginTime);
        mTextView_3.setText(mMPesticideStandardSearch.endTime);
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("详情");
    }
}