//
//  CardChaContent
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.ChaContent;

public class CardChaContent extends Card<String>{
	public String item;
	
	public CardChaContent() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_CHACONTENT;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = ChaContent.getView(context, null);;
        }
        ChaContent mChaContent=(ChaContent) contentView.getTag();
//        mChaContent.set(item);
        return contentView;
    }
    
    

}


