//
//  FrgPublicList
//
//  Created by df on 2017-02-07 14:42:18
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfZacaoList;
import com.udows.common.proto.ApisFactory;


public class FrgPublicSearChThree extends BaseFrg {

    public MPageListView mMPageListView;
    public EditText mEditText;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_publicsearch_three_list);
        initView();
        loaddata();
    }


    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);
        mEditText = (EditText) findViewById(R.id.mEditText);
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_GO) {
                    if (TextUtils.isEmpty(mEditText.getText().toString().trim())) {
                        Helper.toast("请输入", getContext());

                        return true;
                    }
                    mMPageListView.setDataFormat(new DfZacaoList());
                    mMPageListView.setApiUpdate(ApisFactory.getApiMZaCaoInfoList().set(null, mEditText.getText().toString().trim()));
                    mMPageListView.pullLoad();
                    return true;
                }
                return false;
            }
        });
    }

    public void loaddata() {

    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("查询");
    }
}