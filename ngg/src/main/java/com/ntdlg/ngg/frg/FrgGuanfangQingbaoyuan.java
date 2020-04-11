//
//  FrgGuanfangQingbaoyuan
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfGuanfangQingbaoyuan;
import com.ntdlg.ngg.item.GuanfangQingbaoyuanTop;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MOfficeUser;


public class FrgGuanfangQingbaoyuan extends BaseFrg {

    public MPageListView mMPageListView;
    public LinearLayout mLinearLayout_fabu;
    public View view_top;
    public String id;
    public MOfficeUser mMOfficeUser;

    @Override
    protected void create(Bundle savedInstanceState) {
        id = getActivity().getIntent().getStringExtra("id");
        setContentView(R.layout.frg_guanfang_qingbaoyuan);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                this.finish();
                break;
            case 1:
                mMPageListView.pullLoad();
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);
        mLinearLayout_fabu = (LinearLayout) findViewById(R.id.mLinearLayout_fabu);
        view_top = GuanfangQingbaoyuanTop.getView(getContext(), null);
        mLinearLayout_fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgFabu.class, TitleAct.class);
            }
        });
        mMPageListView.setDataFormat(new DfGuanfangQingbaoyuan());
        mMPageListView.setApiUpdate(ApisFactory.getApiMMyOfficeComment().set());
        mMPageListView.pullLoad();
    }

    public void loaddata() {
        ApisFactory.getApiMOfficeUserInfo().load(getContext(), this, "MOfficeUserInfo", id);
    }

    public void MOfficeUserInfo(Son s) {
        mMOfficeUser = (MOfficeUser) s.getBuild();
        ((GuanfangQingbaoyuanTop) view_top.getTag()).set(mMOfficeUser);
    }


}