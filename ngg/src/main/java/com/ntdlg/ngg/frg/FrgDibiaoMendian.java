//
//  FrgDibiaoMendian
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.server.api.Son;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MFirm;


public class FrgDibiaoMendian extends BaseFrg {

    public TextView mTextView_remark;
    public LinearLayout mLinearLayout_fabu;
    public String id;

    @Override
    protected void create(Bundle savedInstanceState) {
        id = getActivity().getIntent().getStringExtra("id");
        setContentView(R.layout.frg_dibiao_mendian);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_remark = (TextView) findViewById(R.id.mTextView_remark);
        mLinearLayout_fabu = (LinearLayout) findViewById(R.id.mLinearLayout_fabu);


    }

    public void loaddata() {
        ApisFactory.getApiMJoinStoreDetail().load(getContext(), this, "MJoinStoreDetail", id);
    }

    public void MJoinStoreDetail(Son s) {
        MFirm mMFirm = (MFirm) s.getBuild();
        mTextView_remark.setText(mMFirm.info);
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("地标门店");
    }
}