//
//  CardSousuoJishu
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.SousuoJishu;

public class CardSousuoJishu extends Card<String>{
	public String item;
	
	public CardSousuoJishu() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_SOUSUOJISHU;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = SousuoJishu.getView(context, null);;
        }
        SousuoJishu mSousuoJishu=(SousuoJishu) contentView.getTag();
//        mSousuoJishu.set(item);
        return contentView;
    }
    
    

}


