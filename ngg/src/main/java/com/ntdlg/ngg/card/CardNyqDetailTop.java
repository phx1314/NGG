//
//  CardNyqDetailTop
//
//  Created by df on 2017-02-10 14:01:15
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.NyqDetailTop;

public class CardNyqDetailTop extends Card<String>{
	public String item;
	
	public CardNyqDetailTop() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_NYQDETAILTOP;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = NyqDetailTop.getView(context, null);;
        }
        NyqDetailTop mNyqDetailTop=(NyqDetailTop) contentView.getTag();
//        mNyqDetailTop.set(item);
        return contentView;
    }
    
    

}


