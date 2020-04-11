//
//  FrgZhenweichaxun
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.item.ChaContent;
import com.ntdlg.ngg.item.ChaTitle;


public class FrgZhenweichaxun extends BaseFrg {

    public LinearLayout mLinearLayout_top;
    public TextView mTextView_ny;
    public TextView mTextView_fl;
    public TextView mTextView_zhz;
    public TextView mTextView_weifei;
    public LinearLayout mLinearLayout_content;
    public RadioGroup mRadioGroup;
    public EditText mEditText;
    public TextView clk_mTextView_tijiao;
    public String data_ny_1[] = {"农药登记证", "生产批准与标准"};
    public String data_ny_1_2[] = {"有效成分", "企业名称", "登记号", "登记作物"};
    public String data_ny_2_2[] = {"企业名称", "生产标准号", "有效成分"};
    public double type1 = 1;
    public double type2 = 1;
    public com.framewidget.view.FixGridLayout mFixGridLayout;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_zhenweichaxun);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                type2 = Integer.valueOf(obj.toString()) + 1;
                for (int i = 0; i < mFixGridLayout.getChildCount(); i++) {
                    View mView = ChaContent.getView(getContext(), null);
                    ((ChaContent) mFixGridLayout.getChildAt(i).getTag()).reFrash(Integer.valueOf(obj.toString()));
                }
                if (type1 == 1) {
                    if (type2 == 1) {
                        mEditText.setHint("例如：苯磺隆");
                    } else if (type2 == 2) {
                        mEditText.setHint("请输入查询企业名称");
                    } else if (type2 == 3) {
                        mEditText.setHint("例如：PD20096247（直接输入数字）");
                    } else if (type2 == 4) {
                        mEditText.setHint("例如：小麦");
                    }
                } else if (type1 == 2) {
                    if (type2 == 1) {
                        mEditText.setHint("请输入查询企业名称");
                    } else if (type2 == 2) {
                        mEditText.setHint("例如HNP空格键14033-A0150(直接输入数字)");
                    } else if (type2 == 3) {
                        mEditText.setHint("例如：苯磺隆");
                    }
                }

                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mLinearLayout_top = (LinearLayout) findViewById(R.id.mLinearLayout_top);
        mTextView_ny = (TextView) findViewById(R.id.mTextView_ny);
        mTextView_fl = (TextView) findViewById(R.id.mTextView_fl);
        mTextView_zhz = (TextView) findViewById(R.id.mTextView_zhz);
        mTextView_weifei = (TextView) findViewById(R.id.mTextView_weifei);
        mLinearLayout_content = (LinearLayout) findViewById(R.id.mLinearLayout_content);
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        mEditText = (EditText) findViewById(R.id.mEditText);
        clk_mTextView_tijiao = (TextView) findViewById(R.id.clk_mTextView_tijiao);
        mFixGridLayout = (com.framewidget.view.FixGridLayout) findViewById(R.id.mFixGridLayout);

        clk_mTextView_tijiao.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int p) {
                type1 = p;
                mFixGridLayout.removeAllViews();
                type2 = 1;
                if (p == 1) {
                    mEditText.setHint("例如：苯磺隆");
                    for (int i = 0; i < data_ny_1_2.length; i++) {
                        View mView = ChaContent.getView(getContext(), null);
                        ((ChaContent) mView.getTag()).set(data_ny_1_2[i], i);
                        ((ChaContent) mView.getTag()).reFrash(0);
                        mFixGridLayout.addView(mView);
                    }
                } else if (p == 2) {
                    mEditText.setHint("请输入查询企业名称");
                    for (int i = 0; i < data_ny_2_2.length; i++) {
                        View mView = ChaContent.getView(getContext(), null);
                        ((ChaContent) mView.getTag()).set(data_ny_2_2[i], i);
                        ((ChaContent) mView.getTag()).reFrash(0);
                        mFixGridLayout.addView(mView);
                    }
                }

            }
        });
    }

    public void loaddata() {
        for (int i = 0; i < data_ny_1.length; i++) {
            RadioButton mRadioButton = (RadioButton) ChaTitle.getView(getContext(), null);
            mRadioButton.setId(i + 1);
            mRadioButton.setText(data_ny_1[i]);
            mRadioButton.setLayoutParams(new RadioGroup.LayoutParams(0, RadioGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            if (i == 0) {
                mRadioButton.setBackgroundResource(R.drawable.btn_checked_bh_jian_1);
                mRadioButton.setChecked(true);
            } else if ((i == data_ny_1.length - 1)) {
                mRadioButton.setBackgroundResource(R.drawable.btn_checked_bh_jian_3);
            } else {
                mRadioButton.setBackgroundResource(R.drawable.btn_checked_bh_jian_2);
            }
            mRadioGroup.addView(mRadioButton);
        }
        mFixGridLayout.setDividerCol(F.dip2px(getContext(), 10));
    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mTextView_tijiao == v.getId()) {
            if (TextUtils.isEmpty(mEditText.getText().toString())) {
                Helper.toast("请输入", getContext());
                return;
            }
            Helper.startActivity(getContext(), FrgChanxunJieguo.class, TitleAct.class, "type1", type1, "type2", type2, "key", mEditText.getText().toString());
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("真伪查询");
    }
}