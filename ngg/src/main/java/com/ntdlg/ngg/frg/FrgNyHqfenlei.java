//
//  FrgNyHqfenlei
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framewidget.view.FixGridLayout;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.item.Nyfenle;
import com.ntdlg.ngg.item.NyfenleLeft;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MCategory;
import com.udows.common.proto.MCategoryList;

import static com.ntdlg.ngg.F.dip2px;


public class FrgNyHqfenlei extends BaseFrg {

    public TextView mEditText_sousuo;
    public LinearLayout mLinearLayout_content;
    public FixGridLayout mFixGridLayout;
    public String title;
    public int position;
    public MCategoryList mMCategoryList;
    public MCategoryList mMCategoryListSon;
    public LinearLayout mLinearLayout_search;

    //农药100，种子101肥料103农机104
    @Override
    protected void create(Bundle savedInstanceState) {
        title = getActivity().getIntent().getStringExtra("title");
        setContentView(R.layout.frg_ny_hqfenlei);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                position = Integer.valueOf(obj.toString());
                ApisFactory.getApiMPesticideCate().load(getContext(), this, "MPesticideCateSon", mMCategoryList.mini.get(position).code, "");
                for (int i = 0; i < mLinearLayout_content.getChildCount(); i++) {
                    View view = mLinearLayout_content.getChildAt(i);
                    ((NyfenleLeft) view.getTag()).set(mMCategoryList.mini.get(i), position, i);
                }
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mEditText_sousuo = (TextView) findViewById(R.id.mEditText_sousuo);
        mLinearLayout_content = (LinearLayout) findViewById(R.id.mLinearLayout_content);
        mFixGridLayout = (FixGridLayout) findViewById(R.id.mFixGridLayout);
        mLinearLayout_search = (LinearLayout) findViewById(R.id.mLinearLayout_search);

        mFixGridLayout.setDividerCol(dip2px(getContext(), 10));
        mFixGridLayout.setDividerLine(dip2px(getContext(), 10));
//        mEditText_sousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//
//            @Override
//            public boolean onEditorAction(TextView v, int actionId,
//                                          KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_GO) {
//                    mFixGridLayout.removeAllViews();
//                    for (MCategory mMCategory : mMCategoryListSon.mini) {
//                        if (mMCategory.name.contains(mEditText_sousuo.getText().toString())) {
//                            View view = Nyfenle.getView(getContext(), null);
//                            ((Nyfenle) view.getTag()).set(mMCategory);
//                            mFixGridLayout.addView(view);
//                        }
//                    }
//                    return true;
//                }
//                return false;
//            }
//        });
        mLinearLayout_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgNyqshFlSearch.class, TitleAct.class);
            }
        });
        mEditText_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgNyqshFlSearch.class, TitleAct.class);
            }
        });

    }

    public void loaddata() {
        ApisFactory.getApiMPesticideCate().load(getContext(), this, "MPesticideCate", "100","");
    }

    public void MPesticideCateSon(Son s) {
        mMCategoryListSon = (MCategoryList) s.getBuild();
        mFixGridLayout.removeAllViews();
        for (MCategory mMCategory : mMCategoryListSon.mini) {
            View view = Nyfenle.getView(getContext(), null);
            ((Nyfenle) view.getTag()).set(mMCategory);
            mFixGridLayout.addView(view);
        }
    }

    public void MPesticideCate(Son s) {
        mMCategoryList = (MCategoryList) s.getBuild();
        for (int i = 0; i < mMCategoryList.mini.size(); i++) {
            View view = NyfenleLeft.getView(getContext(), null);
            ((NyfenleLeft) view.getTag()).set(mMCategoryList.mini.get(i), position, i);
            mLinearLayout_content.addView(view);
        }
        if (mMCategoryList.mini.size() > 0) {
            ApisFactory.getApiMPesticideCate().load(getContext(), this, "MPesticideCateSon", mMCategoryList.mini.get(0).code,"");
        }

    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle(title);
    }
}