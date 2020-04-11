//
//  FrgSousuo
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.framewidget.view.FixGridLayout;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.item.Sousuo;

import java.util.ArrayList;
import java.util.List;

import static com.ntdlg.ngg.F.dip2px;


public class FrgSousuo extends BaseFrg {

    public EditText clk_mTextView_sousuo;
    public TextView clk_mTextView_cancel;
    public RadioGroup mRadioGroup;
    public RadioButton mRadioButton1;
    public RadioButton mRadioButton2;
    public RadioButton mRadioButton3;
    public RadioButton mRadioButton4;
    public ImageView clk_mImageView_del;
    public FixGridLayout mFixGridLayout;
    public List<String> data_history = new ArrayList<>();
    public static int type = 1;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_sousuo);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        clk_mTextView_sousuo = (EditText) findViewById(R.id.clk_mTextView_sousuo);
        clk_mTextView_cancel = (TextView) findViewById(R.id.clk_mTextView_cancel);
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        mRadioButton1 = (RadioButton) findViewById(R.id.mRadioButton1);
        mRadioButton2 = (RadioButton) findViewById(R.id.mRadioButton2);
        mRadioButton3 = (RadioButton) findViewById(R.id.mRadioButton3);
        mRadioButton4 = (RadioButton) findViewById(R.id.mRadioButton4);
        clk_mImageView_del = (ImageView) findViewById(R.id.clk_mImageView_del);
        mFixGridLayout = (FixGridLayout) findViewById(R.id.mFixGridLayout);
        mFixGridLayout.setDividerLine(dip2px(getContext(),10));
        clk_mTextView_sousuo.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mTextView_cancel.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mImageView_del.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mTextView_sousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_GO) {
                    if (TextUtils.isEmpty(clk_mTextView_sousuo.getText().toString().trim())) {
                        Helper.toast("请输入", getContext());
                        return true;
                    }
                    if (!data_history.contains(clk_mTextView_sousuo.getText().toString().trim())) {
                        data_history.add(clk_mTextView_sousuo.getText().toString().trim());
                        View view = Sousuo.getView(getContext(), null);
                        ((Sousuo) view.getTag()).set(clk_mTextView_sousuo.getText().toString().trim());
                        mFixGridLayout.addView(view);
                        Helper.saveBuilder("ngg_sousuoshuju", data_history);
                    }
                    Helper.startActivity(getContext(), FrgSousuoPub.class, NoTitleAct.class, "type", type, "key", clk_mTextView_sousuo.getText().toString().trim());
                    return true;
                }
                return false;
            }
        });
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.mRadioButton1:
                        type = 1;
                        break;
                    case R.id.mRadioButton2:
                        type = 2;
                        break;
                    case R.id.mRadioButton3:
                        type = 3;
                        break;
                    case R.id.mRadioButton4:
                        type = 4;
                        break;
                }
            }
        });
    }

    public void loaddata() {
        data_history = Helper.readBuilder("ngg_sousuoshuju", new ArrayList<String>());
        if (data_history != null && data_history.size() > 0) {
            for (String str : data_history) {
                View view = Sousuo.getView(getContext(), null);
                ((Sousuo) view.getTag()).set(str);
                mFixGridLayout.addView(view);
            }
        }
    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mTextView_cancel == v.getId()) {
            this.finish();
        } else if (R.id.clk_mImageView_del == v.getId()) {
            Helper.deleteBuilder("ngg_sousuoshuju");
            mFixGridLayout.removeAllViews();
        }
    }

}