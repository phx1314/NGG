//
//  FrgDizhiGuanli
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfDizhiGuanli;
import com.udows.common.proto.ApisFactory;


public class FrgDizhiGuanli extends BaseFrg {

    public MPageListView mMPageListView;
    public LinearLayout clk_mLinearLayout_add;
    public String from;
    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                mMPageListView.reload();
                Frame.HANDLES.sentAll(from, 0, null);
                break;
            case 1:
                FrgDizhiGuanli.this.finish();
                Frame.HANDLES.sentAll(from, 1, obj);
                break;
        }
    }

    @Override
    protected void create(Bundle savedInstanceState) {
        from=getActivity().getIntent().getStringExtra("from");
        setContentView(R.layout.frg_dizhi_guanli);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);
        clk_mLinearLayout_add = (LinearLayout) findViewById(R.id.clk_mLinearLayout_add);

        clk_mLinearLayout_add.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void loaddata() {
        mMPageListView.setDataFormat(new DfDizhiGuanli());
        mMPageListView.setApiUpdate(ApisFactory.getApiMMyAddressList().set().setPageSize(Integer.MAX_VALUE));
        mMPageListView.pullLoad();
    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mLinearLayout_add == v.getId()) {
            Helper.startActivity(getContext(), FrgAddAddress.class,
                    TitleAct.class);
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("地址管理");
    }
}