//
//  FrgXinwenzixun
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfSousuoJishu;
import com.ntdlg.ngg.dataformat.DfWodeXiaoxi;
import com.ntdlg.ngg.dataformat.DfXinwenzixun;
import com.ntdlg.ngg.item.XinwenzixunTop;
import com.udows.common.proto.ApisFactory;


public class FrgXinwenzixun extends BaseFrg implements RadioGroup.OnCheckedChangeListener {

    public RadioButton mTextView_yw;
    public RadioButton mTextView_zx;
    public RadioButton mTextView_nj;
    public MPageListView mMPageListView;
    public RadioGroup mRadioGroup;
    public double type = 1.0;
    public Handler mHandler = new Handler();
    public Runnable mrun;
    public View view_top;

    @Override
    protected void create(Bundle savedInstanceState) {
//        type = getActivity().getIntent().getDoubleExtra("type", 1.0);
        setContentView(R.layout.frg_xinwenzixun);
        initView();
        loaddata();

    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_yw = (RadioButton) findViewById(R.id.mTextView_yw);
        mTextView_zx = (RadioButton) findViewById(R.id.mTextView_zx);
        mTextView_nj = (RadioButton) findViewById(R.id.mTextView_nj);
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        view_top = XinwenzixunTop.getView(getContext(), null);

        mRadioGroup.setOnCheckedChangeListener(this);
    }

    public void loaddata() {
        mrun = new Runnable() {
            @Override
            public void run() {
                try {
                    if (type == 1) {
                        mMPageListView.addHeaderView(view_top);
                        mRadioGroup.setOnCheckedChangeListener(null);
                        mRadioGroup.check(R.id.mTextView_yw);
                        mRadioGroup.setOnCheckedChangeListener(FrgXinwenzixun.this);
                        mMPageListView.setDataFormat(new DfXinwenzixun(view_top));
                        mMPageListView.setApiUpdate(ApisFactory.getApiMNewsList().set(type));
                        mMPageListView.pullLoad();
                    } else if (type == 2) {
                        mMPageListView.removeHeaderView(view_top);
                        mMPageListView.addHeaderView(view_top);
                        mRadioGroup.setOnCheckedChangeListener(null);
                        mRadioGroup.check(R.id.mTextView_zx);
                        mRadioGroup.setOnCheckedChangeListener(FrgXinwenzixun.this);
                        mMPageListView.setApiUpdate(ApisFactory.getApiMIndexNewsList().set());
                        mMPageListView.setDataFormat(new DfWodeXiaoxi(view_top));
                        mMPageListView.reload();
                    } else if (type == 3) {
                        mMPageListView.removeHeaderView(view_top);
                        mRadioGroup.setOnCheckedChangeListener(null);
                        mRadioGroup.check(R.id.mTextView_nj);
                        mRadioGroup.setOnCheckedChangeListener(FrgXinwenzixun.this);
                        mMPageListView.setDataFormat(new DfSousuoJishu());
                        mMPageListView.setApiUpdate(ApisFactory.getApiMCropTechList().set(null, 0.0, ""));
                        mMPageListView.reload();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        mHandler.postDelayed(mrun, 400);
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("农业资讯");
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.mTextView_yw:
                type = 1.0;
                mHandler.removeCallbacks(mrun);
                mHandler.postDelayed(mrun, 400);
                break;
            case R.id.mTextView_zx:
                type = 2.0;
                mHandler.removeCallbacks(mrun);
                mHandler.postDelayed(mrun, 400);
                break;
            case R.id.mTextView_nj:
                type = 3.0;
                mHandler.removeCallbacks(mrun);
                mHandler.postDelayed(mrun, 400);
                break;
        }
    }
}