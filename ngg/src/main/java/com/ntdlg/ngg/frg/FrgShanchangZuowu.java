//
//  FrgShanchangZuowu
//
//  Created by df on 2017-02-09 15:30:12
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.mdx.framework.Frame;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;

import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.dataformat.DfShanchangZuowu;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MCategory;

import java.util.ArrayList;
import java.util.List;


public class FrgShanchangZuowu extends BaseFrg {

    public MPageListView mMPageListView;
    public List<MCategory> data = new ArrayList<>();
    public String from;

    @Override
    protected void create(Bundle savedInstanceState) {
        from = getActivity().getIntent().getStringExtra("from");
        setContentView(R.layout.frg_shanchang_zuowu);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                if (!data.contains(obj)) {
                    data.add((MCategory) obj);
                }
                break;
            case 1:
                if (data.contains(obj)) {
                    data.remove(obj);
                }
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);
        mMPageListView.setApiUpdate(ApisFactory.getApiMCropCate().set().setPageSize(Integer.MAX_VALUE));
        mMPageListView.setDataFormat(new DfShanchangZuowu());
        mMPageListView.pullLoad();
    }

    public void loaddata() {

    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("擅长作物");
        mHeadlayout.setRText("保存");
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrgShanchangZuowu.this.finish();
//                String aa = "";
//                for (MCategory str : data) {
//                    aa += str + ",";
//                }
                if (data.size() <= 0) {
                    Helper.toast("请选择作物", getContext());
                }
//                else {
//                    aa = aa.substring(0, aa.length() - 1);
//                }
                Frame.HANDLES.sentAll(from, 1, data);
            }
        });
    }
}