//
//  CardNzHqTop
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import com.mdx.framework.adapter.Card;
import android.view.View;

import com.ntdlg.ngg.item.NzHqTop;

public class CardNzHqTop extends Card<String>{
	public String item;
	
	public CardNzHqTop() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_NZHQTOP;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = NzHqTop.getView(context, null);;
        }
        NzHqTop mNzHqTop=(NzHqTop) contentView.getTag();
        mNzHqTop.set(item);
        return contentView;
    }
    
    

}


