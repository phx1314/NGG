//
//  CardGoodDetailImg
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import com.mdx.framework.adapter.Card;
import android.view.View;

import com.ntdlg.ngg.item.GoodDetailImg;

public class CardGoodDetailImg extends Card<String>{
	public String item;
	
	public CardGoodDetailImg() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_GOODDETAILIMG;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = GoodDetailImg.getView(context, null);;
        }
        GoodDetailImg mGoodDetailImg=(GoodDetailImg) contentView.getTag();
        mGoodDetailImg.set(item);
        return contentView;
    }
    
    

}


