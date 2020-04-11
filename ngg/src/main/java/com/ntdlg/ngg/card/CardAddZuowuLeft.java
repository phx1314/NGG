//
//  CardAddZuowuLeft
//
//  Created by df on 2017-03-10 13:50:16
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.AddZuowuLeft;

public class CardAddZuowuLeft extends Card<String>{
	public String item;
	
	public CardAddZuowuLeft() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_ADDZUOWULEFT;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = AddZuowuLeft.getView(context, null);;
        }
        AddZuowuLeft mAddZuowuLeft=(AddZuowuLeft) contentView.getTag();
//        mAddZuowuLeft.set(item);
        return contentView;
    }
    
    

}


