//
//  CardZacaoList
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.ZacaoList;

public class CardZacaoList extends Card<String>{
	public String item;
	
	public CardZacaoList() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_ZACAOLIST;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = ZacaoList.getView(context, null);;
        }
        ZacaoList mZacaoList=(ZacaoList) contentView.getTag();
//        mZacaoList.set(item);
        return contentView;
    }
    
    

}


