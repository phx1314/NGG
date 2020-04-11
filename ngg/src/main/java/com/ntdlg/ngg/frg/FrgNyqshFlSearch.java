//
//  FrgNyqshFl
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.framewidget.view.FixGridLayout;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.item.NyfenleSon;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MCategory;
import com.udows.common.proto.MCategoryList;

import static com.ntdlg.ngg.F.dip2px;


public class FrgNyqshFlSearch extends BaseFrg {

    public FixGridLayout mFixGridLayout;
    public EditText mEditText;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_nyqsh_fl_search);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    private void findVMethod() {
        mFixGridLayout = (FixGridLayout) findViewById(R.id.mFixGridLayout);
        mEditText = (EditText) findViewById(R.id.mEditText);
        mFixGridLayout.setDividerCol(dip2px(getContext(), 10));
        mFixGridLayout.setDividerLine(dip2px(getContext(), 10));
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_GO) {
                    if (TextUtils.isEmpty(mEditText.getText().toString().trim())) {
                        Helper.toast("请输入", getContext());

                        return true;
                    }
                    ApisFactory.getApiMPesticideCate().load(getContext(), FrgNyqshFlSearch.this, "MPesticideCate", null, mEditText.getText().toString().trim());
                    return true;
                }
                return false;
            }
        });
    }

    public void loaddata() {


    }

    public void MPesticideCate(Son s) {
        mFixGridLayout.removeAllViews();
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
        mHeadlayout.setTitle("查询");
    }
}