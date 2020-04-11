//
//  CardNyfenle
//
//  Created by Administrator on 2017-04-08 12:41:48
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.Nyfenle;

public class CardNyfenle extends Card<String>{
	public String item;
	
	public CardNyfenle() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_NYFENLE;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = Nyfenle.getView(context, null);;
        }
        Nyfenle mNyfenle=(Nyfenle) contentView.getTag();
//        mNyfenle.set(item);
        return contentView;
    }
    
    

}


