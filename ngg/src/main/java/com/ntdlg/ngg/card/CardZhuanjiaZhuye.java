//
//  CardZhuanjiaZhuye
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import com.mdx.framework.adapter.Card;
import android.view.View;

import com.ntdlg.ngg.item.ZhuanjiaZhuye;

public class CardZhuanjiaZhuye extends Card<String>{
	public String item;
	
	public CardZhuanjiaZhuye() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_ZHUANJIAZHUYE;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = ZhuanjiaZhuye.getView(context, null);;
        }
        ZhuanjiaZhuye mZhuanjiaZhuye=(ZhuanjiaZhuye) contentView.getTag();
        mZhuanjiaZhuye.set(item);
        return contentView;
    }
    
    

}


