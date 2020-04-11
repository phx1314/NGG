//
//  CardShouyePop
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import com.mdx.framework.adapter.Card;
import android.view.View;

import com.ntdlg.ngg.item.ShouyePop;

public class CardShouyePop extends Card<String>{
	public String item;
	
	public CardShouyePop() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_SHOUYEPOP;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = ShouyePop.getView(context, null);;
        }
        ShouyePop mShouyePop=(ShouyePop) contentView.getTag();
        mShouyePop.set(item);
        return contentView;
    }
    
    

}


