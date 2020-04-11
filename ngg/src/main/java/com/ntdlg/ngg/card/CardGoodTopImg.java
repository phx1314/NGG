//
//  CardGoodTopImg
//
//  Created by df on 2017-03-02 15:56:26
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import com.mdx.framework.adapter.Card;
import android.view.View;

import com.ntdlg.ngg.item.GoodTopImg;

public class CardGoodTopImg extends Card<String>{
	public String item;
	
	public CardGoodTopImg() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_GOODTOPIMG;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = GoodTopImg.getView(context, null);;
        }
        GoodTopImg mGoodTopImg=(GoodTopImg) contentView.getTag();
        mGoodTopImg.set(item);
        return contentView;
    }
    
    

}


