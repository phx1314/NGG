//
//  FrgWenZhuanjia
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfSousuoZhuanjia;
import com.ntdlg.ngg.view.PopShowZhiNengPx;
import com.ntdlg.ngg.view.PopShowZhuanjiaType;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MCategory;
import com.udows.common.proto.MCategoryList;
import com.udows.common.proto.MRet;


public class FrgWenZhuanjia extends BaseFrg {

    public EditText mEditText_sousuo;
    public RelativeLayout clk_mRelativeLayout_xiaoxi;
    public ImageView mImageView_xiaoxi;
    public ImageView mImageView_xiaoxi_dot;
    public LinearLayout clk_mLinearLayout_leixing;
    public TextView mTextView_lexing;
    public LinearLayout clk_mLinearLayout_paixu;
    public TextView mTextView_paixu;
    public MPageListView mMPageListView;
    public String cateCode;
    public double type_leixing = 1;
    public MCategoryList mMCategoryList;
    public static String zhuangjia_name="全部";
    public static String paixu="全部";
    public ImageView mImageView_back;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_wen_zhuanjia);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 1:
                MCategory mMCategory1 = (MCategory) obj;
                cateCode = mMCategory1.code;
                FrgWenZhuanjia.zhuangjia_name = mMCategory1.name;
                mMPageListView.setDataFormat(new DfSousuoZhuanjia());
                mMPageListView.setApiUpdate(ApisFactory.getApiMProList().set(cateCode, type_leixing, mEditText_sousuo.getText().toString().trim()));
                mMPageListView.pullLoad();
                break;
            case 2:
                MCategory mMCategory = (MCategory) obj;
                type_leixing = Double.valueOf(mMCategory.code);
                FrgWenZhuanjia.paixu = mMCategory.name;
                mMPageListView.setDataFormat(new DfSousuoZhuanjia());
                mMPageListView.setApiUpdate(ApisFactory.getApiMProList().set(cateCode, type_leixing, mEditText_sousuo.getText().toString().trim()));
                mMPageListView.pullLoad();
                break;
            case 3:
                mTextView_lexing.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bt_zjlx_n, 0, R.drawable.bt_sanjiao_n, 0);
                mTextView_lexing.setTextColor(getResources().getColor(R.color.E4));
                break;
            case 4:
                mTextView_paixu.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bt_znpx_n, 0, R.drawable.bt_sanjiao_n, 0);
                mTextView_paixu.setTextColor(getResources().getColor(R.color.E4));
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mEditText_sousuo = (EditText) findViewById(R.id.mEditText_sousuo);
        clk_mRelativeLayout_xiaoxi = (RelativeLayout) findViewById(R.id.clk_mRelativeLayout_xiaoxi);
        mImageView_xiaoxi = (ImageView) findViewById(R.id.mImageView_xiaoxi);
        mImageView_xiaoxi_dot = (ImageView) findViewById(R.id.mImageView_xiaoxi_dot);
        clk_mLinearLayout_leixing = (LinearLayout) findViewById(R.id.clk_mLinearLayout_leixing);
        mTextView_lexing = (TextView) findViewById(R.id.mTextView_lexing);
        clk_mLinearLayout_paixu = (LinearLayout) findViewById(R.id.clk_mLinearLayout_paixu);
        mTextView_paixu = (TextView) findViewById(R.id.mTextView_paixu);
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);
        mImageView_back = (ImageView) findViewById(R.id.mImageView_back);

        clk_mRelativeLayout_xiaoxi.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout_leixing.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout_paixu.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mImageView_back.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mEditText_sousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_GO) {
                    mMPageListView.setDataFormat(new DfSousuoZhuanjia());
                    mMPageListView.setApiUpdate(ApisFactory.getApiMProList().set(cateCode, type_leixing, mEditText_sousuo.getText().toString().trim()));
                    mMPageListView.pullLoad();
                    F.closeSoftKey(getActivity());
                    return true;
                }
                return false;
            }
        });
    }

    public void loaddata() {
        ApisFactory.getApiMCountNotify().load(getContext(), this, "MCountNotify");
        ApisFactory.getApiMProCategory().load(getContext(), this, "MProCategory");
        mMPageListView.setDataFormat(new DfSousuoZhuanjia());
        mMPageListView.setApiUpdate(ApisFactory.getApiMProList().set());
        mMPageListView.pullLoad();
    }

    public void MProCategory(Son s) {
        mMCategoryList = (MCategoryList) s.getBuild();
        MCategory mMCategory = new MCategory();
        mMCategory.name = "全部";
        mMCategoryList.mini.add(0, mMCategory);
    }

    public void MCountNotify(Son s) {
        MRet mMRet = (MRet) s.getBuild();
        if (mMRet.code > 0) {
            mImageView_xiaoxi_dot.setVisibility(View.VISIBLE);
        } else {
            mImageView_xiaoxi_dot.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mRelativeLayout_xiaoxi == v.getId()) {
            Helper.startActivity(getContext(), FrgWodeXiaoxi.class, TitleAct.class);
        } else if (R.id.clk_mLinearLayout_leixing == v.getId()) {
            PopShowZhuanjiaType mPopShowZhuanjiaType = new PopShowZhuanjiaType(getContext(), clk_mLinearLayout_leixing, mMCategoryList);
            mPopShowZhuanjiaType.show();
            mTextView_lexing.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bt_zjlx_h, 0, R.drawable.bt_sanjiao_h, 0);
            mTextView_lexing.setTextColor(getResources().getColor(R.color.A));
        } else if (R.id.clk_mLinearLayout_paixu == v.getId()) {
            PopShowZhiNengPx mPopShowZhiNengPx = new PopShowZhiNengPx(getContext(), clk_mLinearLayout_paixu);
            mPopShowZhiNengPx.show();
            mTextView_paixu.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bt_znpx_h, 0, R.drawable.bt_sanjiao_h, 0);
            mTextView_paixu.setTextColor(getResources().getColor(R.color.A));
        } else if (R.id.mImageView_back == v.getId()) {
            this.finish();
        }
    }


}