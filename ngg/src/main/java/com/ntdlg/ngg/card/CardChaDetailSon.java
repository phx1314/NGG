//
//  CardChaDetailSon
//
//  Created by df on 2017-03-08 16:10:04
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.ChaDetailSon;

public class CardChaDetailSon extends Card<String>{
	public String item;
	
	public CardChaDetailSon() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_CHADETAILSON;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = ChaDetailSon.getView(context, null);;
        }
        ChaDetailSon mChaDetailSon=(ChaDetailSon) contentView.getTag();
//        mChaDetailSon.set(item);
        return contentView;
    }
    
    

}


