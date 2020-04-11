//
//  ZhuanjiaZhuye
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.item;

import com.ntdlg.ngg.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import android.view.View;
import android.widget.TextView;
import com.framewidget.view.FixGridLayout;
import com.mdx.framework.widget.MImageView;



public class ZhuanjiaZhuye extends BaseItem{
    public TextView mTextView_title;
    public TextView mTextView_type;
    public TextView mTextView_address;
    public FixGridLayout mFixGridLayout;
    public MImageView mMImageView_touxiang;
    public TextView mTextView_name;
    public TextView mTextView_time;
    public TextView mTextView_pl;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_zhuanjia_zhuye,null);
	     convertView.setTag( new ZhuanjiaZhuye(convertView));
	     return convertView;
	}

	public ZhuanjiaZhuye(View view){
		this.contentview=view;
		this.context=contentview.getContext();
		initView();
	}
    
    private void initView() {
    	this.contentview.setTag(this);
    	findVMethod();
    }

    private void findVMethod(){
        mTextView_title=(TextView)contentview.findViewById(R.id.mTextView_title);
        mTextView_type=(TextView)contentview.findViewById(R.id.mTextView_type);
        mTextView_address=(TextView)contentview.findViewById(R.id.mTextView_address);
        mFixGridLayout=(FixGridLayout)contentview.findViewById(R.id.mFixGridLayout);
        mMImageView_touxiang=(MImageView)contentview.findViewById(R.id.mMImageView_touxiang);
        mTextView_name=(TextView)contentview.findViewById(R.id.mTextView_name);
        mTextView_time=(TextView)contentview.findViewById(R.id.mTextView_time);
        mTextView_pl=(TextView)contentview.findViewById(R.id.mTextView_pl);


    }

    public void set(String item){

    }
    
    

}