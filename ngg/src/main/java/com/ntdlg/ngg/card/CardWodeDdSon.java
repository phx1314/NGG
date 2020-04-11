//
//  CardWodeDdSon
//
//  Created by df on 2017-03-07 16:57:42
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.WodeDdSon;

public class CardWodeDdSon extends Card<String>{
	public String item;
	
	public CardWodeDdSon() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_WODEDDSON;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = WodeDdSon.getView(context, null);
        }
        WodeDdSon mWodeDdSon=(WodeDdSon) contentView.getTag();
//        mWodeDdSon.set(item);
        return contentView;
    }
    
    

}


