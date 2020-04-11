//
//  CardSousuoZhuanjia
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.SousuoZhuanjia;

public class CardSousuoZhuanjia extends Card<String>{
	public String item;
	
	public CardSousuoZhuanjia() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_SOUSUOZHUANJIA;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = SousuoZhuanjia.getView(context, null);;
        }
        SousuoZhuanjia mSousuoZhuanjia=(SousuoZhuanjia) contentView.getTag();
//        mSousuoZhuanjia.set(item);
        return contentView;
    }
    
    

}


