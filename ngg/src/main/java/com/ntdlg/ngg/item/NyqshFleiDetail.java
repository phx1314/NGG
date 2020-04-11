//
//  NyqshFleiDetail
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



public class NyqshFleiDetail extends BaseItem{
    public TextView mTextView_title;
    public TextView mTextView_content;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_nyqsh_flei_detail,null);
	     convertView.setTag( new NyqshFleiDetail(convertView));
	     return convertView;
	}

	public NyqshFleiDetail(View view){
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
        mTextView_content=(TextView)contentview.findViewById(R.id.mTextView_content);


    }

    public void set(String item){

    }
    
    

}