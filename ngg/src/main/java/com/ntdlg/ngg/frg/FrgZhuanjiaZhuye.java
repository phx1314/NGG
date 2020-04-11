//
//  FrgZhuanjiaZhuye
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfShouye;
import com.ntdlg.ngg.item.ZhuanjiazhuyeTop;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.SUser;


public class FrgZhuanjiaZhuye extends BaseFrg {

    public MPageListView mMPageListView;
    public TextView mTextView_tiwen;
    public TextView mTextView_add_guanhzu;

    public String id;
    public SUser mSUser;
    public View view_top;

    @Override
    protected void create(Bundle savedInstanceState) {
        id = getActivity().getIntent().getStringExtra("id");
        setContentView(R.layout.frg_zhuanjia_zhuye);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                ApisFactory.getApiMProDetail().load(getContext(), this, "MProDetail", id);
                break;
            case 1:
                this.finish();
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);
        mTextView_tiwen = (TextView) findViewById(R.id.mTextView_tiwen);
        mTextView_add_guanhzu = (TextView) findViewById(R.id.mTextView_add_guanhzu);
        view_top = ZhuanjiazhuyeTop.getView(getContext(), null);
        mMPageListView.addHeaderView(view_top);

        mTextView_tiwen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgTiwenti.class, TitleAct.class, "fid", id);
            }
        });

    }

    public void loaddata() {
        mMPageListView.setDataFormat(new DfShouye("FrgZhuanjiaZhuye"));
        mMPageListView.setApiUpdate(ApisFactory.getApiSUserReplyList().set(id));
        mMPageListView.pullLoad();

    }

    public void MProDetail(Son s) {
        mSUser = (SUser) s.getBuild();
        ((ZhuanjiazhuyeTop) view_top.getTag()).set(mSUser);
        if (mSUser.isFocus == 1) {
            mTextView_add_guanhzu.setText("取消关注");
            mTextView_add_guanhzu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ApisFactory.getApiSEditFriend().load(getContext(), FrgZhuanjiaZhuye.this, "SEditFriend", 2.0, mSUser.id);
                }
            });
        } else {
            mTextView_add_guanhzu.setText("关注");
            mTextView_add_guanhzu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ApisFactory.getApiSEditFriend().load(getContext(), FrgZhuanjiaZhuye.this, "SEditFriend", 1.0, mSUser.id);
                }
            });
        }
    }

    public void SEditFriend(Son s) {
        ApisFactory.getApiSGetUserInfo().load(getContext(), this, "SGetUserInfo", id);
        mMPageListView.pullLoad();
    }


}