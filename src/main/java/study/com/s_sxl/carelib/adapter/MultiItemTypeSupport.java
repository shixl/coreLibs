package study.com.s_sxl.carelib.adapter;

public interface MultiItemTypeSupport<T> {

    int getLayoutId(int position , T t);

    int getViewTypeCount();

    int getItemViewType(int position,T t );
}
