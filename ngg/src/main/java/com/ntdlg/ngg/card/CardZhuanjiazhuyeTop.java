//
//  CardZhuanjiazhuyeTop
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.ZhuanjiazhuyeTop;

public class CardZhuanjiazhuyeTop extends Card<String>{
	public String item;
	
	public CardZhuanjiazhuyeTop() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_ZHUANJIAZHUYETOP;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = ZhuanjiazhuyeTop.getView(context, null);;
        }
        ZhuanjiazhuyeTop mZhuanjiazhuyeTop=(ZhuanjiazhuyeTop) contentView.getTag();
//        mZhuanjiazhuyeTop.set(item);
        return contentView;
    }
    
    

}


