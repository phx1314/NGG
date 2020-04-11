//
//  GoodDetailTop
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
import android.widget.ImageView;
import android.widget.TextView;

import com.framewidget.view.DfCirleCurr;
import com.ntdlg.ngg.R;
import com.udows.common.proto.MScGoods;



public class GoodDetailTop extends BaseItem{
    public DfCirleCurr mMImageView_bg;
    public ImageView clk_mImageView_back;
    public ImageView clk_mImageView_share;
    public TextView mTextView_title;
    public TextView mTextView_price;
    public TextView mTextView_content;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_good_detail_top,null);
	     convertView.setTag( new GoodDetailTop(convertView));
	     return convertView;
	}

	public GoodDetailTop(View view){
		this.contentview=view;
		this.context=contentview.getContext();
		initView();
	}
    
    private void initView() {
    	this.contentview.setTag(this);
    	findVMethod();
    }

    private void findVMethod(){
        mMImageView_bg=(DfCirleCurr)contentview.findViewById(R.id.mMImageView_bg);
        clk_mImageView_back=(ImageView)contentview.findViewById(R.id.clk_mImageView_back);
        clk_mImageView_share=(ImageView)contentview.findViewById(R.id.clk_mImageView_share);
        mTextView_title=(TextView)contentview.findViewById(R.id.mTextView_title);
        mTextView_price=(TextView)contentview.findViewById(R.id.mTextView_price);
        mTextView_content=(TextView)contentview.findViewById(R.id.mTextView_content);

        clk_mImageView_back.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mImageView_share.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void set(MScGoods item){

    }
    
	@Override
	public void onClick(android.view.View v) {

        if(R.id.clk_mImageView_back==v.getId()){

        }else if(R.id.clk_mImageView_share==v.getId()){

        }
	}    

}