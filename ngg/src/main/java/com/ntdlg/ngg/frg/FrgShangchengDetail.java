//
//  FrgShangchengDetail
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.ada.AdaBinghaiBanner;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MCreditGoods;
import com.udows.common.proto.MUserAddress;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FrgShangchengDetail extends BaseFrg {

    public TextView mTextView_num;
    public TextView mTextView_price;
    public TextView mTextView_gmai;
    public MCreditGoods mMCreditGoods;
    public String addressId;
    public com.mdx.framework.widget.banner.CirleCurr mMImageView;
    public WebView mWebView;

    @Override
    protected void create(Bundle savedInstanceState) {
        mMCreditGoods = (MCreditGoods) getActivity().getIntent().getSerializableExtra("mMCreditGoods");
        setContentView(R.layout.frg_shangcheng_detail);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 1:
                MUserAddress mMUserAddress = (MUserAddress) obj;
                addressId = mMUserAddress.id;
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_num = (TextView) findViewById(R.id.mTextView_num);
        mTextView_price = (TextView) findViewById(R.id.mTextView_price);
        mTextView_gmai = (TextView) findViewById(R.id.mTextView_gmai);
        mMImageView = (com.mdx.framework.widget.banner.CirleCurr) findViewById(R.id.mMImageView);
        mWebView = (WebView) findViewById(R.id.mWebView);
        mTextView_gmai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(addressId)) {
                    Helper.toast("请选择收货地址", getContext());
                    Helper.startActivity(getContext(), FrgDizhiGuanli.class, TitleAct.class, "from", "FrgShangchengDetail");
                } else {
                    ApisFactory.getApiMCreditGoodsExchange().load(getContext(), FrgShangchengDetail.this, "MCreditGoodsExchange", mMCreditGoods.id, addressId);
                }
            }
        });

    }

    public void MCreditGoodsExchange(Son s) {
        Helper.toast("兑换成功", getContext());
        this.finish();
        Frame.HANDLES.sentAll("FrgWode,FrgWodeGgd,FrgGgdShangcheng", 0, null);
    }


    public void loaddata() {
        mTextView_num.setText(mMCreditGoods.credit + "");
        mTextView_price.setText("￥" + mMCreditGoods.price);
        mTextView_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        List data = new ArrayList();

        if (!TextUtils.isEmpty(mMCreditGoods.img)) {
            data.add(mMCreditGoods.img);
        }
        if (!TextUtils.isEmpty(mMCreditGoods.imgs)) {
            data.addAll(Arrays.asList(mMCreditGoods.imgs.split(","))) ;
        }
        mWebView.loadUrl(BaseConfig.getUri() + mMCreditGoods.info);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });

        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK
                            && mWebView.canGoBack()) { // 表示按返回键
                        mWebView.goBack(); // 后退
                        return true; // 已处理
                    }
                }
                return false;
            }
        });
        mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
        mMImageView.setAdapter(new AdaBinghaiBanner(getContext(), data));
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle(mMCreditGoods.name);
    }
}