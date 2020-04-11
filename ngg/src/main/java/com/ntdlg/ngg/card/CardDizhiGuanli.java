//
//  CardDizhiGuanli
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.DizhiGuanli;

public class CardDizhiGuanli extends Card<String>{
	public String item;
	
	public CardDizhiGuanli() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_DIZHIGUANLI;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = DizhiGuanli.getView(context, null);;
        }
        DizhiGuanli mDizhiGuanli=(DizhiGuanli) contentView.getTag();
//        mDizhiGuanli.set(item);
        return contentView;
    }
    
    

}


