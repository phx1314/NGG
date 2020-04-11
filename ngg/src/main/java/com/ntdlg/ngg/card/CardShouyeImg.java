//
//  CardShouyeImg
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.ShouyeImg;

public class CardShouyeImg extends Card<String>{
	public String item;
	
	public CardShouyeImg() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_SHOUYEIMG;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = ShouyeImg.getView(context, null);;
        }
        ShouyeImg mShouyeImg=(ShouyeImg) contentView.getTag();
//        mShouyeImg.set(item);
        return contentView;
    }
    
    

}


