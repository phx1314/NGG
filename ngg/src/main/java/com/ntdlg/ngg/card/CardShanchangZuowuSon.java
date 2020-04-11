//
//  CardShanchangZuowuSon
//
//  Created by df on 2017-02-09 15:44:30
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.ShanchangZuowuSon;

public class CardShanchangZuowuSon extends Card<String>{
	public String item;
	
	public CardShanchangZuowuSon() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_SHANCHANGZUOWUSON;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = ShanchangZuowuSon.getView(context, null);;
        }
        ShanchangZuowuSon mShanchangZuowuSon=(ShanchangZuowuSon) contentView.getTag();
//        mShanchangZuowuSon.set(item);
        return contentView;
    }
    
    

}


