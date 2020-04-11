//
//  CardGongju
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import com.mdx.framework.adapter.Card;
import android.view.View;

import com.ntdlg.ngg.item.Gongju;

public class CardGongju extends Card<String>{
	public String item;
	
	public CardGongju() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_GONGJU;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = Gongju.getView(context, null);;
        }
        Gongju mGongju=(Gongju) contentView.getTag();
//        mGongju.set(item);
        return contentView;
    }
    
    

}


