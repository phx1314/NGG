//
//  FrgJiesuan
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.item.JiesuanImg;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MCart;
import com.udows.common.proto.MCartToOrder;
import com.udows.common.proto.MCartToOrderList;
import com.udows.common.proto.MPayMixOrder;
import com.udows.common.proto.MShoppingCartList;
import com.udows.common.proto.MStoreCart;
import com.udows.common.proto.MUserAddress;
import com.udows.common.proto.MUserAddressList;
import com.unionpay.UPPayAssistEx;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import simcpux.AlixId;
import simcpux.BaseHelper;
import simcpux.Constants;
import simcpux.MD5;
import simcpux.Rsa;


public class FrgJiesuan extends BaseFrg implements Handler.Callback, Runnable {

    public LinearLayout clk_mLinearLayout_noaddress;
    public LinearLayout clk_mLinearLayout_hasaddress;
    public TextView mTextView_name;
    public TextView mTextView_phone;
    public TextView mTextView_address;
    public LinearLayout mLinearLayout_content;
    public TextView mTextView_shangpinprice;
    public TextView mTextView_yunfei;
    public TextView mTextView_zongji_first;
    public LinearLayout clk_mLinearLayout_weixin;
    public ImageView mImageView_wx;
    public LinearLayout clk_mLinearLayout_zfb;
    public ImageView mImageView_zfb;
    public LinearLayout clk_mLinearLayout_yl;
    public ImageView mImageView_yl;
    public TextView mTextView_zongji;
    public TextView clk_mTextView_jiesuan;
    public List<String> cartIds = new ArrayList<>();
    public MShoppingCartList mMShoppingCartList;
    public TextView mTextView_num;
    public LinearLayout mLinearLayout_yunfei;
    public int payType = 3;//1支付宝2银联3微信
    /*****************************************************************
     * mMode参数解释： "00" - 启动银联正式环境 "01" - 连接银联测试环境
     *****************************************************************/
    private final String mMode = "00";

    private ProgressDialog mLoadingDialog = null;
    private ProgressDialog mProgress = null;
    private static final int RQF_PAY = 1;
    // 微信支付
    private PayReq req;
    private IWXAPI msgApi;
    private Handler mHandler1 = new Handler();
    private String orderid;
    private String addressid;
    private String storeId;
    private MPayMixOrder mMPayMixOrder;

    @Override
    protected void create(Bundle savedInstanceState) {
        cartIds = (List<String>) getActivity().getIntent().getSerializableExtra("cartIds");
        storeId = getActivity().getIntent().getStringExtra("storeId");
        setContentView(R.layout.frg_jiesuan);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                ApisFactory.getApiMMyAddressList().load(getContext(), this, "MMyAddressList");
                break;
            case 1:
                MUserAddress mMUserAddress = (MUserAddress) obj;
                clk_mLinearLayout_hasaddress.setVisibility(View.VISIBLE);
                clk_mLinearLayout_noaddress.setVisibility(View.GONE);
                mTextView_name.setText(mMUserAddress.name);
                mTextView_phone.setText(mMUserAddress.phone);
                mTextView_address.setText(mMUserAddress.address);
                addressid = mMUserAddress.id;
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        msgApi = WXAPIFactory.createWXAPI(getContext(), null);
        req = new PayReq();
        clk_mLinearLayout_noaddress = (LinearLayout) findViewById(R.id.clk_mLinearLayout_noaddress);
        clk_mLinearLayout_hasaddress = (LinearLayout) findViewById(R.id.clk_mLinearLayout_hasaddress);
        mTextView_name = (TextView) findViewById(R.id.mTextView_name);
        mTextView_phone = (TextView) findViewById(R.id.mTextView_phone);
        mTextView_address = (TextView) findViewById(R.id.mTextView_address);
        mLinearLayout_content = (LinearLayout) findViewById(R.id.mLinearLayout_content);
        mTextView_shangpinprice = (TextView) findViewById(R.id.mTextView_shangpinprice);
        mTextView_yunfei = (TextView) findViewById(R.id.mTextView_yunfei);
        mTextView_zongji_first = (TextView) findViewById(R.id.mTextView_zongji_first);
        clk_mLinearLayout_weixin = (LinearLayout) findViewById(R.id.clk_mLinearLayout_weixin);
        mImageView_wx = (ImageView) findViewById(R.id.mImageView_wx);
        clk_mLinearLayout_zfb = (LinearLayout) findViewById(R.id.clk_mLinearLayout_zfb);
        mImageView_zfb = (ImageView) findViewById(R.id.mImageView_zfb);
        clk_mLinearLayout_yl = (LinearLayout) findViewById(R.id.clk_mLinearLayout_yl);
        mImageView_yl = (ImageView) findViewById(R.id.mImageView_yl);
        mTextView_zongji = (TextView) findViewById(R.id.mTextView_zongji);
        clk_mTextView_jiesuan = (TextView) findViewById(R.id.clk_mTextView_jiesuan);
        mTextView_num = (TextView) findViewById(R.id.mTextView_num);
        mLinearLayout_yunfei = (LinearLayout) findViewById(R.id.mLinearLayout_yunfei);

        clk_mLinearLayout_noaddress.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout_hasaddress.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout_weixin.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout_zfb.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout_yl.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mTextView_jiesuan.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
    }

    public void loaddata() {
        String ids = "";
        for (String str : cartIds) {
            ids += str + ",";
        }
        ApisFactory.getApiMCatchStoreCoupon().load(getContext(), this, "MCatchStoreCoupon", ids.substring(0, ids.length() - 1));
        ApisFactory.getApiMMyAddressList().load(getContext(), this, "MMyAddressList");
    }

    public void MMyAddressList(Son s) {
        MUserAddressList mMUserAddressList = (MUserAddressList) s.getBuild();
        for (MUserAddress mMUserAddress : mMUserAddressList.address) {
            if (mMUserAddress.isDefault == 1) {
                clk_mLinearLayout_hasaddress.setVisibility(View.VISIBLE);
                clk_mLinearLayout_noaddress.setVisibility(View.GONE);
                mTextView_name.setText(mMUserAddress.name);
                mTextView_phone.setText(mMUserAddress.phone);
                mTextView_address.setText(mMUserAddress.address);
                addressid = mMUserAddress.id;
                return;
            }
        }
        clk_mLinearLayout_hasaddress.setVisibility(View.GONE);
        clk_mLinearLayout_noaddress.setVisibility(View.VISIBLE);
    }

    public void MCatchStoreCoupon(Son s) {
        mMShoppingCartList = (MShoppingCartList) s.getBuild();
        for (MCart mMCart : mMShoppingCartList.cart.get(0).goods) {
            View view = JiesuanImg.getView(getContext(), null);
            ((JiesuanImg) view.getTag()).set(mMCart.img);
            mLinearLayout_content.addView(view);
        }
        mTextView_num.setText("共" + mMShoppingCartList.cart.get(0).goodsNum + "件");
        mTextView_shangpinprice.setText("￥" + mMShoppingCartList.cart.get(0).total);
        if (mMShoppingCartList.cart.get(0).express.size() > 0) {
            mLinearLayout_yunfei.setVisibility(View.VISIBLE);
            mTextView_yunfei.setText("￥" + mMShoppingCartList.cart.get(0).express.get(0).price);
        } else {
            mLinearLayout_yunfei.setVisibility(View.GONE);
        }
        mTextView_zongji_first.setText(Html.fromHtml("总计:   <font color='#FF933B'>￥" + mMShoppingCartList.total + "</font>"));
        mTextView_zongji.setText(Html.fromHtml("总计:<font color='#FF933B'>￥" + mMShoppingCartList.total + "</font>"));
    }


    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mLinearLayout_noaddress == v.getId()) {
            if (mMPayMixOrder == null)
                Helper.startActivity(getContext(), FrgDizhiGuanli.class, TitleAct.class, "from", "FrgJiesuan");
        } else if (R.id.clk_mLinearLayout_hasaddress == v.getId()) {
            if (mMPayMixOrder == null)
                Helper.startActivity(getContext(), FrgDizhiGuanli.class, TitleAct.class, "from", "FrgJiesuan");
        } else if (R.id.clk_mLinearLayout_weixin == v.getId()) {
            payType = 3;
            mImageView_wx.setVisibility(View.VISIBLE);
            mImageView_zfb.setVisibility(View.INVISIBLE);
            mImageView_yl.setVisibility(View.INVISIBLE);
        } else if (R.id.clk_mLinearLayout_zfb == v.getId()) {
            payType = 1;
            mImageView_wx.setVisibility(View.INVISIBLE);
            mImageView_zfb.setVisibility(View.VISIBLE);
            mImageView_yl.setVisibility(View.INVISIBLE);
        } else if (R.id.clk_mLinearLayout_yl == v.getId()) {
            payType = 2;
            mImageView_wx.setVisibility(View.INVISIBLE);
            mImageView_zfb.setVisibility(View.INVISIBLE);
            mImageView_yl.setVisibility(View.VISIBLE);
        } else if (R.id.clk_mTextView_jiesuan == v.getId()) {
            if (mMPayMixOrder == null) {
                if (TextUtils.isEmpty(addressid)) {
                    Helper.toast("请选择收货地址", getContext());
                    return;
                }
                MCartToOrderList mMCartToOrderList = new MCartToOrderList();
                mMCartToOrderList.payType = payType;
                mMCartToOrderList.addressId = addressid;
                MCartToOrder mCartToOrder = new MCartToOrder();
                mMCartToOrderList.store.add(mCartToOrder);
                for (String str : cartIds) {
                    MStoreCart mMStoreCart = new MStoreCart();
                    mMStoreCart.id = str;
                    mCartToOrder.cart.add(mMStoreCart);
                }
                mCartToOrder.storeId = storeId;
                mCartToOrder.payType = payType;
                ApisFactory.getApiMAddOrder().load(getContext(), this, "MAddOrder", mMCartToOrderList);
            } else {
                goBa(mMPayMixOrder);
            }
        }
    }

    public void MAddOrder(Son s) {
        Frame.HANDLES.sentAll("FrgCart", 1, null);
        mMPayMixOrder = (MPayMixOrder) s.getBuild();
        goBa(mMPayMixOrder);
    }

    public void MToPay(Son s) {

    }

    public void goBa(MPayMixOrder mMPayMixOrder) {
//        ApisFactory.getApiMToPay().load(getContext(), this, "MToPay", mMPayMixOrder.ids, 0.0, null, (double) payType);
//        MPayMixOrder mMPayMixOrder = (MPayMixOrder) s.getBuild();
        orderid = mMPayMixOrder.ids;
        switch (payType) {
            case 1:
                pay();
                break;
            case 2:
                mLoadingDialog = ProgressDialog.show(getContext(), // context
                        "", // title
                        "正在努力的获取中,请稍候...", // message
                        true); // 进度是否是不确定的，这只和创建进度条有关
                /*************************************************
                 * 步骤1：从网络开始,获取交易流水号即TN
                 ************************************************/
                new Thread(this).start();
                break;
            case 3:
                // 微信支付
                genPayReq();
                break;
        }
    }

    /**
     * get the selected order info for pay. 获取商品订单信息
     * <p>
     * 商品在列表中的位置
     *
     * @return
     */
    String getNewOrderInfo() {
        String appname = null;
        appname = Frame.CONTEXT.getPackageName();
        StringBuilder sb = new StringBuilder();
        sb.append("partner=\"");
        sb.append(F.partnerId);
        sb.append("\"&out_trade_no=\"");
        sb.append(orderid); // 订单号 原数据getOutTradeNo()
        sb.append("\"&subject=\"");
        sb.append("来" + getResources().getString(R.string.app_name) + "的商品");// sProducts[position].subject
        sb.append("\"&body=\"");
        sb.append("看不见的body");// sProducts[position].body
        sb.append("\"&total_fee=\"");
        // 价格
        sb.append(Double.valueOf(mMShoppingCartList.total));// sProducts[position].price.replace("一口价:",
        // "")
        sb.append("\"&notify_url=\"");

        // 网址需要做URL编码
        sb.append(URLEncoder.encode("http://120.27.232.223/ngg/mobilePayNotify"));// "http://diedie.webok.net/alipaynotify"
        sb.append("\"&service=\"mobile.securitypay.pay");
        sb.append("\"&_input_charset=\"UTF-8");
        sb.append("\"&return_url=\"");
        sb.append(URLEncoder.encode("http://m.alipay.com"));
        sb.append("\"&payment_type=\"1");
        sb.append("\"&seller_id=\"");
        sb.append(F.sellerId);

        // 如果show_url值为空，可不传
        // sb.append("\"&show_url=\"");
        sb.append("\"&it_b_pay=\"1m");
        sb.append("\"");

        return new String(sb);
    }

    private void pay() {

        // 根据订单信息开始进行支付
        try {
            Log.i("ExternalPartner", "onItemClick");
            String info = getNewOrderInfo();
            String sign = Rsa.sign(info, F.rsaPrivate);
            sign = URLEncoder.encode(sign, "utf8");
            info += "&sign=\"" + sign + "\"&" + getSignType();
            Log.i("ExternalPartner", "start pay");
            // start the pay.
            Log.i("info", "info = " + info);

            final String orderInfo = info;
            new Thread() {
                public void run() {
                    PayTask alipay = new PayTask(getActivity());
                    // 设置为沙箱模式，不设置默认为线上环境
                    // alipay.setSandBox(true);
                    String result = alipay.pay(orderInfo);
                    Log.i("info", "result = " + result);
                    Message msg = new Message();
                    msg.what = RQF_PAY;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                }
            }.start();

        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(getContext(), R.string.remote_call_failed,
                    Toast.LENGTH_SHORT).show();
        }
    }

    // the handler use to receive the pay result.
    // 这里接收支付结果，支付宝手机端同步通知
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

            try {
                String strRet = (String) msg.obj;

                Log.e("info", strRet); // strRet范例：resultStatus={9000};memo={};result={partner="2088201564809153"&seller="2088201564809153"&out_trade_no="050917083121576"&subject="123456"&body="2010新款NIKE 耐克902第三代板鞋 耐克男女鞋 386201 白红"&total_fee="0.01"&notify_url="http://notify.java.jpxx.org/index.jsp"&success="true"&sign_type="RSA"&sign="d9pdkfy75G997NiPS1yZoYNCmtRbdOP0usZIMmKCCMVqbSG1P44ohvqMYRztrB6ErgEecIiPj9UldV5nSy9CrBVjV54rBGoT6VSUF/ufjJeCSuL510JwaRpHtRPeURS1LXnSrbwtdkDOktXubQKnIMg2W0PreT1mRXDSaeEECzc="}
                switch (msg.what) {
                    case AlixId.RQF_PAY: {
                        closeProgress();
                        BaseHelper.log("info", strRet);
                        // 处理交易结果
                        try {
                            // 获取交易状态码，具体状态代码请参看文档
                            String tradeStatus = "resultStatus={";
                            int imemoStart = strRet.indexOf("resultStatus=");
                            imemoStart += tradeStatus.length();
                            int imemoEnd = strRet.indexOf("};memo=");
                            tradeStatus = strRet.substring(imemoStart, imemoEnd);
                            if (tradeStatus.equals("9000")) {// 判断交易状态码，只有9000表示交易成功
                                endSucess();
                            } else
                                Toast.makeText(getContext(), "支付失败",
                                        Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            BaseHelper.showDialog(getActivity(), "提示", strRet,
                                    R.drawable.infoicon);
                        }
                    }
                    break;
                }

                super.handleMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * the OnCancelListener for lephone platform. lephone系统使用到的取消dialog监听
     */
    public static class AlixOnCancelListener implements
            DialogInterface.OnCancelListener {
        Activity mcontext;

        public AlixOnCancelListener(Activity context) {
            mcontext = context;
        }

        public void onCancel(DialogInterface dialog) {
            mcontext.onKeyDown(KeyEvent.KEYCODE_BACK, null);
        }
    }

    //
    // close the progress bar
    // 关闭进度框
    void closeProgress() {
        try {
            if (mProgress != null) {
                mProgress.dismiss();
                mProgress = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get the sign type we use. 获取签名方式
     *
     * @return
     */

    private String getSignType() {

        return "sign_type=\"RSA\"";
    }

    // 银联支付
    @Override
    public boolean handleMessage(Message msg) {
        if (mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
        String tn = "";
        if (msg.obj == null || ((String) msg.obj).length() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("错误提示");
            builder.setMessage("网络连接失败,请重试!");
            builder.setNegativeButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.create().show();
        } else {
            tn = (String) msg.obj;
            /*************************************************
             * 步骤2：通过银联工具类启动支付插件
             ************************************************/
            UPPayAssistEx.startPayByJAR(getActivity(), FrgJiesuan.class,
                    null, null, tn, mMode);
        }

        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*************************************************
         * 步骤3：处理银联手机支付控件返回的支付结果
         ************************************************/
        if (data == null) {
            return;
        }

        String msg = "";
        /*
         * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
		 */
        final String str = data.getExtras().getString("pay_result");
        if (str.equalsIgnoreCase("success")) {
            msg = "支付成功！";
        } else if (str.equalsIgnoreCase("fail")) {
            msg = "支付失败！";
        } else if (str.equalsIgnoreCase("cancel")) {
            msg = "用户取消了支付";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("支付结果通知");
        builder.setMessage(msg);
        builder.setInverseBackgroundForced(true);
        // builder.setCustomTitle();
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (str.equalsIgnoreCase("success")) {
                    endSucess();
                }
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void run() {
        String tn = orderid;
        Message msg = mHandler1.obtainMessage();
        msg.obj = tn;
        mHandler1.sendMessage(msg);
    }


    /**
     * 微信支付调用 生成签名
     *
     * @author Avery
     */
    private void genPayReq() {
        req.appId = Constants.APP_ID;
        req.partnerId = Constants.MCH_ID;
        req.prepayId = orderid;
        req.packageValue = "Sign=WXPay";
        req.nonceStr = genNonceStr();
        req.timeStamp = String.valueOf(genTimeStamp());

        List<NameValuePair> signParams = new LinkedList<NameValuePair>();
        signParams.add(new BasicNameValuePair("appid", req.appId));
        signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
        signParams.add(new BasicNameValuePair("package", req.packageValue));
        signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
        signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
        signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));

        req.sign = genAppSign(signParams);
        Log.e("signParams", signParams.toString());
        msgApi.registerApp(Constants.APP_ID);
        msgApi.sendReq(req);
    }

    private String genNonceStr() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000))
                .getBytes());
    }

    private long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    private String genAppSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Constants.API_KEY);

        String appSign = MD5.getMessageDigest(sb.toString().getBytes())
                .toUpperCase();
        Log.e("orion", appSign);
        return appSign;
    }

    public void endSucess() {
        Helper.toast("支付成功", getContext());
        this.finish();
        Frame.HANDLES.sentAll("FrgCart", 1, null);
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("结算");
    }
}