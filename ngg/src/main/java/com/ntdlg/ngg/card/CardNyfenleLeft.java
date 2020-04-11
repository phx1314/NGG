//
//  CardNyfenleLeft
//
//  Created by Administrator on 2017-04-08 12:41:48
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.NyfenleLeft;

public class CardNyfenleLeft extends Card<String>{
	public String item;
	
	public CardNyfenleLeft() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_NYFENLELEFT;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = NyfenleLeft.getView(context, null);;
        }
        NyfenleLeft mNyfenleLeft=(NyfenleLeft) contentView.getTag();
//        mNyfenleLeft.set(item);
        return contentView;
    }
    
    

}


