//
//  FrgXinwenzixunDetail
//
//  Created by Administrator on 2017-02-04 18:35:42
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
import android.widget.TextView;

import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;
import com.udows.common.proto.MNews;


public class FrgXinwenzixunDetail extends BaseFrg {

    public TextView mTextView_title;
    public TextView mTextView_src;
    public TextView mTextView_time;
    public WebView mWebView;
    public MNews item;

    @Override
    protected void create(Bundle savedInstanceState) {
        item = (MNews) getActivity().getIntent().getSerializableExtra("item");
        setContentView(R.layout.frg_xinwenzixun_detail);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_title = (TextView) findViewById(R.id.mTextView_title);
        mTextView_src = (TextView) findViewById(R.id.mTextView_src);
        mTextView_time = (TextView) findViewById(R.id.mTextView_time);
        mWebView = (WebView) findViewById(R.id.mwb);


    }

    public void loaddata() {
        mTextView_title.setText(item.title);
//        mTextView_src.setText(item.);
        mTextView_time.setText(item.createTime);
        mWebView.loadUrl(BaseConfig.getUri() + item.url);
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
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("新闻资讯");
        mHeadlayout.setRightBacgroud(R.drawable.bt_fenxiang_w_n);
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                F.getShare();
            }
        });
    }
}