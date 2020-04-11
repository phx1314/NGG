//
//  BaseFrg
//
//  Created by df on 2016-12-29 15:28:00
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.framewidget.view.Headlayout;
import com.mdx.framework.activity.MFragment;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;

public abstract class BaseFrg  extends MFragment implements View.OnClickListener{
	public Headlayout mHeadlayout;
	@Override
	public void onClick(View v) {
		
	}
	@Override
	protected void initcreate(Bundle savedInstanceState) {
		super.initcreate(savedInstanceState);
		LoadingShow = true;
	}

	@Override
	public void setActionBar(ActionBar actionBar, Context context) {
		mHeadlayout = new Headlayout(context);
		mHeadlayout.setLeftBackground(R.drawable.bt_back_n);
		mHeadlayout.setRTColor(getResources().getColor(R.color.white));
		mHeadlayout.setGoBack(getActivity());
		actionBar.addView(mHeadlayout);
	}

	@Override
	public void finish() {
		super.finish();
		F.closeSoftKey(getActivity());
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		F.closeSoftKey(getActivity());
	}


	@Override
	public void onResume() {
		super.onResume();
		F.closeSoftKey(getActivity());
	}
}
