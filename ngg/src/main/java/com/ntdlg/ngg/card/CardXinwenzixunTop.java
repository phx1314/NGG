//
//  CardXinwenzixunTop
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.XinwenzixunTop;

public class CardXinwenzixunTop extends Card<String>{
	public String item;
	
	public CardXinwenzixunTop() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_XINWENZIXUNTOP;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = XinwenzixunTop.getView(context, null);;
        }
        XinwenzixunTop mXinwenzixunTop=(XinwenzixunTop) contentView.getTag();
//        mXinwenzixunTop.set(item);
        return contentView;
    }
    
    

}


