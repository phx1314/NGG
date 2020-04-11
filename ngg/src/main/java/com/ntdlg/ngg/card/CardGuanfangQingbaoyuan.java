//
//  CardGuanfangQingbaoyuan
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.GuanfangQingbaoyuan;

public class CardGuanfangQingbaoyuan extends Card<String>{
	public String item;
	
	public CardGuanfangQingbaoyuan() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_GUANFANGQINGBAOYUAN;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = GuanfangQingbaoyuan.getView(context, null);;
        }
        GuanfangQingbaoyuan mGuanfangQingbaoyuan=(GuanfangQingbaoyuan) contentView.getTag();
//        mGuanfangQingbaoyuan.set(item);
        return contentView;
    }
    
    

}


