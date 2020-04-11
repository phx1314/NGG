//
//  CardDialogBianji
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.DialogBianji;

public class CardDialogBianji extends Card<String>{
	public String item;
	
	public CardDialogBianji() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_DIALOGBIANJI;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = DialogBianji.getView(context, null);;
        }
        DialogBianji mDialogBianji=(DialogBianji) contentView.getTag();
//        mDialogBianji.set(item);
        return contentView;
    }
    
    

}


