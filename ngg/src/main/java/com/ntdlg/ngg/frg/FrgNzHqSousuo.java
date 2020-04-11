//
//  FrgNzHqSousuo
//
//  Created by df on 2017-03-14 09:28:58
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.frg;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.R;



public class FrgNzHqSousuo extends BaseFrg{

    public ImageView mImageView_back;
    public EditText mEditText;
    public ImageView mImageView_more;
    public MPageListView mMPageListView;


 	@Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_nz_hq_sousuo);
        initView();
        loaddata();
    }

    private void initView(){
        findVMethod();
    }
    
    private void findVMethod() {
        mImageView_back=(ImageView)findViewById(R.id.mImageView_back);
        mEditText=(EditText)findViewById(R.id.mEditText);
        mImageView_more=(ImageView)findViewById(R.id.mImageView_more);
        mMPageListView=(MPageListView)findViewById(R.id.mMPageListView);


    }
    
    public void loaddata(){

    }
    
   
 
}