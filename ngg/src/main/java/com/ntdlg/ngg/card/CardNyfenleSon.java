//
//  CardNyfenleSon
//
//  Created by Administrator on 2017-04-08 12:56:49
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.NyfenleSon;

public class CardNyfenleSon extends Card<String>{
	public String item;
	
	public CardNyfenleSon() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_NYFENLESON;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = NyfenleSon.getView(context, null);;
        }
        NyfenleSon mNyfenleSon=(NyfenleSon) contentView.getTag();
//        mNyfenleSon.set(item);
        return contentView;
    }
    
    

}


