//
//  CardWodeDd
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.WodeDd;

public class CardWodeDd extends Card<String>{
	public String item;
	
	public CardWodeDd() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_WODEDD;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = WodeDd.getView(context, null);;
        }
        WodeDd mWodeDd=(WodeDd) contentView.getTag();
//        mWodeDd.set(item);
        return contentView;
    }
    
    

}


