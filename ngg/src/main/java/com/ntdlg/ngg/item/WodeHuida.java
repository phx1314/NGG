//
//  WodeHuida
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


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
import com.udows.common.proto.STopic;


public class WodeHuida extends BaseItem{
    public TextView mTextView_title;
    public TextView mTextView_time;
    public TextView mTextView_zan_f;
    public TextView mTextView_pl;
    public TextView mTextView_title_fu;
    public TextView mTextView_type;
    public TextView mTextView_address;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_wode_huida,null);
	     convertView.setTag( new WodeHuida(convertView));
	     return convertView;
	}

	public WodeHuida(View view){
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
        mTextView_time=(TextView)contentview.findViewById(R.id.mTextView_time);
        mTextView_zan_f=(TextView)contentview.findViewById(R.id.mTextView_zan_f);
        mTextView_pl=(TextView)contentview.findViewById(R.id.mTextView_pl);
        mTextView_title_fu=(TextView)contentview.findViewById(R.id.mTextView_title_fu);
        mTextView_type=(TextView)contentview.findViewById(R.id.mTextView_type);
        mTextView_address=(TextView)contentview.findViewById(R.id.mTextView_address);


    }

    public void set(STopic item){
        mTextView_title.setText(item.title);
        mTextView_time.setText(item.time);
        mTextView_zan_f.setText("("+item.praiseNoCnt+")");
        mTextView_pl.setText("("+item.praiseCnt+")");
        mTextView_title_fu.setText(item.topic.get(0).title);
        mTextView_type.setText(item.topic.get(0).label);
        mTextView_address.setText(item.topic.get(0).address);
    }
    
    

}