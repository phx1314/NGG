//
//  CardWodeHuida
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.WodeHuida;

public class CardWodeHuida extends Card<String>{
	public String item;
	
	public CardWodeHuida() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_WODEHUIDA;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = WodeHuida.getView(context, null);;
        }
        WodeHuida mWodeHuida=(WodeHuida) contentView.getTag();
//        mWodeHuida.set(item);
        return contentView;
    }
    
    

}


