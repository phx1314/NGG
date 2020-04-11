//
//  FrgFabu
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.framewidget.newMenu.DateDfSelectDialog;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;
import com.udows.common.proto.ApisFactory;


public class FrgFabu extends BaseFrg {

    public EditText mEditText_1;
    public EditText mEditText_2;
    public EditText mEditText_3;
    public EditText mEditText_4;
    public TextView mEditText_riqi;
    public TextView mEditText_shuxing;
    public String cateCode;
    public DateDfSelectDialog mDateSelectDialog;
    public String name;

    @Override
    protected void create(Bundle savedInstanceState) {
        name = getActivity().getIntent().getStringExtra("name");
        setContentView(R.layout.frg_fabu);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mEditText_1 = (EditText) findViewById(R.id.mEditText_1);
        mEditText_2 = (EditText) findViewById(R.id.mEditText_2);
        mEditText_3 = (EditText) findViewById(R.id.mEditText_3);
        mEditText_4 = (EditText) findViewById(R.id.mEditText_4);
        mEditText_riqi = (TextView) findViewById(R.id.mEditText_riqi);
        mEditText_shuxing = (TextView) findViewById(R.id.mEditText_shuxing);
        mDateSelectDialog = new DateDfSelectDialog(getContext(), null, 3);
        mEditText_riqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDateSelectDialog.clear();
                mDateSelectDialog.show();
            }
        });
        mEditText_shuxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgNyHqfenlei.class, TitleAct.class);
            }
        });
    }

    public void loaddata() {
        if (!TextUtils.isEmpty(name)) {
            mEditText_1.setText(name);
        }
        mDateSelectDialog.setOnSelected(new DateDfSelectDialog.OnSelected() {
            @Override
            public void onSelected(Dialog dia, String selected) {
                mEditText_riqi.setText(selected);
            }
        });
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("发布");
        mHeadlayout.setRText("完成");
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEditText_1.getText().toString().trim())) {
                    Helper.toast("请输入名称", getContext());
                    return;
                }
                if (TextUtils.isEmpty(mEditText_3.getText().toString().trim())) {
                    Helper.toast("请输入规格", getContext());
                    return;
                }
                if (TextUtils.isEmpty(mEditText_2.getText().toString().trim())) {
                    Helper.toast("请输入价格", getContext());
                    return;
                }
                if (TextUtils.isEmpty(mEditText_riqi.getText().toString().trim())) {
                    Helper.toast("请选择日期", getContext());
                    return;
                }
                if (TextUtils.isEmpty(mEditText_4.getText().toString().trim())) {
                    Helper.toast("请输入评价", getContext());
                    return;
                }
                if (TextUtils.isEmpty(mEditText_shuxing.getText().toString().trim())) {
                    Helper.toast("请选择属性", getContext());
                    return;
                }
                ApisFactory.getApiMOfficeUserAdd().load(getContext(), this, "MOfficeUserAdd", cateCode, mEditText_1.getText().toString().trim(), mEditText_2.getText().toString().trim(), mEditText_3.getText().toString().trim(), mEditText_4.getText().toString().trim());
            }
        });
    }

    public void MOfficeUserAdd(Son s) {
        Helper.toast("上报成功", getContext());
        Frame.HANDLES.sentAll("FrgGuanfangQingbaoyuan", 1, null);
        this.finish();
    }

}