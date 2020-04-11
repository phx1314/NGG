//
//  CardGuanfangQingbaoyuanTop
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.GuanfangQingbaoyuanTop;

public class CardGuanfangQingbaoyuanTop extends Card<String>{
	public String item;
	
	public CardGuanfangQingbaoyuanTop() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_GUANFANGQINGBAOYUANTOP;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = GuanfangQingbaoyuanTop.getView(context, null);;
        }
        GuanfangQingbaoyuanTop mGuanfangQingbaoyuanTop=(GuanfangQingbaoyuanTop) contentView.getTag();
//        mGuanfangQingbaoyuanTop.set(item);
        return contentView;
    }
    
    

}


