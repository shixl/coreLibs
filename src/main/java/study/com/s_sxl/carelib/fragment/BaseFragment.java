package study.com.s_sxl.carelib.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import study.com.s_sxl.carelib.activity.BaseActivity;

/**
 * Copyright  @2016. All rights reserved.
 * <p>Description: </p>
 * @ClassName BaseFragment
 * @Package study.com.s_sxl.carelib.fragment
 * @Author S_sxl
 * @Time 2016/6/29
 */
public abstract class BaseFragment extends Fragment{

    private View mParentView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParentView = getLayoutInflater(savedInstanceState).inflate(getLayoutId(), null, false);
        ButterKnife.bind(this,mParentView);
        init(savedInstanceState);
    }

    /**
     * 初始化方法, 类似OnCreate, 仅在此方法中做初始化操作, findView与事件绑定请使用ButterKnife
     */
    protected abstract void init(Bundle savedInstanceState);

    /**
     * 指定Fragment需加载的布局ID
     * @return 需加载的布局ID
     */
    protected abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mParentView == null){
            mParentView = inflater.inflate(getLayoutId(),null,false);
        }
        return mParentView;
    }

    public void showLoading() {
        if(getActivity() instanceof BaseActivity) ((BaseActivity)getActivity()).showDialog();
    }

    public void hideLoading() {
        if(getActivity() instanceof BaseActivity) ((BaseActivity)getActivity()).hideDialog();
    }

    public void showToastMessage(String message) {
        if(getActivity() instanceof BaseActivity) ((BaseActivity)getActivity()).showToast(message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
