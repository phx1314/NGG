//
//  CardWentiTop
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import com.mdx.framework.adapter.Card;
import android.view.View;

import com.ntdlg.ngg.item.WentiTop;

public class CardWentiTop extends Card<String>{
	public String item;
	
	public CardWentiTop() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_WENTITOP;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = WentiTop.getView(context, null);;
        }
        WentiTop mWentiTop=(WentiTop) contentView.getTag();
//        mWentiTop.set(item);
        return contentView;
    }
    
    

}


