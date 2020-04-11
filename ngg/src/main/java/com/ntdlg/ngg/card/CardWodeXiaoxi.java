//
//  CardWodeXiaoxi
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.WodeXiaoxi;

public class CardWodeXiaoxi extends Card<String>{
	public String item;
	
	public CardWodeXiaoxi() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_WODEXIAOXI;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = WodeXiaoxi.getView(context, null);;
        }
        WodeXiaoxi mWodeXiaoxi=(WodeXiaoxi) contentView.getTag();
//        mWodeXiaoxi.set(item);
        return contentView;
    }
    
    

}


