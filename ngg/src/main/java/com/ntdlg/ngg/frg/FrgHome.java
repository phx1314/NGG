//
//  FrgHome
//
//  Created by df on 2016-12-29 15:28:00
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.framewidget.newMenu.SlidingFragment;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.service.LocService;


public class FrgHome extends BaseFrg {

    public LinearLayout mLinearLayout_content;
    public SlidingFragment mSlidingFragment;
    public android.support.v4.app.FragmentManager fragmentManager;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_home);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mLinearLayout_content = (LinearLayout) findViewById(R.id.mLinearLayout_content);

        mSlidingFragment = new SlidingFragment(this);
        fragmentManager = getActivity().getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.add(R.id.mLinearLayout_content, mSlidingFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        mSlidingFragment.addContentView(new FrgShouye(), "首页",
                R.drawable.btn_checked_1);
        mSlidingFragment.addContentView(new FrgGongju(), "工具",
                R.drawable.btn_checked_2);
        mSlidingFragment.addContentView(new FrgFaxian(), "发现",
                R.drawable.btn_checked_3);
        mSlidingFragment.addContentView(new FrgWode(), "我的",
                R.drawable.btn_checked_4);
        WindowManager wm = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);
        mSlidingFragment.setFadeDegree(0.5f);
    }

    public void loaddata() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().stopService(
                new Intent(getContext(), LocService.class));
    }
}