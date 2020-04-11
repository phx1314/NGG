//
//  FrgZacaoListSanji
//
//  Created by df on 2017-03-13 10:29:46
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfZacaoList;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MCategory;


public class FrgZacaoListSanji extends BaseFrg {

    public MPageListView mMPageListView;
    public MCategory item;
    public EditText mEditText;

    @Override
    protected void create(Bundle savedInstanceState) {
        item = (MCategory) getActivity().getIntent().getSerializableExtra("item");
        setContentView(R.layout.frg_zacao_list_sanji);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);
        mEditText = (EditText) findViewById(R.id.mEditText);

        mMPageListView.setDataFormat(new DfZacaoList());
        mMPageListView.setApiUpdate(ApisFactory.getApiMZaCaoInfoList().set(item.code).setPageSize(Integer.MAX_VALUE));
        mMPageListView.pullLoad();
    }

    public void loaddata() {

    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle(item.name);
    }
}