//
//  CardZacaoListErji
//
//  Created by df on 2017-03-13 10:31:18
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.ZacaoListErji;

public class CardZacaoListErji extends Card<String>{
	public String item;
	
	public CardZacaoListErji() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_ZACAOLISTERJI;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = ZacaoListErji.getView(context, null);;
        }
        ZacaoListErji mZacaoListErji=(ZacaoListErji) contentView.getTag();
//        mZacaoListErji.set(item);
        return contentView;
    }
    
    

}


