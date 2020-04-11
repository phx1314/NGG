//
//  CardAddZuowuRight
//
//  Created by df on 2017-03-10 13:50:16
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.AddZuowuRight;

public class CardAddZuowuRight extends Card<String>{
	public String item;
	
	public CardAddZuowuRight() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_ADDZUOWURIGHT;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = AddZuowuRight.getView(context, null);;
        }
        AddZuowuRight mAddZuowuRight=(AddZuowuRight) contentView.getTag();
//        mAddZuowuRight.set(item);
        return contentView;
    }
    
    

}


