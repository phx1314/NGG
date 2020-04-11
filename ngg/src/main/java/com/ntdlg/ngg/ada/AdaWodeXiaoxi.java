//
//  AdaWodeXiaoxi
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.framewidget.frg.FrgPtDetail;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.frg.FrgNzDetail;
import com.ntdlg.ngg.frg.FrgWentiDetail;
import com.ntdlg.ngg.frg.FrgZhuanjiaZhuye;
import com.ntdlg.ngg.item.WodeXiaoxi;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MNotify;

import java.util.List;

public class AdaWodeXiaoxi extends MAdapter<MNotify> {

    public AdaWodeXiaoxi(Context context, List<MNotify> list) {
        super(context, list);
    }

//    0:不跳转 1:url2:问答详情3专家申请通过4门店申请通过

    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final MNotify item = get(position);
        if (convertView == null) {
            convertView = WodeXiaoxi.getView(getContext(), parent);
        }
        WodeXiaoxi mWodeXiaoxi = (WodeXiaoxi) convertView.getTag();
        mWodeXiaoxi.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (item.redirectType) {
                    case 0:
                        break;
                    case 2:
                        Helper.startActivity(getContext(), FrgWentiDetail.class, TitleAct.class, "mid", item.redirectContent);
                        break;
                    case 3:
                        Helper.startActivity(getContext(), FrgZhuanjiaZhuye.class, NoTitleAct.class, "id", item.redirectContent);
                        break;
                    case 4:
                        if (item.title.contains("失败")) {
                        } else {
                            Helper.startActivity(getContext(), FrgNzDetail.class, NoTitleAct.class, "id", item.redirectContent);
                        }

                        break;
                    case 1:
                        Helper.startActivity(getContext(), FrgPtDetail.class, NoTitleAct.class, "url", item.redirectContent, "title", "详情");
                        break;
                }
                if (item.isRead == 0) {
                    ApisFactory.getApiMReadNotify().load(getContext(), AdaWodeXiaoxi.this, "MReadNotify", item.id);
                    item.isRead = 1;
                    AdaWodeXiaoxi.this.notifyDataSetChanged();
                }
            }
        });
        return convertView;
    }

    public void MReadNotify(Son s) {
        Frame.HANDLES.sentAll("FrgShouye,FrgWodeXiaoxi,FrgSousuoPub", 1, null);
    }

}
