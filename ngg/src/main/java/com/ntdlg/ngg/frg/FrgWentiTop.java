//
//  FrgWentiTop
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.frg;
import android.os.Bundle;

import com.ntdlg.ngg.R;

import com.mdx.framework.widget.MPageListView;
import android.widget.LinearLayout;
import android.widget.TextView;



public class FrgWentiTop extends BaseFrg{

    public MPageListView mMPageListView;
    public LinearLayout clk_mLinearLayout_jieda;
    public LinearLayout mLinearLayout_fs;
    public TextView mTextView_fs;


 	@Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_wenti_top);
        initView();
        loaddata();
    }

    private void initView(){
        findVMethod();
    }
    
    private void findVMethod() {
        mMPageListView=(MPageListView)findViewById(R.id.mMPageListView);
        clk_mLinearLayout_jieda=(LinearLayout)findViewById(R.id.clk_mLinearLayout_jieda);
        mLinearLayout_fs=(LinearLayout)findViewById(R.id.mLinearLayout_fs);
        mTextView_fs=(TextView)findViewById(R.id.mTextView_fs);

        clk_mLinearLayout_jieda.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }
    
    public void loaddata(){

    }
    
	@Override
	public void onClick(android.view.View v) {

        if(R.id.clk_mLinearLayout_jieda==v.getId()){

        }
	}   
 
}