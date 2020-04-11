//
//  CardNyqDetail
//
//  Created by df on 2017-02-10 14:00:09
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.NyqDetail;

public class CardNyqDetail extends Card<String>{
	public String item;
	
	public CardNyqDetail() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_NYQDETAIL;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = NyqDetail.getView(context, null);;
        }
        NyqDetail mNyqDetail=(NyqDetail) contentView.getTag();
//        mNyqDetail.set(item);
        return contentView;
    }
    
    

}


