package study.com.s_sxl.carelib.viewUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


import java.util.List;

import study.com.s_sxl.carelib.R;
import study.com.s_sxl.carelib.utils.DisplayUtil;

/**
 * Copyright  All rights reserved.
 * <p>Description: </p>
 * @ClassName PopupMenu
 * @Package study.com.s_sxl.carelib.viewUtils
 * @Author S_sxl
 * @Time 2016/9/1
 */

public class PopupMenu extends PopupWindow implements AdapterView.OnItemClickListener {

    private Context context;

    private FrameLayout parent;
    private ListView menu;

    private List<String> data;
    private MenuAdapter adapter;

    private int itemHeight;
    private int maxShowingItemCount = -1;

    private int selectedItem = -1;
    private OnMenuSelectedListener listener;

    public PopupMenu(Context context) {
        this.context = context;
        init();
    }

    public PopupMenu(Context context, List<String> data, String currentItem) {
        this(context);
        setup(data, currentItem, null);
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.view_popup_menu, null);

        itemHeight = DisplayUtil.dip2px(context, 40);

        setContentView(view);
        setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable(null, (Bitmap) null));
        setAnimationStyle(R.style.anim_style);

        parent = (FrameLayout) view.findViewById(R.id.parent);
        menu = (ListView) view.findViewById(R.id.menu);
    }

    public void setup(List<String> data, String currentItem, OnMenuSelectedListener listener) {
        this.data = data;
        this.listener = listener;
        selectedItem = calculateCurrentSelectedItem(currentItem);

        if (adapter == null) {
            adapter = new MenuAdapter();
            menu.setAdapter(adapter);
            menu.setOnItemClickListener(this);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private int calculateCurrentSelectedItem(String currentItem) {
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).equals(currentItem))
                    return i;
            }
        }

        return -1;
    }

    @Override
    public void showAsDropDown(View anchor) {
        int width = anchor.getWidth();
        setMenuWidth(width);
        checkShowingItemCount();

        super.showAsDropDown(anchor);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        checkShowingItemCount();
        super.showAtLocation(parent, gravity, x, y);
    }

    private void checkShowingItemCount() {
        if (maxShowingItemCount > 0 && maxShowingItemCount < data.size()) {
            setMenuHeight(itemHeight * maxShowingItemCount);
        }
    }

    public void setMenuWidth(int width) {
        LinearLayout.LayoutParams layoutParams =
                (LinearLayout.LayoutParams) parent.getLayoutParams();
        layoutParams.width = width;
        parent.setLayoutParams(layoutParams);
    }

    public void setMenuHeight(int height) {
        LinearLayout.LayoutParams layoutParams =
                (LinearLayout.LayoutParams) parent.getLayoutParams();
        layoutParams.height = height;
        parent.setLayoutParams(layoutParams);
    }

    public void setMaxShowingItemCount(int max) {
        maxShowingItemCount = max;
    }

    public interface OnMenuSelectedListener {
        void onMenuSelected(int position, String data);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectedItem = position;
        if (listener != null) listener.onMenuSelected(position, data.get(position));
        dismiss();
    }

    public int getSelectedItem() {
        return selectedItem;
    }

    public String getSelectedItemString() {
        return data.get(getSelectedItem());
    }

    class MenuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
                convertView = LayoutInflater.from(context).inflate(R.layout.item_popup_menu, null);

            TextView text = ViewHolder.get(convertView, R.id.item_menu);
            text.setText(data.get(position));

            if (position == selectedItem)
                text.setTextColor(ContextCompat.getColor(context, R.color.main));
            else
                text.setTextColor(ContextCompat.getColor(context, R.color.text_main));

            return convertView;
        }
    }
}
