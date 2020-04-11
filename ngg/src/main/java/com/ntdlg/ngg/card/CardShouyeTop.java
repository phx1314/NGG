//
//  CardShouyeTop
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import com.mdx.framework.adapter.Card;
import android.view.View;

import com.ntdlg.ngg.item.ShouyeTop;

public class CardShouyeTop extends Card<String>{
	public String item;
	
	public CardShouyeTop() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_SHOUYETOP;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = ShouyeTop.getView(context, null);;
        }
        ShouyeTop mShouyeTop=(ShouyeTop) contentView.getTag();
        mShouyeTop.set(item);
        return contentView;
    }
    
    

}


