//
//  FrgNyqshFl
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.framewidget.view.FixGridLayout;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.item.NyfenleSon;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MCategory;
import com.udows.common.proto.MCategoryList;

import static com.ntdlg.ngg.F.dip2px;


public class FrgNyqshFl extends BaseFrg {

    public FixGridLayout mFixGridLayout;
    public MCategory item;

    @Override
    protected void create(Bundle savedInstanceState) {
        item = (MCategory) getActivity().getIntent().getSerializableExtra("item");
        setContentView(R.layout.frg_nyqsh_fl);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mFixGridLayout = (FixGridLayout) findViewById(R.id.mFixGridLayout);
        mFixGridLayout.setDividerCol(dip2px(getContext(), 10));
        mFixGridLayout.setDividerLine(dip2px(getContext(), 10));

    }

    public void loaddata() {
        ApisFactory.getApiMPesticideCate().load(getContext(), this, "MPesticideCate", item.code,"");

    }

    public void MPesticideCate(Son s) {
        MCategoryList mMCategoryList = (MCategoryList) s.getBuild();
        for (MCategory mMCategory : mMCategoryList.mini) {
            View view = NyfenleSon.getView(getContext(), null);
            ((NyfenleSon) view.getTag()).set(mMCategory);
            mFixGridLayout.addView(view);
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle(item.name);
    }
}