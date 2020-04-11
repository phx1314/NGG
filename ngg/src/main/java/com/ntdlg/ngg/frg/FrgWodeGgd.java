//
//  FrgWodeGgd
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfWodeGgd;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.SUser;

import static com.ntdlg.ngg.F.mSUser;


public class FrgWodeGgd extends BaseFrg {

    public TextView mTextView_num;
    public TextView mTextView_duihuan;
    public MPageListView mMPageListView;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_wode_ggd);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                ApisFactory.getApiSGetUserInfo().load(getContext(), this, "SGetUserInfo", F.UserId);
                mMPageListView.pullLoad();
                break;
        }
    }

    public void SGetUserInfo(Son s) {
        mSUser = (SUser) s.getBuild();
        mTextView_num.setText(mSUser.credit + "");
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_num = (TextView) findViewById(R.id.mTextView_num);
        mTextView_duihuan = (TextView) findViewById(R.id.mTextView_duihuan);
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);

        mTextView_duihuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgGgdShangcheng.class, TitleAct.class);
            }
        });
    }

    public void loaddata() {
        mMPageListView.setDataFormat(new DfWodeGgd());
        mMPageListView.setApiUpdate(ApisFactory.getApiMCreditLogList().set(0.0));
        mMPageListView.pullLoad();

        mTextView_num.setText(mSUser.credit + "");
    }


    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("我的乖乖豆");
    }
}