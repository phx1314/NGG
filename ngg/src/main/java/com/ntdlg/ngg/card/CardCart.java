//
//  CardCart
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.Cart;

public class CardCart extends Card<String>{
	public String item;
	
	public CardCart() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_CART;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = Cart.getView(context, null);;
        }
        Cart mCart=(Cart) contentView.getTag();
//        mCart.set(item);
        return contentView;
    }
    
    

}


