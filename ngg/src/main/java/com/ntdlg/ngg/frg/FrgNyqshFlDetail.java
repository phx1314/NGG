//
//  FrgNyqshFlDetail
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;


public class FrgNyqshFlDetail extends BaseFrg {

    public WebView mWebView;
    public String url;

    @Override
    protected void create(Bundle savedInstanceState) {
        url = getActivity().getIntent().getStringExtra("url");
        setContentView(R.layout.frg_nyqsh_fl_detail);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mWebView = (WebView) findViewById(R.id.mWebView);

        mWebView.loadUrl(BaseConfig.getUri() + url);
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
    }

    public void loaddata() {

    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("详情");
    }
}