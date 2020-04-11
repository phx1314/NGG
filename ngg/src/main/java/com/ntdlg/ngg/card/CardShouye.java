//
//  CardShouye
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.Shouye;

public class CardShouye extends Card<String>{
	public String item;
	
	public CardShouye() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_SHOUYE;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = Shouye.getView(context, null);;
        }
        Shouye mShouye=(Shouye) contentView.getTag();
//        mShouye.set(item);
        return contentView;
    }
    
    

}


