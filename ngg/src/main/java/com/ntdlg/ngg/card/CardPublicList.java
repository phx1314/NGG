//
//  CardPublicList
//
//  Created by df on 2017-02-07 14:42:18
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import com.mdx.framework.adapter.Card;
import android.view.View;

import com.ntdlg.ngg.item.PublicList;

public class CardPublicList extends Card<String>{
	public String item;
	
	public CardPublicList() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_PUBLICLIST;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = PublicList.getView(context, null);;
        }
        PublicList mPublicList=(PublicList) contentView.getTag();
        mPublicList.set(item);
        return contentView;
    }
    
    

}


