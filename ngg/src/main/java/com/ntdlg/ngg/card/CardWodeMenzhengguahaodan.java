//
//  CardWodeMenzhengguahaodan
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.WodeMenzhengguahaodan;

public class CardWodeMenzhengguahaodan extends Card<String>{
	public String item;
	
	public CardWodeMenzhengguahaodan() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_WODEMENZHENGGUAHAODAN;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = WodeMenzhengguahaodan.getView(context, null);;
        }
        WodeMenzhengguahaodan mWodeMenzhengguahaodan=(WodeMenzhengguahaodan) contentView.getTag();
//        mWodeMenzhengguahaodan.set(item);
        return contentView;
    }
    
    

}


