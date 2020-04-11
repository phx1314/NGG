//
//  CardSousuo
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import com.mdx.framework.adapter.Card;
import android.view.View;

import com.ntdlg.ngg.item.Sousuo;

public class CardSousuo extends Card<String>{
	public String item;
	
	public CardSousuo() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_SOUSUO;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = Sousuo.getView(context, null);;
        }
        Sousuo mSousuo=(Sousuo) contentView.getTag();
        mSousuo.set(item);
        return contentView;
    }
    
    

}


