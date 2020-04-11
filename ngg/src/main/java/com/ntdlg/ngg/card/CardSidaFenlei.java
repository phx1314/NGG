//
//  CardSidaFenlei
//
//  Created by Administrator on 2017-04-08 12:06:18
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.SidaFenlei;

public class CardSidaFenlei extends Card<String>{
	public String item;
	
	public CardSidaFenlei() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_SIDAFENLEI;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = SidaFenlei.getView(context, null);;
        }
        SidaFenlei mSidaFenlei=(SidaFenlei) contentView.getTag();
//        mSidaFenlei.set(item);
        return contentView;
    }
    
    

}


