//
//  CardGoodDetailTop
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.GoodDetailTop;

public class CardGoodDetailTop extends Card<String>{
	public String item;
	
	public CardGoodDetailTop() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_GOODDETAILTOP;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = GoodDetailTop.getView(context, null);;
        }
        GoodDetailTop mGoodDetailTop=(GoodDetailTop) contentView.getTag();
//        mGoodDetailTop.set(item);
        return contentView;
    }
    
    

}


