//
//  AdaZacaoList
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.ntdlg.ngg.item.ZacaoList;
import com.ntdlg.ngg.view.ModelZaocao;

import java.util.List;

public class AdaZacaoList extends MAdapter<ModelZaocao>{

   public AdaZacaoList(Context context, List<ModelZaocao> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        ModelZaocao item = get(position);
        if (convertView == null) {
            convertView = ZacaoList.getView(getContext(), parent);;
        }
        ZacaoList mZacaoList=(ZacaoList) convertView.getTag();
        mZacaoList.set(item);
        return convertView;
    }
}
