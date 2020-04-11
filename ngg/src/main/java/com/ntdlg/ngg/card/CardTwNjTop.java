//
//  CardTwNjTop
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.TwNjTop;

public class CardTwNjTop extends Card<String>{
	public String item;
	
	public CardTwNjTop() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_TWNJTOP;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = TwNjTop.getView(context, null);;
        }
        TwNjTop mTwNjTop=(TwNjTop) contentView.getTag();
//        mTwNjTop.set(item);
        return contentView;
    }
    
    

}


