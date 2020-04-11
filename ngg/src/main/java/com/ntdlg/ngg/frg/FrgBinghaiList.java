//
//  FrgBinghaiList
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;

import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;

import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.dataformat.DfSousuoJishu;
import com.udows.common.proto.ApisFactory;


public class FrgBinghaiList extends BaseFrg {

    public MPageListView mMPageListView;
    public String cateCode;
    public String item;
    public int type;

    @Override
    protected void create(Bundle savedInstanceState) {
        item = getActivity().getIntent().getStringExtra("item");
        cateCode = getActivity().getIntent().getStringExtra("cateCode");
        type = getActivity().getIntent().getIntExtra("type", 1);
        setContentView(R.layout.frg_binghai_list);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);


    }

    public void loaddata() {
        mMPageListView.setDataFormat(new DfSousuoJishu());
        mMPageListView.setApiUpdate(ApisFactory.getApiMCropTechList().set(cateCode, (double) type, item));
        mMPageListView.pullLoad();
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        if (type == 1) {
            mHeadlayout.setTitle("病害列表");
        } else if (type == 2) {
            mHeadlayout.setTitle("虫害列表");
        } else if (type == 3) {
            mHeadlayout.setTitle("草害列表");
        }
    }
}