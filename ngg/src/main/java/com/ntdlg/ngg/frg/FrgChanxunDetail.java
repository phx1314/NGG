//
//  FrgChanxunDetail
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.item.ChaDetailSon;
import com.ntdlg.ngg.view.ModelFz;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MPesticideSearchDetail;


public class FrgChanxunDetail extends BaseFrg {

    public TextView mTextView_changjia;
    public TextView mTextView_dj;
    public TextView mTextView_hl;
    public TextView mTextView_cf;
    public TextView mTextView_dx;
    public TextView mTextView_jx;
    public TextView mTextView_time;
    public LinearLayout mLinearLayout_address;
    public TextView mTextView_address;
    public TextView mTextView_phone;
    public TextView mTextView_czh;
    public TextView mTextView_yb;
    public TextView mTextView_lianxiren;
    public String id;
    public LinearLayout mLinearLayout_content;
    public MPesticideSearchDetail mMPesticideSearchDetail;

    @Override
    protected void create(Bundle savedInstanceState) {
        id = getActivity().getIntent().getStringExtra("id");
        setContentView(R.layout.frg_chanxun_detail);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_changjia = (TextView) findViewById(R.id.mTextView_changjia);
        mTextView_dj = (TextView) findViewById(R.id.mTextView_dj);
        mTextView_hl = (TextView) findViewById(R.id.mTextView_hl);
        mTextView_cf = (TextView) findViewById(R.id.mTextView_cf);
        mTextView_dx = (TextView) findViewById(R.id.mTextView_dx);
        mTextView_jx = (TextView) findViewById(R.id.mTextView_jx);
        mTextView_time = (TextView) findViewById(R.id.mTextView_time);
        mLinearLayout_address = (LinearLayout) findViewById(R.id.mLinearLayout_address);
        mTextView_address = (TextView) findViewById(R.id.mTextView_address);
        mTextView_phone = (TextView) findViewById(R.id.mTextView_phone);
        mTextView_czh = (TextView) findViewById(R.id.mTextView_czh);
        mTextView_yb = (TextView) findViewById(R.id.mTextView_yb);
        mTextView_lianxiren = (TextView) findViewById(R.id.mTextView_lianxiren);
        mLinearLayout_content = (LinearLayout) findViewById(R.id.mLinearLayout_content);
        mLinearLayout_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgFujin.class, NoTitleAct.class, "type", 2, "mlat", mMPesticideSearchDetail.lat, "mlng", mMPesticideSearchDetail.lng);
            }
        });

    }

    public void loaddata() {
        ApisFactory.getApiMPesticideSearchDetail().load(getContext(), this, "MPesticideSearchDetail", id);
    }

    public void MPesticideSearchDetail(Son s) {
        mMPesticideSearchDetail = (MPesticideSearchDetail) s.getBuild();
        mTextView_changjia.setText(mMPesticideSearchDetail.pro_firm_name);
        mTextView_dj.setText(mMPesticideSearchDetail.cert_no);
        mTextView_hl.setText(mMPesticideSearchDetail.total_inside);
        mTextView_cf.setText(mMPesticideSearchDetail.cheng_one_per + mMPesticideSearchDetail.cheng_one + " " + mMPesticideSearchDetail.cheng_two_per + mMPesticideSearchDetail.cheng_two + " " + mMPesticideSearchDetail.cheng_three_per + mMPesticideSearchDetail.cheng_three + " " + mMPesticideSearchDetail.cheng_four_per + mMPesticideSearchDetail.cheng_four + " " + mMPesticideSearchDetail.cheng_five_per + mMPesticideSearchDetail.cheng_five + " " + mMPesticideSearchDetail.cheng_six_per + mMPesticideSearchDetail.cheng_six);
        mTextView_dx.setText(mMPesticideSearchDetail.druge_name);
        mTextView_jx.setText(mMPesticideSearchDetail.ji_name);
        mTextView_time.setText(mMPesticideSearchDetail.beginTime + "至" + mMPesticideSearchDetail.endTime);
        mTextView_address.setText(mMPesticideSearchDetail.address);
        mTextView_phone.setText("电话号码："+mMPesticideSearchDetail.phone);
        mTextView_czh.setText("传真号码："+mMPesticideSearchDetail.fax);
        mTextView_yb.setText("邮编："+mMPesticideSearchDetail.zipCode);
        mTextView_lianxiren.setText("联系人："+mMPesticideSearchDetail.contact);
        addView(mMPesticideSearchDetail.crop_one, mMPesticideSearchDetail.crop_one_fang, mMPesticideSearchDetail.crop_one_cheng, mMPesticideSearchDetail.crop_one_way);
        addView(mMPesticideSearchDetail.crop_two, mMPesticideSearchDetail.crop_two_fang, mMPesticideSearchDetail.crop_two_cheng, mMPesticideSearchDetail.crop_two_way);
        addView(mMPesticideSearchDetail.crop_three, mMPesticideSearchDetail.crop_three_fang, mMPesticideSearchDetail.crop_three_cheng, mMPesticideSearchDetail.crop_three_way);
        addView(mMPesticideSearchDetail.crop_four, mMPesticideSearchDetail.crop_four_fang, mMPesticideSearchDetail.crop_four_cheng, mMPesticideSearchDetail.crop_four_way);
        addView(mMPesticideSearchDetail.crop_five, mMPesticideSearchDetail.crop_five_fang, mMPesticideSearchDetail.crop_five_cheng, mMPesticideSearchDetail.crop_five_way);
        addView(mMPesticideSearchDetail.crop_six, mMPesticideSearchDetail.crop_six_fang, mMPesticideSearchDetail.crop_six_cheng, mMPesticideSearchDetail.crop_six_way);
        addView(mMPesticideSearchDetail.crop_seven, mMPesticideSearchDetail.crop_seven_fang, mMPesticideSearchDetail.crop_seven_cheng, mMPesticideSearchDetail.crop_seven_way);
        addView(mMPesticideSearchDetail.crop_eight, mMPesticideSearchDetail.crop_eight_fang, mMPesticideSearchDetail.crop_eight_cheng, mMPesticideSearchDetail.crop_eight_way);
        addView(mMPesticideSearchDetail.crop_nine, mMPesticideSearchDetail.crop_nine_fang, mMPesticideSearchDetail.crop_nine_cheng, mMPesticideSearchDetail.crop_nine_way);
        addView(mMPesticideSearchDetail.crop_ten, mMPesticideSearchDetail.crop_ten_fang, mMPesticideSearchDetail.crop_ten_cheng, mMPesticideSearchDetail.crop_ten_way);

    }

    public void addView(String a, String b, String c, String d) {
        if (!TextUtils.isEmpty(a) && !TextUtils.isEmpty(b) && !TextUtils.isEmpty(c) &&
                !TextUtils.isEmpty(d)) {
            View view1 = ChaDetailSon.getView(getContext(), null);
            ((ChaDetailSon) view1.getTag()).set(new ModelFz(a, b, c, d));
            mLinearLayout_content.addView(view1);
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("详情");
    }
}