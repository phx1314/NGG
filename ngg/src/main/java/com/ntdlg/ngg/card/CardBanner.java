//
//  CardBanner
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.Banner;

public class CardBanner extends Card<String>{
	public String item;
	
	public CardBanner() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_BANNER;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = Banner.getView(context, null);;
        }
        Banner mBanner=(Banner) contentView.getTag();
//        mBanner.set(item);
        return contentView;
    }
    
    

}


