//
//  CardChanxunJieguo
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.ChanxunJieguo;

public class CardChanxunJieguo extends Card<String>{
	public String item;
	
	public CardChanxunJieguo() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_CHANXUNJIEGUO;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = ChanxunJieguo.getView(context, null);;
        }
        ChanxunJieguo mChanxunJieguo=(ChanxunJieguo) contentView.getTag();
//        mChanxunJieguo.set(item);
        return contentView;
    }
    
    

}


