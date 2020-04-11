//
//  AdaGgdShangcheng
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
import com.ntdlg.ngg.item.GgdShangcheng;
import com.ntdlg.ngg.view.ModelShangCheng;

import java.util.List;

public class AdaGgdShangcheng extends MAdapter<ModelShangCheng>{

   public AdaGgdShangcheng(Context context, List<ModelShangCheng> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        ModelShangCheng item = get(position);
        if (convertView == null) {
            convertView = GgdShangcheng.getView(getContext(), parent);;
        }
        GgdShangcheng mGgdShangcheng=(GgdShangcheng) convertView.getTag();
        mGgdShangcheng.set(item);
        return convertView;
    }
}
