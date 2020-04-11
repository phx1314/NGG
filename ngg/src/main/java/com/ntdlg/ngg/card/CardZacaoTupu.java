//
//  CardZacaoTupu
//
//  Created by df on 2017-03-13 13:28:39
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.ZacaoTupu;

public class CardZacaoTupu extends Card<String>{
	public String item;
	
	public CardZacaoTupu() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_ZACAOTUPU;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = ZacaoTupu.getView(context, null);;
        }
        ZacaoTupu mZacaoTupu=(ZacaoTupu) contentView.getTag();
//        mZacaoTupu.set(item);
        return contentView;
    }
    
    

}


