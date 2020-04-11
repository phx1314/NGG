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

import com.mdx.framework.Frame;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfPublicList;
import com.udows.common.proto.ApisFactory;


public class FrgPublicList extends BaseFrg {

    public MPageListView mMPageListView;
    public String title;
    public String from;

    @Override
    protected void create(Bundle savedInstanceState) {
        title = getActivity().getIntent().getStringExtra("title");
        from = getActivity().getIntent().getStringExtra("from");
        setContentView(R.layout.frg_public_list);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                this.finish();
                Frame.HANDLES.sentAll(from, 120, obj);
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);

    }

    public void loaddata() {
        if (title.equals("选择职业")) {
//            mMPageListView.setDataFormat(new DfPublicList());
//            mMPageListView.setApiUpdate(ApisFactory.getapi);
//            mMPageListView.pullLoad();
        } else if (title.equals("专家类型")) {
            mMPageListView.setDataFormat(new DfPublicList());
            mMPageListView.setApiUpdate(ApisFactory.getApiMProCategory().set());
            mMPageListView.pullLoad();
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle(title);
    }
}