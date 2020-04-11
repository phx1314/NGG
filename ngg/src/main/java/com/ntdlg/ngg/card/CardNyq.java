//
//  CardNyq
//
//  Created by df on 2017-02-10 14:53:54
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.Nyq;

public class CardNyq extends Card<String>{
	public String item;
	
	public CardNyq() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_NYQ;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = Nyq.getView(context, null);;
        }
        Nyq mNyq=(Nyq) contentView.getTag();
//        mNyq.set(item);
        return contentView;
    }
    
    

}


