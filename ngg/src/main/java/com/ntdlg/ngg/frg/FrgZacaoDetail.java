//
//  FrgZacaoDetail
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.ada.AdaZaCaoDetail;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MZaCaoInfo;

import java.util.Arrays;


public class FrgZacaoDetail extends BaseFrg {

    public MImageView mMImageView_bg;
    public ImageView clk_mImageView_back;
    public ImageView clk_mImageView_share;
    public TextView mTextView_title;
    public WebView mWebView;
    public String id;
    public com.framewidget.view.DfCirleCurr mDfCirleCurr;

    @Override
    protected void create(Bundle savedInstanceState) {
        id = getActivity().getIntent().getStringExtra("id");
        setContentView(R.layout.frg_zacao_detail);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMImageView_bg = (MImageView) findViewById(R.id.mMImageView_bg);
        clk_mImageView_back = (ImageView) findViewById(R.id.clk_mImageView_back);
        clk_mImageView_share = (ImageView) findViewById(R.id.clk_mImageView_share);
        mTextView_title = (TextView) findViewById(R.id.mTextView_title);
        mWebView = (WebView) findViewById(R.id.mWebView);
        mDfCirleCurr = (com.framewidget.view.DfCirleCurr) findViewById(R.id.mDfCirleCurr);

        clk_mImageView_back.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mImageView_share.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void loaddata() {
        ApisFactory.getApiMZaCaoInfo().load(getContext(), this, "MZaCaoInfo", id);
    }

    public void MZaCaoInfo(Son s) {
        MZaCaoInfo mMZaCaoInfo = (MZaCaoInfo) s.getBuild();
        mMImageView_bg.setObj(mMZaCaoInfo.img);
        mTextView_title.setText(mMZaCaoInfo.name);
        mDfCirleCurr.setAdapter(new AdaZaCaoDetail(getContext(), Arrays.asList(mMZaCaoInfo.img.split(","))));
        mWebView.loadUrl(BaseConfig.getUri() + mMZaCaoInfo.info);
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
    }

    @Override
    public void onClick(android.view.View v) {

        if (R.id.clk_mImageView_back == v.getId()) {
            this.finish();
        } else if (R.id.clk_mImageView_share == v.getId()) {

        }
    }

}