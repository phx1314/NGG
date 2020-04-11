//
//  CardShangchengDuihuanjilu
//
//  Created by DELL on 2017-04-01 15:46:12
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.ShangchengDuihuanjilu;

public class CardShangchengDuihuanjilu extends Card<String>{
	public String item;
	
	public CardShangchengDuihuanjilu() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_SHANGCHENGDUIHUANJILU;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = ShangchengDuihuanjilu.getView(context, null);;
        }
        ShangchengDuihuanjilu mShangchengDuihuanjilu=(ShangchengDuihuanjilu) contentView.getTag();
//        mShangchengDuihuanjilu.set(item);
        return contentView;
    }
    
    

}


