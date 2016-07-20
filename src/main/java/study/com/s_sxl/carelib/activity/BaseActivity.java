package study.com.s_sxl.carelib.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;
import study.com.s_sxl.carelib.AppManager;
import study.com.s_sxl.carelib.R;
import study.com.s_sxl.carelib.utils.StatusBarUtil;
import study.com.s_sxl.carelib.utils.ToastMgr;
import study.com.s_sxl.carelib.viewUtils.LoadingDialog;

/**
 * Copyright  @2016  All rights reserved.
 * <p>Description: </p>
 * @ClassName BaseActivity
 * @Package study.com.s_sxl.carelib.activity
 * @Author S_sxl
 * @Time 2016/6/29
 */
public abstract class BaseActivity extends FragmentActivity {

    // 状态栏与UI颜色是否一体化
    protected boolean mIsUseSystemBar = true;

    private int mStatusBarColorResource = 0;

    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        AppManager.getAppManager().addActivity(this);
        ButterKnife.bind(this);
        mLoadingDialog = new LoadingDialog(this);
        init(savedInstanceState);
    }

    protected abstract void init(Bundle savedInstanceState);

    public abstract int getLayoutId();

    /**
     * 获取dialog
     * @return
     */
    public LoadingDialog getLoadingDialog(){
        return mLoadingDialog;
    }

    /**
     * 显示dialog
     */
    public void showDialog(){
        mLoadingDialog.show();
    }

    /**
     * 影藏dialog
     */
    public void hideDialog(){
        mLoadingDialog.dismiss();
    }

    public void showToast(String s) {
        ToastMgr.show(s);
    }

    public void showToast(int r) {
        ToastMgr.show(r);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
        ButterKnife.unbind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mIsUseSystemBar){
            if(mStatusBarColorResource == 0){
                StatusBarUtil.initSystemBar(this,R.color.statusBar);
            }else {
                StatusBarUtil.initSystemBar(this,mStatusBarColorResource);
            }
        }
    }

    /**
     * 设置状态栏颜色
     * @param colorId
     */
    public void setStatusBarColor(int colorId){
        mStatusBarColorResource = colorId;
    }
}
