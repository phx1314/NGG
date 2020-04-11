//
//  FrgSet
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.cache.DataStoreCacheManage;
import com.mdx.framework.cache.FileStoreCacheManage;
import com.mdx.framework.cache.ImageStoreCacheManage;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.utility.UnitConver;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.udows.common.proto.ApisFactory;


public class FrgSet extends BaseFrg {

    public TextView mTextView_guanyu;
    public TextView mTextView_yjfk;
    public TextView mTextView_huancun;
    public TextView clk_mTextView_tuichu;
    public LinearLayout mLinearLayout_clear;
    private Runnable mRunnable;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_set);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_guanyu = (TextView) findViewById(R.id.mTextView_guanyu);
        mTextView_yjfk = (TextView) findViewById(R.id.mTextView_yjfk);
        mTextView_huancun = (TextView) findViewById(R.id.mTextView_huancun);
        clk_mTextView_tuichu = (TextView) findViewById(R.id.clk_mTextView_tuichu);
        mLinearLayout_clear = (LinearLayout) findViewById(R.id.mLinearLayout_clear);

        clk_mTextView_tuichu.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_guanyu.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_yjfk.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
    }

    public void loaddata() {
        mTextView_huancun.setText(UnitConver.getBytesSize(
                DataStoreCacheManage.FILEMANAGER.TEMPFILES.tempSize
                        + FileStoreCacheManage.FILEMANAGER.TEMPFILES.tempSize
                        + ImageStoreCacheManage.FILEMANAGER.TEMPFILES.tempSize)
                .toString());

        mRunnable = new Runnable() {
            @Override
            public void run() {
                mTextView_huancun.setText(UnitConver
                        .getBytesSize(DataStoreCacheManage.FILEMANAGER.TEMPFILES.tempSize
                                + FileStoreCacheManage.FILEMANAGER.TEMPFILES.tempSize
                                + ImageStoreCacheManage.FILEMANAGER.TEMPFILES.tempSize + Frame.IMAGECACHE.getsize())
                        .toString());
            }
        };
        mLinearLayout_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setMessage("是否清除缓存")
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        DataStoreCacheManage.FILEMANAGER
                                                .clear(mRunnable);
                                        FileStoreCacheManage.FILEMANAGER
                                                .clear(mRunnable);
                                        ImageStoreCacheManage.FILEMANAGER
                                                .clear(mRunnable);
                                        Frame.IMAGECACHE.clean();
                                        Frame.IMAGECACHE.cleanMemoryCache();
                                        Frame.IMAGECACHE.cleanCache();
                                        dialog.dismiss();
                                    }
                                }).setNegativeButton("取消", null).create().show();
            }
        });
    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mTextView_tuichu == v.getId()) {
            ApisFactory.getApiMLogout().load(getContext(), this, "MLogout");
        } else if (R.id.mTextView_guanyu == v.getId()) {
            Helper.startActivity(getContext(), FrgGuanyu.class, TitleAct.class);
        } else if (R.id.mTextView_yjfk == v.getId()) {
            Helper.startActivity(getContext(), FrgYijianFk.class, TitleAct.class);
        }
    }

    public void MLogout(Son s) {
        this.finish();
        F.Login("", "");
        Frame.HANDLES.sentAll("FrgWode", 0, null);
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("设置");
    }
}