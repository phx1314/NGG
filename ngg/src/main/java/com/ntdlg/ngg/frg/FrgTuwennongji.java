//
//  FrgTuwennongji
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfSousuoJishu;
import com.ntdlg.ngg.item.TwNjTop;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MCategory;
import com.udows.common.proto.MCategoryList;

import java.util.ArrayList;
import java.util.List;


public class FrgTuwennongji extends BaseFrg {

    public LinearLayout mLinearLayout_content;
    public LinearLayout mLinearLayout_add;
    public EditText mEditText_sousuo;
    public MPageListView mMPageListView;
    public View view_top;
    public String cateCode;
    public List<MCategory> mMCategorys = new ArrayList<>();

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_tuwennongji);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                Helper.startActivity(getContext(), FrgBinghaiList.class, TitleAct.class, "item", mEditText_sousuo.getText().toString().trim(), "type", obj, "cateCode", cateCode);
                break;
            case 3:
                mMPageListView.pullLoad();
                break;
            case 2:
                for (MCategory mMCategory : mMCategorys) {
                    if (cateCode.equals(mMCategory.code)) {
                        Helper.startActivity(getContext(), FrgTWNGWendalist.class, TitleAct.class, "key", mMCategory.name + mEditText_sousuo.getText().toString().trim());
                        return;
                    }
                }
                break;
            case 1:
                final List<MCategory> mMCategorys = (List<MCategory>) obj;
                mLinearLayout_content.removeAllViews();
                for (int i = 0; i < mMCategorys.size(); i++) {
                    final TextView mTextView = new TextView(getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins((int) getResources().getDimension(R.dimen.j10dp), 0, 0, 0);
                    mTextView.setLayoutParams(layoutParams);
                    mTextView.setText(mMCategorys.get(i).name);
                    mTextView.setTypeface(Typeface.DEFAULT, R.style.text_style_15_black);
                    if (i == 0) {
                        mTextView.setTextColor(getResources().getColor(R.color.A));
                        cateCode = mMCategorys.get(i).code;
                    } else {
                        mTextView.setTextColor(getResources().getColor(R.color.black));
                    }
                    mLinearLayout_content.addView(mTextView);
                    final int position = i;
                    mTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            for (int j = 0; j < mLinearLayout_content.getChildCount(); j++) {
                                ((TextView) mLinearLayout_content.getChildAt(j)).setTextColor(getResources().getColor(R.color.black));
                            }
                            mTextView.setTextColor(getResources().getColor(R.color.A));
                            cateCode = mMCategorys.get(position).code;
                            mMPageListView.setApiUpdate(ApisFactory.getApiMCropTechList().set(cateCode, 0.0, ""));
                            mMPageListView.pullLoad();
                        }
                    });
                }
                Helper.saveBuilder(F.UserId + "mMCategorys", mMCategorys);
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mLinearLayout_content = (LinearLayout) findViewById(R.id.mLinearLayout_content);
        mLinearLayout_add = (LinearLayout) findViewById(R.id.mLinearLayout_add);
        mEditText_sousuo = (EditText) findViewById(R.id.mEditText_sousuo);
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);
        mEditText_sousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_GO) {
                    mMPageListView.setApiUpdate(ApisFactory.getApiMCropTechList().set(cateCode, 0.0, mEditText_sousuo.getText().toString().trim()));
                    mMPageListView.pullLoad();
                    return true;
                }
                return false;
            }
        });
        mLinearLayout_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgAddZuowu.class, TitleAct.class);
            }
        });
        view_top = TwNjTop.getView(getContext(), null);
        mMPageListView.addHeaderView(view_top);
    }

    public void loaddata() {
        mMCategorys = Helper.readBuilder(F.UserId + "mMCategorys", mMCategorys);
        if (mMCategorys.size() <= 0) {
            ApisFactory.getApiMCropCate().load(getContext(), this, "MCategoryList");
        } else {
            for (int i = 0; i < mMCategorys.size(); i++) {
                final TextView mTextView = new TextView(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins((int) getResources().getDimension(R.dimen.j10dp), 0, 0, 0);
                mTextView.setLayoutParams(layoutParams);
                mTextView.setText(mMCategorys.get(i).name);
                mTextView.setTypeface(Typeface.DEFAULT, R.style.text_style_15_black);
                if (i == 0) {
                    mTextView.setTextColor(getResources().getColor(R.color.A));
                    cateCode = mMCategorys.get(i).code;
                } else {
                    mTextView.setTextColor(getResources().getColor(R.color.black));
                }
                mLinearLayout_content.addView(mTextView);
                final int position = i;
                mTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (int j = 0; j < mLinearLayout_content.getChildCount(); j++) {
                            ((TextView) mLinearLayout_content.getChildAt(j)).setTextColor(getResources().getColor(R.color.black));
                        }
                        mTextView.setTextColor(getResources().getColor(R.color.A));
                        cateCode = mMCategorys.get(position).code;
                        mMPageListView.setApiUpdate(ApisFactory.getApiMCropTechList().set(cateCode, 0.0, ""));
                        mMPageListView.pullLoad();
                    }
                });
            }
            mMPageListView.setDataFormat(new DfSousuoJishu());
            mMPageListView.setApiUpdate(ApisFactory.getApiMCropTechList().set(cateCode, 0.0, ""));
            mMPageListView.pullLoad();
        }
    }

    public void MCategoryList(Son s) {
        MCategoryList mMCategoryList = (MCategoryList) s.getBuild();
        for (final MCategory mMCategory : mMCategoryList.mini) {
            if (mMCategory.son.size() > 0) {
                final TextView mTextView = new TextView(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins((int) getResources().getDimension(R.dimen.j10dp), 0, 0, 0);
                mTextView.setLayoutParams(layoutParams);
                mTextView.setText(mMCategory.son.get(0).name);
                mTextView.setTypeface(Typeface.DEFAULT, R.style.text_style_15_black);
                if (mMCategorys.size() <= 0) {
                    mTextView.setTextColor(getResources().getColor(R.color.A));
                    cateCode = mMCategory.son.get(0).code;
                } else {
                    mTextView.setTextColor(getResources().getColor(R.color.black));
                }
                mMCategorys.add(mMCategory.son.get(0));
                mLinearLayout_content.addView(mTextView);
                mTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (int i = 0; i < mLinearLayout_content.getChildCount(); i++) {
                            ((TextView) mLinearLayout_content.getChildAt(i)).setTextColor(getResources().getColor(R.color.black));
                        }
                        mTextView.setTextColor(getResources().getColor(R.color.A));
                        cateCode = mMCategory.son.get(0).code;
                        mMPageListView.setApiUpdate(ApisFactory.getApiMCropTechList().set(cateCode, 0.0, ""));
                        mMPageListView.pullLoad();
                    }
                });
            }
        }
        Helper.saveBuilder(F.UserId + "mMCategorys", mMCategorys);
        mMPageListView.setDataFormat(new DfSousuoJishu());
        mMPageListView.setApiUpdate(ApisFactory.getApiMCropTechList().set(cateCode, 0.0, ""));
        mMPageListView.pullLoad();
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("图文农技");
    }
}