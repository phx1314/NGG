//
//  CardWenti
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.Wenti;

public class CardWenti extends Card<String>{
	public String item;
	
	public CardWenti() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_WENTI;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = Wenti.getView(context, null);;
        }
        Wenti mWenti=(Wenti) contentView.getTag();
//        mWenti.set(item);
        return contentView;
    }
    
    

}


