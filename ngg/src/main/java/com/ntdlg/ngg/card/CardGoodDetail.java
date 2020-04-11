//
//  CardGoodDetail
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import com.mdx.framework.adapter.Card;
import android.view.View;

import com.ntdlg.ngg.item.GoodDetail;

public class CardGoodDetail extends Card<String>{
	public String item;
	
	public CardGoodDetail() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_GOODDETAIL;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = GoodDetail.getView(context, null);;
        }
        GoodDetail mGoodDetail=(GoodDetail) contentView.getTag();
        mGoodDetail.set(item);
        return contentView;
    }
    
    

}


