//
//  CardChaTitle
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import com.mdx.framework.adapter.Card;
import android.view.View;

import com.ntdlg.ngg.item.ChaTitle;

public class CardChaTitle extends Card<String>{
	public String item;
	
	public CardChaTitle() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_CHATITLE;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = ChaTitle.getView(context, null);;
        }
        ChaTitle mChaTitle=(ChaTitle) contentView.getTag();
        mChaTitle.set(item);
        return contentView;
    }
    
    

}


