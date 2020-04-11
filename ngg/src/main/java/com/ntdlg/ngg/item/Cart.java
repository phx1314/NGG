//
//  Cart
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.ada.AdaCart;
import com.ntdlg.ngg.frg.FrgGoodDetail;
import com.ntdlg.ngg.view.ModelCart;
import com.udows.common.proto.ApisFactory;


public class Cart extends BaseItem implements CompoundButton.OnCheckedChangeListener {
    public CheckBox mCheckBox;
    public MImageView mMImageView;
    public TextView mTextView_title;
    public TextView mTextView_remark;
    public TextView mTextView_price;
    public TextView mTextView_num;
    public ModelCart item;
    public AdaCart mAdaCart;
    public ImageView mImageView_jian;
    public ImageView mImageView_jia;
    public Handler mHandler = new Handler();
    public Runnable mRunnable;
    public LinearLayout mLinearLayout_content;
    public LinearLayout mLinearLayout_add;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_cart, null);
        convertView.setTag(new Cart(convertView));
        return convertView;
    }

    public Cart(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mCheckBox = (CheckBox) contentview.findViewById(R.id.mCheckBox);
        mMImageView = (MImageView) contentview.findViewById(R.id.mMImageView);
        mTextView_title = (TextView) contentview.findViewById(R.id.mTextView_title);
        mTextView_remark = (TextView) contentview.findViewById(R.id.mTextView_remark);
        mTextView_price = (TextView) contentview.findViewById(R.id.mTextView_price);
        mTextView_num = (TextView) contentview.findViewById(R.id.mTextView_num);
        mImageView_jian = (ImageView) findViewById(R.id.mImageView_jian);
        mImageView_jia = (ImageView) findViewById(R.id.mImageView_jia);
        mLinearLayout_content = (LinearLayout) findViewById(R.id.mLinearLayout_content);
        mLinearLayout_add = (LinearLayout) findViewById(R.id.mLinearLayout_add);
        mCheckBox.setOnCheckedChangeListener(this);

        mImageView_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.getmMShoppingCart().num > 1) {
                    item.getmMShoppingCart().num--;
                    mAdaCart.notifyDataSetChanged();
                    mHandler.removeCallbacks(mRunnable);
                    mHandler.postDelayed(mRunnable, 400);
                }
            }
        });
        mImageView_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.getmMShoppingCart().num < item.getmMShoppingCart().total) {
                    item.getmMShoppingCart().num++;
                    mAdaCart.notifyDataSetChanged();
                    mHandler.removeCallbacks(mRunnable);
                    mHandler.postDelayed(mRunnable, 400);
                } else {
                    Helper.toast("库存不够", context);
                }
            }
        });
        mLinearLayout_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(context, Intent.FLAG_ACTIVITY_CLEAR_TOP, FrgGoodDetail.class, NoTitleAct.class, "id", item.getmMShoppingCart().goodsId);
            }
        });
        mMImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(context, Intent.FLAG_ACTIVITY_CLEAR_TOP, FrgGoodDetail.class, NoTitleAct.class, "id", item.getmMShoppingCart().goodsId);
            }
        });
        mRunnable = new Runnable() {
            @Override
            public void run() {
                ApisFactory.getApiMEditShopCart().load(context, Cart.this, "MEditShopCart", item.getmMShoppingCart().id, (double) item.getmMShoppingCart().num);
            }
        };
    }

    public void MEditShopCart(Son s) {
        Frame.HANDLES.sentAll("FrgGoodDetail", 0, null);
        if (item.isChoose) {
            Frame.HANDLES.sentAll("FrgCart", 0, null);
        }
    }

    public void set(ModelCart item, AdaCart mAdaCart) {
        this.item = item;
        this.mAdaCart = mAdaCart;
        if (item.isChoose) {
            mCheckBox.setOnCheckedChangeListener(null);
            mCheckBox.setChecked(true);
            mCheckBox.setOnCheckedChangeListener(this);
        } else {
            mCheckBox.setOnCheckedChangeListener(null);
            mCheckBox.setChecked(false);
            mCheckBox.setOnCheckedChangeListener(this);
        }
        if (!TextUtils.isEmpty(item.getmMShoppingCart().img)) {
            mMImageView.setObj(item.getmMShoppingCart().img.split(",")[0]);
        } else {
            mMImageView.setObj("");
        }
        mTextView_title.setText(item.getmMShoppingCart().title);
        mTextView_remark.setText(item.getmMShoppingCart().info);
        mTextView_price.setText("单价￥" + (TextUtils.isEmpty(item.getmMShoppingCart().nowPrice) ? "" : item.getmMShoppingCart().nowPrice));
        mTextView_num.setText(item.getmMShoppingCart().num + "");
        if (!TextUtils.isEmpty(item.getmMShoppingCart().goodsId)) {
            mCheckBox.setVisibility(View.VISIBLE);
            mLinearLayout_add.setVisibility(View.VISIBLE);
        } else {
            mCheckBox.setVisibility(View.INVISIBLE);
            mLinearLayout_add.setVisibility(View.GONE);
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        item.isChoose = b;
        Frame.HANDLES.sentAll("FrgCart", 0, null);
        mAdaCart.notifyDataSetChanged();
    }
}