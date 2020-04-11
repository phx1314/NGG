//
//  FrgAddZuowu
//
//  Created by df on 2017-03-10 13:51:13
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.mdx.framework.Frame;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.ada.AdaAddZuowuLeft;
import com.ntdlg.ngg.ada.AdaAddZuowuRight;
import com.ntdlg.ngg.view.ModelZuoWuGaoJi;
import com.ntdlg.ngg.view.ModelZuoWuPOsition;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MCategory;
import com.udows.common.proto.MCategoryList;

import java.util.ArrayList;
import java.util.List;


public class FrgAddZuowu extends BaseFrg {

    public MPageListView mMPageListView;
    public GridView mGridView;
    public static List<MCategory> mMCategorys=new ArrayList<>();
    public  AdaAddZuowuRight mAdaAddZuowuRight;
    @Override
    protected void create(Bundle savedInstanceState) {
        mMCategorys =Helper.readBuilder(F.UserId + "mMCategorys", mMCategorys);
        setContentView(R.layout.frg_add_zuowu);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                ModelZuoWuPOsition item = (ModelZuoWuPOsition) obj;
                boolean isWu = true;
                for (MCategory mMCategory : mMCategorys) {
                    if (mMCategory.code.equals(item.mMCategory.code)) {
                        mMCategorys.remove(mMCategory);
                        isWu = false;
                        break;
                    }
                }
                if (isWu) {
                    mMCategorys.add(item.mMCategory);
                    ((AdaAddZuowuLeft) mMPageListView.getListAdapter()).get(item.position).num++;
                } else {
                    ((AdaAddZuowuLeft) mMPageListView.getListAdapter()).get(item.position).num--;
                }
                ((AdaAddZuowuLeft) mMPageListView.getListAdapter()).notifyDataSetChanged();
                mAdaAddZuowuRight.notifyDataSetChanged();
                break;
            case 1:
                mAdaAddZuowuRight=new AdaAddZuowuRight(getContext(), ((AdaAddZuowuLeft) mMPageListView.getListAdapter()).get(Integer.valueOf(obj.toString())).mMCategory.son, Integer.valueOf(obj.toString()));
                mGridView.setAdapter(mAdaAddZuowuRight);
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);
        mGridView = (GridView) findViewById(R.id.mGridView);


    }

    public void loaddata() {
        ApisFactory.getApiMCropCate().load(getContext(), this, "MCropCate");
    }

    public void MCropCate(Son s) {
        MCategoryList mMCategoryList = (MCategoryList) s.getBuild();
        List<ModelZuoWuGaoJi> mModelZuoWuGaoJis = new ArrayList<>();
        for (int i = 0; i < mMCategoryList.mini.size(); i++) {
            ModelZuoWuGaoJi mModelZuoWuGaoJi = new ModelZuoWuGaoJi(mMCategoryList.mini.get(i));
            for (MCategory mMCategory : mMCategoryList.mini.get(i).son) {
                for (MCategory mMCategory2 : mMCategorys) {
                    if (mMCategory.code.equals(mMCategory2.code)) {
                        mModelZuoWuGaoJi.num++;
                        break;
                    }
                }

            }
            mModelZuoWuGaoJis.add(mModelZuoWuGaoJi);
        }
        mMPageListView.setAdapter(new AdaAddZuowuLeft(getContext(), mModelZuoWuGaoJis));
        if (mMCategoryList.mini.size() > 0){
            mAdaAddZuowuRight=new AdaAddZuowuRight(getContext(), mMCategoryList.mini.get(0).son, 0);
            mGridView.setAdapter(mAdaAddZuowuRight);
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("添加作物");
        mHeadlayout.setRText("确定");
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMCategorys.size() <= 0) {
                    Helper.toast("请选择至少一个作物", getContext());
                    return;
                }
                Frame.HANDLES.sentAll("FrgTuwennongji", 1, mMCategorys);
                FrgAddZuowu.this.finish();
            }
        });
    }
}