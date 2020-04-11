//
//  CardBinghaiBanner
//
//  Created by df on 2017-03-10 10:14:12
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import com.mdx.framework.adapter.Card;
import android.view.View;

import com.ntdlg.ngg.item.BinghaiBanner;

public class CardBinghaiBanner extends Card<String>{
	public String item;
	
	public CardBinghaiBanner() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_BINGHAIBANNER;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = BinghaiBanner.getView(context, null);;
        }
        BinghaiBanner mBinghaiBanner=(BinghaiBanner) contentView.getTag();
        mBinghaiBanner.set(item);
        return contentView;
    }
    
    

}


