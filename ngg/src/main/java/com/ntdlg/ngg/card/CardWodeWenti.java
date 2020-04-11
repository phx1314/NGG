//
//  CardWodeWenti
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.WodeWenti;

public class CardWodeWenti extends Card<String>{
	public String item;
	
	public CardWodeWenti() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_WODEWENTI;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = WodeWenti.getView(context, null);;
        }
        WodeWenti mWodeWenti=(WodeWenti) contentView.getTag();
//        mWodeWenti.set(item);
        return contentView;
    }
    
    

}


