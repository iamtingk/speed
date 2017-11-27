package com.test.tingk.tingkproject.data.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.test.tingk.tingkproject.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tingk on 2017/11/25.
 */

public class MenuListAdapter extends BaseAdapter {
    private Context context;
    private List<String> items;

    public MenuListAdapter(Context context, String[] items)
    {
        this.context = context;
        this.items = new ArrayList(Arrays.asList(items));
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.
                getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            return setMenuItem(convertView, inflater, items.get(position));
    }

    /**
     * 設定一般項目
     */
    private View setMenuItem(View convertView, LayoutInflater inflater, String title)
    {
        MenuItemHolder holder;
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.menu_item, null);
            holder = new MenuItemHolder();
            holder.title = (TextView) convertView.findViewById(R.id.text_title);
        }
        else
        {
            holder = (MenuItemHolder) convertView.getTag();
        }

        // 設定資料
        holder.title.setText(title);
        convertView.setTag(holder);
        return convertView;
    }

    /* -----------------------------------------------------------
     * Holder Classes
     * --------------------------------------------------------- */

    private class MenuItemHolder {
        TextView title;
    }
}
