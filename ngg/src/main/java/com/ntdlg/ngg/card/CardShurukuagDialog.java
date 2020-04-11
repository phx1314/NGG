//
//  CardShurukuagDialog
//
//  Created by df on 2017-03-02 08:53:33
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.ShurukuagDialog;

public class CardShurukuagDialog extends Card<String>{
	public String item;
	
	public CardShurukuagDialog() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_SHURUKUAGDIALOG;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = ShurukuagDialog.getView(context, null);;
        }
        ShurukuagDialog mShurukuagDialog=(ShurukuagDialog) contentView.getTag();
//        mShurukuagDialog.set(item);
        return contentView;
    }
    
    

}


