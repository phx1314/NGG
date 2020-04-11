//
//  CardWenZhuanjiaPo
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.WenZhuanjiaPo;

public class CardWenZhuanjiaPo extends Card<String>{
	public String item;
	
	public CardWenZhuanjiaPo() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_WENZHUANJIAPO;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = WenZhuanjiaPo.getView(context, null);;
        }
        WenZhuanjiaPo mWenZhuanjiaPo=(WenZhuanjiaPo) contentView.getTag();
//        mWenZhuanjiaPo.set(item);
        return contentView;
    }
    
    

}


