//
//  CardWodeGgd
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.WodeGgd;

public class CardWodeGgd extends Card<String>{
	public String item;
	
	public CardWodeGgd() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_WODEGGD;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = WodeGgd.getView(context, null);;
        }
        WodeGgd mWodeGgd=(WodeGgd) contentView.getTag();
//        mWodeGgd.set(item);
        return contentView;
    }
    
    

}


