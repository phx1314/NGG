//
//  FrgZacaoListErji
//
//  Created by df on 2017-03-13 10:29:46
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;

import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfZacaoListErji;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MCategory;


public class FrgZacaoListErji extends BaseFrg {

    public MPageListView mMPageListView;
    public MCategory mMCategory;

    @Override
    protected void create(Bundle savedInstanceState) {
        mMCategory = (MCategory) getActivity().getIntent().getSerializableExtra("mMCategory");
        setContentView(R.layout.frg_zacao_list_erji);
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
//        mMPageListView.setAdapter(new AdaZacaoListErji(getContext(), mMCategory.son));
        mMPageListView.setApiUpdate(ApisFactory.getApiMZaCaoCate().set(mMCategory.code));
        mMPageListView.setDataFormat(new DfZacaoListErji());
        mMPageListView.pullLoad();
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle(mMCategory.name);
    }
}