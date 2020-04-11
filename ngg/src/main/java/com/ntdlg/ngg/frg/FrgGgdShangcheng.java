//
//  FrgGgdShangcheng
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfGgdShangcheng;
import com.udows.common.proto.ApisFactory;


public class FrgGgdShangcheng extends BaseFrg {

    public TextView mTextView_num;
    public TextView mTextView_jil;
    public MPageListView mMPageListView;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_ggd_shangcheng);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type){
            case 0:
                mMPageListView.pullLoad();
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_num = (TextView) findViewById(R.id.mTextView_num);
        mTextView_jil = (TextView) findViewById(R.id.mTextView_jil);
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);

        mMPageListView.setDataFormat(new DfGgdShangcheng());
        mMPageListView.setApiUpdate(ApisFactory.getApiMCreditGoodsList().set());
        mMPageListView.pullLoad();
        mTextView_jil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(),FrgShangchengDuihuanjilu.class, TitleAct.class);
            }
        });
    }

    public void loaddata() {
        mTextView_num.setText(F.mSUser.credit + "");
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("乖乖豆商城");
    }
}