package study.com.s_sxl.carelib.viewUtils;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import study.com.s_sxl.carelib.R;
import study.com.s_sxl.carelib.utils.IMEUtil;

/**
 * Copyright  @2016 All rights reserved.
 * <p>Description: </p>
 * @ClassName NavBar
 * @Package study.com.s_sxl.carelib.viewUtils
 * @Author S_sxl
 * @Time 2016/7/21
 */
public class NavBar extends FrameLayout {

    private ImageView mIvDown;
    private ImageView mIvBack;
    private TextView mTvAddress;
    private LinearLayout mllSearch;
    private TextView mTvTitle;
    private ImageView mIvCode;
    private ImageView mIvBg;
    private ImageView mIvSecondCode;
    private LinearLayout mllSearchTrue;

    private OnClickListener mBackListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            ((Activity)getContext()).finish();
        }
    };
    private EditText mEtSearch;

    public NavBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.view_nav, this);

        mIvDown = (ImageView) contentView.findViewById(R.id.iv_down);
        mIvBack = (ImageView) contentView.findViewById(R.id.iv_back);
        mTvAddress = (TextView) contentView.findViewById(R.id.tv_address);
        mllSearch = (LinearLayout) contentView.findViewById(R.id.ll_search);
        mTvTitle = (TextView) contentView.findViewById(R.id.tv_title);
        mIvCode = (ImageView) contentView.findViewById(R.id.iv_code);
        mIvSecondCode = (ImageView) contentView.findViewById(R.id.iv_second_code);
        mIvBg = (ImageView) contentView.findViewById(R.id.iv_bg);
        mllSearchTrue = (LinearLayout) contentView.findViewById(R.id.ll_search_true);
        mEtSearch = (EditText) contentView.findViewById(R.id.et_search);

        addListener();
    }

    private void addListener() {
        mIvBack.setOnClickListener(mBackListener);
    }

    /**
     * 设置返回监听已经返回按钮图片, 默认的返回监听是结束Activity
     **/
    public void setOnBackClickedListener(int resId,OnClickListener listener) {
        mIvBack.setImageResource(resId);
        mIvBack.setVisibility(VISIBLE);
        mIvBack.setOnClickListener(listener);
    }

    /**
     * 隐藏返回按钮
     **/
    public void hideBack() {
        mIvBack.setVisibility(GONE);
    }

    /**
     * 设置标题
     **/
    public void setTitle(String title) {
        mTvTitle.setVisibility(VISIBLE);
        mTvTitle.setText(title);
    }

    /**
     * 显示地址与下拉按钮
     * @param address
     */
    public void setAddress(String address,OnClickListener listener){
        mIvBack.setVisibility(GONE);
        mTvAddress.setVisibility(VISIBLE);
        mIvDown.setVisibility(VISIBLE);
        mIvDown.setOnClickListener(listener);
        mTvAddress.setText(address);
    }

    /**
     * 显示假搜索框, 用于主页
     **/
    public void showFakeSearch(OnClickListener listener) {
        mTvTitle.setVisibility(GONE);
        mllSearch.setVisibility(VISIBLE);
        mllSearch.setOnClickListener(listener);
    }

    /**
     * 显示右侧图片按钮
     **/
    public void showRightIcon(int resId, OnClickListener listener) {
        mIvSecondCode.setVisibility(VISIBLE);
        mIvSecondCode.setImageResource(resId);
        mIvSecondCode.setOnClickListener(listener);
    }

    /**
     * 显示右侧图片按钮
     **/
    public void showRightIcon(OnClickListener listener) {
        mIvSecondCode.setVisibility(VISIBLE);
        mIvSecondCode.setOnClickListener(listener);
    }

    /**
     * 显示右侧图片按钮(两个)
     **/
    public void showRightText(int resId, int secondResId, OnClickListener listener, OnClickListener secondListener) {
        mIvCode.setVisibility(VISIBLE);
        mIvCode.setImageResource(resId);
        mIvCode.setOnClickListener(listener);
        mIvSecondCode.setVisibility(VISIBLE);
        mIvSecondCode.setImageResource(secondResId);
        mIvSecondCode.setOnClickListener(secondListener);
    }

    /**
     * 显示搜索
     */
    public void search(){
        mllSearchTrue.setVisibility(VISIBLE);

    }

    /**
     * 获取用户输入的搜索关键字
     */
    public String getSearch(){
        return mEtSearch.getText().toString().trim();
    }

    /**
     * 关闭软键盘
     **/
    public void closeIME() {
        IMEUtil.closeIME(mEtSearch, getContext());
    }

    /**
     * 设置标题栏背景颜色
     */
    public void setBgColor(int res){
        mIvBg.setImageResource(res);
    }

    /**
     * 将标题栏设置为透明
     **/
    public void setNavTransparent() {
        setBgColor(R.color.transparent);
    }

}
