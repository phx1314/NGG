//
//  FrgNyqDetail
//
//  Created by df on 2017-02-10 13:58:58
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framewidget.F;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfNyqDetail;
import com.ntdlg.ngg.item.NyqDetailTop;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.SReply;
import com.udows.common.proto.STopic;


public class FrgNyqDetail extends BaseFrg {

    public MPageListView mMPageListView;
    public LinearLayout mLinearLayout_fs;
    public TextView mTextView_fs;
    public STopic item;
    public View view_top;
    public EditText mEditText;
    public String targetId;

    @Override
    protected void create(Bundle savedInstanceState) {
        item = (STopic) getActivity().getIntent().getSerializableExtra("item");
        setContentView(R.layout.frg_nyq_detail);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                mMPageListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mMPageListView.setPageAble(true);
                    }
                }, 200);
                break;
            case 1:
                SReply mSReply = (SReply) obj;
                targetId = mSReply.userId;
                mEditText.setText("");
                mEditText.setHint("回复" + mSReply.nickName);
                break;
            case 2:
                SReply mSReply1 = (SReply) obj;
                targetId = mSReply1.targetId;
                mEditText.setText("");
                mEditText.setHint("回复" + mSReply1.targetName);
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);
        mLinearLayout_fs = (LinearLayout) findViewById(R.id.mLinearLayout_fs);
        mTextView_fs = (TextView) findViewById(R.id.mTextView_fs);
        mEditText = (EditText) findViewById(R.id.mEditText);
        view_top = NyqDetailTop.getView(getContext(), null);
        ((NyqDetailTop) view_top.getTag()).set(item);
        mMPageListView.addHeaderView(view_top);

        mTextView_fs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEditText.getText().toString().trim())) {
                    Helper.toast("请输入", getContext());
                    return;
                }
                ApisFactory.getApiSAddCommentReply().load(getContext(), FrgNyqDetail.this, "SAddCommentReply", item.id, mEditText.getText().toString().trim(), targetId);
            }
        });
    }

    public void SAddCommentReply(Son s) {
        mEditText.setText("");
        F.closeSoftKey(getActivity());
        mMPageListView.setPageAble(true);
    }

    public void loaddata() {
        targetId = item.lz.id;
        mMPageListView.setDataFormat(new DfNyqDetail());
        mMPageListView.setApiUpdate(ApisFactory.getApiSCommentReplys().set(item.id));
        mMPageListView.pullLoad();
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("动态详情");
    }
}