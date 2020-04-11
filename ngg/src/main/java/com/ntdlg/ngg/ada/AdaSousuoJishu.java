//
//  AdaSousuoJishu
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.frg.FrgBinghaiDetail;
import com.ntdlg.ngg.item.SousuoJishu;
import com.udows.common.proto.MCropTech;

import java.util.List;

public class AdaSousuoJishu extends MAdapter<MCropTech> {

    public AdaSousuoJishu(Context context, List<MCropTech> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final MCropTech item = get(position);
        if (convertView == null) {
            convertView = SousuoJishu.getView(getContext(), parent);
        }
        SousuoJishu mSousuoJishu = (SousuoJishu) convertView.getTag();
        mSousuoJishu.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgBinghaiDetail.class, TitleAct.class, "mMCropTech", item);
            }
        });
        return convertView;
    }
}
