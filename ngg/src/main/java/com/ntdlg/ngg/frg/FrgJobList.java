//
//  FrgJobList
//
//  Created by DELL on 2017-05-12 16:42:15
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;

import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.ada.AdaJob;

import java.util.Arrays;


public class FrgJobList extends BaseFrg {

    public MPageListView mMPageListView;
    public String[] data = {"种植大户", "技术员", "业务员", "经销商", "其他"};

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_job_list);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                this.finish();
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);

        mMPageListView.setAdapter(new AdaJob(getContext(), Arrays.asList(data)));
    }

    public void loaddata() {

    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("职业选择");
    }
}