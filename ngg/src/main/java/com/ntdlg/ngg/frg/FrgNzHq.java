//
//  FrgNzHq
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfGuanfangQingbaoyuan;
import com.ntdlg.ngg.item.NzHqTop;
import com.udows.common.proto.ApisFactory;


public class FrgNzHq extends BaseFrg {

    public MPageListView mMPageListView;
    public View view_top;
    public ImageView mImageView_back;
    public EditText mEditText;
    public ImageView mImageView_more;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_nz_hq);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);
        mImageView_back = (ImageView) findViewById(R.id.mImageView_back);
        mEditText = (EditText) findViewById(R.id.mEditText);
        mImageView_more = (ImageView) findViewById(R.id.mImageView_more);
        view_top = NzHqTop.getView(getContext(), null);
        mMPageListView.addHeaderView(view_top);
        mImageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrgNzHq.this.finish();
            }
        });
        mImageView_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (F.mSUser.isOffice == 1) {
                    Helper.startActivity(getContext(), FrgFabu.class, TitleAct.class);
                } else {
                    Helper.startActivity(getContext(), FrgShenqingguanfangxinxiyuan.class, TitleAct.class);
                }
            }
        });
    }

    public void loaddata() {
        mMPageListView.setDataFormat(new DfGuanfangQingbaoyuan());
        mMPageListView.setApiUpdate(ApisFactory.getApiMNongZiInfoList().set());
        mMPageListView.pullLoad();
    }


}