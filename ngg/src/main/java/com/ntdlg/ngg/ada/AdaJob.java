//
//  AdaJob
//
//  Created by DELL on 2017-05-12 16:46:18
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.Frame;
import com.mdx.framework.adapter.MAdapter;
import com.ntdlg.ngg.item.Job;

import java.util.List;

public class AdaJob extends MAdapter<String> {

    public AdaJob(Context context, List<String> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final String item = get(position);
        if (convertView == null) {
            convertView = Job.getView(getContext(), parent);
        }
        Job mJob = (Job) convertView.getTag();
        mJob.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Frame.HANDLES.sentAll("FrgXiugaiGerenziliao", 102, item);
                Frame.HANDLES.sentAll("FrgJobList", 0, null);
            }
        });
        return convertView;
    }
}
