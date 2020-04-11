//
//  Job
//
//  Created by DELL on 2017-05-12 16:46:18
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ntdlg.ngg.R;


public class Job extends BaseItem{


    public TextView mTextView_num;

    @SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_job,null);
	     convertView.setTag( new Job(convertView));
	     return convertView;
	}

	public Job(View view){
		this.contentview=view;
		this.context=contentview.getContext();
		initView();
	}
    
    private void initView() {
    	this.contentview.setTag(this);
    	findVMethod();
    }

    private void findVMethod(){


        mTextView_num = (TextView) findViewById(R.id.mTextView_num);
    }

    public void set(String item){
        mTextView_num.setText(item);
    }
    
    

}