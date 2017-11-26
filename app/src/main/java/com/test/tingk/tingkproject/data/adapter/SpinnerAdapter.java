package com.test.tingk.tingkproject.data.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.test.tingk.tingkproject.R;

/**
 * Created by tingk on 2017/11/26.
 */

public class SpinnerAdapter extends BaseAdapter {
    private String[] typeItem;
    private Context context;
    private String strTitle;

    public SpinnerAdapter(Context context, String[] typeItem,  String strTitle) {
        this.context = context;
        this.typeItem = typeItem;
        this.strTitle = strTitle;
    }

    @Override
    public int getCount() {
        return typeItem.length;
    }

    @Override
    public Object getItem(int position) {
        return typeItem[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView txt = new TextView(context);
        txt.setText(typeItem[position]);
        txt.setTextSize(16);
        txt.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        txt.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,context.getResources().getDimensionPixelSize(R.dimen.row_length_50)));

        return txt;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.spinner_item,null);

        TextView txtLabel = (TextView)view.findViewById(R.id.txt_label);
        TextView txtContent = (TextView)view.findViewById(R.id.txt_content);

        if (view != null){
            txtLabel.setText(strTitle);
            txtContent.setText(typeItem[position]);
        }
        return view;
    }
}
