package study.com.s_sxl.carelib.pullRefreshView.extras;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import study.com.s_sxl.carelib.pullRefreshView.support.impl.PullType;


public class PullTypeImageView extends ImageView implements PullType {

    public PullTypeImageView(Context context) {
        super(context);
    }


    public PullTypeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullTypeImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    public boolean isGetTop() {
        return true;
    }

    @Override
    public boolean isGetBottom() {
        return true;
    }

}
