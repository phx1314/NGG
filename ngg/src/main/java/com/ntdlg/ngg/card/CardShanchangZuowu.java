//
//  CardShanchangZuowu
//
//  Created by df on 2017-02-09 15:44:30
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.ShanchangZuowu;

public class CardShanchangZuowu extends Card<String>{
	public String item;
	
	public CardShanchangZuowu() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_SHANCHANGZUOWU;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = ShanchangZuowu.getView(context, null);;
        }
        ShanchangZuowu mShanchangZuowu=(ShanchangZuowu) contentView.getTag();
//        mShanchangZuowu.set(item);
        return contentView;
    }
    
    

}


