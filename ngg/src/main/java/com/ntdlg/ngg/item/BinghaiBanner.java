//
//  BinghaiBanner
//
//  Created by df on 2017-03-10 10:14:12
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.R;



public class BinghaiBanner extends BaseItem{
    public MImageView mImageView;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_binghai_banner,null);
	     convertView.setTag( new BinghaiBanner(convertView));
	     return convertView;
	}

	public BinghaiBanner(View view){
		this.contentview=view;
		this.context=contentview.getContext();
		initView();
	}
    
    private void initView() {
    	this.contentview.setTag(this);
    	findVMethod();
    }

    private void findVMethod(){
        mImageView=(MImageView)contentview.findViewById(R.id.mImageView);


    }

    public void set(String item){
		mImageView.setObj(item);
    }
    
    

}