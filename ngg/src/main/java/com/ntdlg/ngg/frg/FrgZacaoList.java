//
//  FrgZacaoList
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.ada.AdaZacaoTupu;
import com.ntdlg.ngg.ada.AdaZacaoTupu2;
import com.ntdlg.ngg.view.PinyinComparator;
import com.ntdlg.ngg.view.SideBar;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MCategory;
import com.udows.common.proto.MCategoryList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FrgZacaoList extends BaseFrg {

    public MPageListView mMPageListView;
    public TextView mEditText;
    public com.ntdlg.ngg.view.SideBar sidrbar;
    public TextView dialog;
    public RelativeLayout mRelativeLayout_1;
    public List<MCategory> mMCategorys = new ArrayList<>();
    public MPageListView mMPageListView_2;
    public LinearLayout mLinearLayout_serach;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_zacao_list);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);
        mEditText = (TextView) findViewById(R.id.mEditText);
        sidrbar = (com.ntdlg.ngg.view.SideBar) findViewById(R.id.sidrbar);
        dialog = (TextView) findViewById(R.id.dialog);
        mRelativeLayout_1 = (RelativeLayout) findViewById(R.id.mRelativeLayout_1);
        mMPageListView_2 = (MPageListView) findViewById(R.id.mMPageListView_2);
        mLinearLayout_serach = (LinearLayout) findViewById(R.id.mLinearLayout_serach);
        sidrbar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                dialog.setText(s);
                dialog.setVisibility(View.VISIBLE);
                // 该字母首次出现的位置
                int position = getPositionForSection(s.charAt(0), ((AdaZacaoTupu) mMPageListView.getListAdapter()).getList());
                if (position != -1) {
                    mMPageListView.setSelection(position + 1);
                } else if (s.equals("#")) {
                    mMPageListView.setSelection(0);
                }
            }

            @Override
            public void onUp() {
                dialog.setVisibility(View.INVISIBLE);
            }
        });
        sidrbar.setTextView(dialog);
//        mEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
//                                      int arg3) {
//                if (arg0.toString().equals("")) {
//                    mRelativeLayout_1.setVisibility(View.VISIBLE);
//                    mMPageListView_2.setVisibility(View.GONE);
//                } else {
//                    mRelativeLayout_1.setVisibility(View.GONE);
//                    mMPageListView_2.setVisibility(View.VISIBLE);
//                    filterData(arg0.toString());
//                }
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence arg0, int arg1,
//                                          int arg2, int arg3) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable arg0) {
//
//            }
//        });
        mLinearLayout_serach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgPublicSearChThree.class, TitleAct.class);
            }
        });
        mEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgPublicSearChThree.class, TitleAct.class);
            }
        });
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(char section, List<MCategory> mMCategorys) {
        for (int i = 0; i < mMCategorys.size(); i++) {
            String sortStr = F.toPinYin(mMCategorys.get(i).name.charAt(0));
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    public void filterData(String filterStr) {
        List<MCategory> filterDateList = new ArrayList<MCategory>();
        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = mMCategorys;
        } else {
            filterDateList.clear();
            for (MCategory sortModel : mMCategorys) {
                String name = sortModel.name;
                if (name.indexOf(filterStr.toString()) != -1
                        || F
                        .toPinYin(sortModel.name.charAt(0))
                        .startsWith(filterStr.toString()) || F
                        .toPinYin(sortModel.name.charAt(0)).toUpperCase()
                        .startsWith(filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }
        // // 根据a-z进行排序
//        Collections.sort(filterDateList, pinyinComparator);
        mMPageListView_2.setAdapter(new AdaZacaoTupu2(getContext(),
                filterDateList));
    }

    public void loaddata() {
        ApisFactory.getApiMZaCaoCate().load(getContext(), this, "MZaCaoCate", null);
    }

    public void MZaCaoCate(Son s) {
        MCategoryList mMCategoryList = (MCategoryList) s.getBuild();
        mMCategorys = mMCategoryList.mini;
        Collections.sort(mMCategorys, new PinyinComparator());
        mMPageListView.setAdapter(new AdaZacaoTupu(getContext(),
                mMCategorys));
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("杂草图谱");
    }
}

