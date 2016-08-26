package study.com.s_sxl.carelib.pullRefreshView.extras;


import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.widget.TextView;

import study.com.s_sxl.carelib.pullRefreshView.support.impl.OnScrollListener;
import study.com.s_sxl.carelib.pullRefreshView.support.impl.PullType;


public class PullTypeTextView extends TextView implements PullType {
    private OnScrollListener onScrollListener = null;

    public PullTypeTextView(Context context) {
        super(context);
        setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    public PullTypeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    public PullTypeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    public boolean isGetTop() {
        if (getScrollY() == 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean isGetBottom() {
        if (getScrollY() >= (getLayout().getHeight() - getMeasuredHeight() + getCompoundPaddingBottom() + getCompoundPaddingTop()))
            return true;
        else
            return false;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (onScrollListener != null) {
            onScrollListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }
}
