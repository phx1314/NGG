//
//  FrgBinghaiDetail
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.framewidget.view.DfCirleCurr;
import com.mdx.framework.Frame;
import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.ada.AdaBinghaiBanner;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MCropTech;

import java.util.Arrays;


public class FrgBinghaiDetail extends BaseFrg {

    public DfCirleCurr mDfCirleCurr;
    public MCropTech mMCropTech;
    public WebView mWebView;

    @Override
    protected void create(Bundle savedInstanceState) {
        mMCropTech = (MCropTech) getActivity().getIntent().getSerializableExtra("mMCropTech");
        setContentView(R.layout.frg_binghai_detail);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mDfCirleCurr = (DfCirleCurr) findViewById(R.id.mDfCirleCurr);
        mWebView = (WebView) findViewById(R.id.mWebView);

        ApisFactory.getApiMCropTech().load(getContext(), this, "MCropTech", mMCropTech.id);
    }

    public void MCropTech(Son s) {
        mMCropTech = (MCropTech) s.getBuild();
        if (!TextUtils.isEmpty(mMCropTech.imgs)) {
            mDfCirleCurr.setAdapter(new AdaBinghaiBanner(getContext(), Arrays.asList(mMCropTech.imgs.split(","))));
        } else {
            mDfCirleCurr.setVisibility(View.GONE);
        }
        Frame.HANDLES.sentAll("FrgTuwennongji", 3, null);
        if (mMCropTech.isCollect > 0) {
            mHeadlayout.setRight2Bacgroud(R.drawable.bt_shoucang_w_h);
        } else {
            mHeadlayout.setRight2Bacgroud(R.drawable.bt_shoucang_w_n);
        }
        mHeadlayout.setRight2Onclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMCropTech.isCollect > 0) {
                    ApisFactory.getApiMCropTechCollect().load(getContext(), FrgBinghaiDetail.this, "SCollectTopicCancel", mMCropTech.id, 0.0);
                } else {
                    ApisFactory.getApiMCropTechCollect().load(getContext(), FrgBinghaiDetail.this, "SCollectTopic", mMCropTech.id, 1.0);
                }

            }
        });
    }

    public void SCollectTopic(Son s) {
        mMCropTech.isCollect = 1;
        mHeadlayout.setRight2Bacgroud(R.drawable.bt_shoucang_w_h);
        Frame.HANDLES.sentAll("FrgWodeShoucang", 0, null);
    }

    public void SCollectTopicCancel(Son s) {
        mMCropTech.isCollect = 0;
        mHeadlayout.setRight2Bacgroud(R.drawable.bt_shoucang_w_n);
        Frame.HANDLES.sentAll("FrgWodeShoucang", 0, null);
    }

    public void loaddata() {
        mDfCirleCurr.setFillColor(Color.parseColor("#62B9EF"));
        mDfCirleCurr.setPageColor(Color.parseColor("#88000000"));


        mWebView.loadUrl(BaseConfig.getUri() + mMCropTech.info);
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

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("详情");
        mHeadlayout.setRightBacgroud(R.drawable.bt_fenxiang_w_n);
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.framewidget.F.getShare(getContext(), "", BaseConfig.getUri() + mMCropTech.info
                        , "", "");
            }
        });
    }
}