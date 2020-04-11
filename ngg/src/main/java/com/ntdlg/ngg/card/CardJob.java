//
//  CardJob
//
//  Created by DELL on 2017-05-12 16:46:18
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import com.mdx.framework.adapter.Card;
import android.view.View;

import com.ntdlg.ngg.item.Job;

public class CardJob extends Card<String>{
	public String item;
	
	public CardJob() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_JOB;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = Job.getView(context, null);;
        }
        Job mJob=(Job) contentView.getTag();
        mJob.set(item);
        return contentView;
    }
    
    

}


