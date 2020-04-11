//
//  CardNyqshFleiDetail
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import com.mdx.framework.adapter.Card;
import android.view.View;

import com.ntdlg.ngg.item.NyqshFleiDetail;

public class CardNyqshFleiDetail extends Card<String>{
	public String item;
	
	public CardNyqshFleiDetail() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_NYQSHFLEIDETAIL;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = NyqshFleiDetail.getView(context, null);;
        }
        NyqshFleiDetail mNyqshFleiDetail=(NyqshFleiDetail) contentView.getTag();
        mNyqshFleiDetail.set(item);
        return contentView;
    }
    
    

}


