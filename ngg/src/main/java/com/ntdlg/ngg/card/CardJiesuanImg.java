//
//  CardJiesuanImg
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import com.mdx.framework.adapter.Card;
import android.view.View;

import com.ntdlg.ngg.item.JiesuanImg;

public class CardJiesuanImg extends Card<String>{
	public String item;
	
	public CardJiesuanImg() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_JIESUANIMG;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = JiesuanImg.getView(context, null);;
        }
        JiesuanImg mJiesuanImg=(JiesuanImg) contentView.getTag();
        mJiesuanImg.set(item);
        return contentView;
    }
    
    

}


