//
//  ShouyePop
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



public class ShouyePop extends BaseItem{
    public TextView clk_mTextView_fjdwt;
    public TextView clk_mTextView_tjwt;
    public TextView clk_mTextView_djjwt;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_shouye_pop,null);
	     convertView.setTag( new ShouyePop(convertView));
	     return convertView;
	}

	public ShouyePop(View view){
		this.contentview=view;
		this.context=contentview.getContext();
		initView();
	}
    
    private void initView() {
    	this.contentview.setTag(this);
    	findVMethod();
    }

    private void findVMethod(){
        clk_mTextView_fjdwt=(TextView)contentview.findViewById(R.id.clk_mTextView_fjdwt);
        clk_mTextView_tjwt=(TextView)contentview.findViewById(R.id.clk_mTextView_tjwt);
        clk_mTextView_djjwt=(TextView)contentview.findViewById(R.id.clk_mTextView_djjwt);

        clk_mTextView_fjdwt.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mTextView_tjwt.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mTextView_djjwt.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void set(String item){

    }
    
	@Override
	public void onClick(android.view.View v) {

        if(R.id.clk_mTextView_fjdwt==v.getId()){

        }else if(R.id.clk_mTextView_tjwt==v.getId()){

        }else if(R.id.clk_mTextView_djjwt==v.getId()){

        }
	}    

}