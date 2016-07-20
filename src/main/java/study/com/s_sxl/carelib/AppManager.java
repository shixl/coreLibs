package study.com.s_sxl.carelib;

import android.app.Activity;

import java.util.Stack;

public final class AppManager{

    private Stack<Activity> mActivityStack;

    private static AppManager mAppManager;

    public AppManager(){

    }

    public static AppManager getAppManager(){
        if(mAppManager == null){
            mAppManager = new AppManager();
        }
        return mAppManager;
    }

    /**
     * 添加activity到任务栈中
     * @param activity
     */
    public void addActivity(Activity activity){
        if(mActivityStack == null){
            mActivityStack = new Stack<>();
        }
        mActivityStack.add(activity);
    }

    /**
     * 获取当前的activity（栈中最后一个压入的）
     * @return
     */
    public Activity getCurrentActivity(){
        return mActivityStack.lastElement();
    }

    /**
     * 结束当前的activity
     */
    public void finishActivity(){
        finishActivity(mActivityStack.lastElement());
    }

    /**
     * 结束指定的activity
     * @param activity
     */
    public void finishActivity(Activity activity){
        if(activity != null){
            mActivityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的activity
     * @param cls
     */
    public void finishActivity(Class<?> cls){
        Activity activity = null;
        for(Activity a: mActivityStack){
            if(a.getClass() == cls){
                activity = a;
                break;
            }
        }

        if(activity != null){
            finishActivity(activity);
        }
    }

    /**
     * 获取指定类名的activity
     * @param clz
     * @return
     */
    public Activity getActivity(Class<?> clz){
        if(mActivityStack != null && mActivityStack.size() != 0){
            for(int i = 0;i < mActivityStack.size();i++){
                Activity activity = mActivityStack.get(i);
                if(activity.getClass().equals(clz)){
                    return  activity;
                }
            }
        }
        return null;
    }

    /**
     * 结束所有activity
     */
    public void finishAllActivity(){
        for(int i =0;i<mActivityStack.size() ; i++){
            if(mActivityStack.get(i) != null){
                mActivityStack.get(i).finish();
            }
        }
        mActivityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void appExit(){
        finishAllActivity();
        System.exit(0);
    }

}
