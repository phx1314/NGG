//
//  CardGongjuTop
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.GongjuTop;

public class CardGongjuTop extends Card<String>{
	public String item;
	
	public CardGongjuTop() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_GONGJUTOP;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = GongjuTop.getView(context, null);;
        }
        GongjuTop mGongjuTop=(GongjuTop) contentView.getTag();
//        mGongjuTop.set(item);
        return contentView;
    }
    
    

}


