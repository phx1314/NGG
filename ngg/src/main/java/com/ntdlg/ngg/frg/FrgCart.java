//
//  FrgCart
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framewidget.F;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.ada.AdaCart;
import com.ntdlg.ngg.dataformat.DfCart;
import com.ntdlg.ngg.view.ModelCart;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MShoppingCart;

import java.util.ArrayList;
import java.util.List;


public class FrgCart extends BaseFrg implements CompoundButton.OnCheckedChangeListener {

    public MPageListView mMPageListView;
    public CheckBox mCheckBox;
    public TextView mTextView_zongji;
    public TextView mTextView_num;
    public TextView clk_mTextView_jiesuan;
    public LinearLayout mLinearLayout_shuju;
    public List<String> cartIds = new ArrayList<>();
    public MShoppingCart mMShoppingCart;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_cart);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                List<Boolean> datas = new ArrayList<>();
                int num_all = 0;
                double price_all = 0;
                cartIds.clear();
                for (ModelCart mModelCart : ((AdaCart) mMPageListView.getListAdapter()).getList()) {
                    if (!TextUtils.isEmpty(mModelCart.getmMShoppingCart().goodsId)) {
                        datas.add(mModelCart.isChoose);
                        if (mModelCart.isChoose) {
                            num_all += mModelCart.getmMShoppingCart().num;
                            price_all += mModelCart.getmMShoppingCart().num * Double.valueOf(mModelCart.getmMShoppingCart().nowPrice);
                            cartIds.add(mModelCart.getmMShoppingCart().id);
                        }
                    }
                }
                if (datas.contains(true) && !datas.contains(false)) {
                    mCheckBox.setOnCheckedChangeListener(null);
                    mCheckBox.setChecked(true);
                    mCheckBox.setOnCheckedChangeListener(this);
                    mLinearLayout_shuju.setVisibility(View.VISIBLE);
                } else if (!datas.contains(true) && datas.contains(false)) {
                    mCheckBox.setOnCheckedChangeListener(null);
                    mCheckBox.setChecked(false);
                    mCheckBox.setOnCheckedChangeListener(this);
                    mLinearLayout_shuju.setVisibility(View.INVISIBLE);
                } else {
                    mLinearLayout_shuju.setVisibility(View.VISIBLE);
                }
                mTextView_zongji.setText(Html.fromHtml("总计:<font color='#FF933B'>￥" + F.go2Wei(price_all) + "</font>"));
                mTextView_num.setText("共" + num_all + "件商品");
                break;
            case 1:
                mMPageListView.pullLoad();
                Frame.HANDLES.sentAll("FrgGoodDetail", 0, null);
                break;
            case 2:
                mCheckBox.setOnCheckedChangeListener(null);
                mCheckBox.setChecked(false);
                mCheckBox.setOnCheckedChangeListener(this);
                mLinearLayout_shuju.setVisibility(View.INVISIBLE);
                mMShoppingCart = (MShoppingCart) obj;
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);
        mCheckBox = (CheckBox) findViewById(R.id.mCheckBox);
        mTextView_zongji = (TextView) findViewById(R.id.mTextView_zongji);
        mTextView_num = (TextView) findViewById(R.id.mTextView_num);
        clk_mTextView_jiesuan = (TextView) findViewById(R.id.clk_mTextView_jiesuan);
        mLinearLayout_shuju = (LinearLayout) findViewById(R.id.mLinearLayout_shuju);
        clk_mTextView_jiesuan.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mCheckBox.setOnCheckedChangeListener(this);
    }

    public void loaddata() {
        mMPageListView.setDataFormat(new DfCart());
        mMPageListView.setApiUpdate(ApisFactory.getApiMShopCartList().set().setPageSize(Integer.MAX_VALUE));
        mMPageListView.pullLoad();
    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mTextView_jiesuan == v.getId()) {
            if (cartIds.size() <= 0) {
                Helper.toast("请选择", getContext());
                return;
            }
            Helper.startActivity(getContext(), FrgJiesuan.class, TitleAct.class, "cartIds", cartIds, "storeId", mMShoppingCart.storeId);
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("购物车");
        mHeadlayout.setRText("清空");
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cartIds_all = "";
                for (ModelCart mModelCart : ((AdaCart) mMPageListView.getListAdapter()).getList()) {
                    cartIds_all += mModelCart.mMShoppingCart.id + ",";
                }
                if (!TextUtils.isEmpty(cartIds_all))
                    ApisFactory.getApiMDelShopCart().load(getContext(), FrgCart.this, "MDelShopCart", cartIds_all);
            }
        });
    }

    public void MDelShopCart(Son s) {
        mMPageListView.pullLoad();
        mCheckBox.setOnCheckedChangeListener(null);
        mCheckBox.setChecked(false);
        mCheckBox.setOnCheckedChangeListener(this);
        Frame.HANDLES.sentAll("FrgGoodDetail", 0, null);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int num_all = 0;
        double price_all = 0;
        cartIds.clear();
        for (ModelCart mModelCart : ((AdaCart) mMPageListView.getListAdapter()).getList()) {
            if (!TextUtils.isEmpty(mModelCart.getmMShoppingCart().goodsId)) {
                mModelCart.isChoose = b;
                if (b) {
                    num_all += mModelCart.getmMShoppingCart().num;
                    price_all += mModelCart.getmMShoppingCart().num * Double.valueOf(mModelCart.getmMShoppingCart().nowPrice);
                    cartIds.add(mModelCart.getmMShoppingCart().id);
                }
            }
        }
        mTextView_zongji.setText(Html.fromHtml("总计:<font color='#FF933B'>￥" + F.go2Wei(price_all) + "</font>"));
        mTextView_num.setText("共" + num_all + "件商品");
        if (b) {
            mLinearLayout_shuju.setVisibility(View.VISIBLE);
        } else {
            mLinearLayout_shuju.setVisibility(View.INVISIBLE);
        }
        ((AdaCart) mMPageListView.getListAdapter()).notifyDataSetChanged();
    }
}