package com.test.tingk.tingkproject.data.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.tingk.tingkproject.R;
import com.test.tingk.tingkproject.data.model.RecordModel;

import java.util.List;

/**
 * Created by tingk on 2017/11/25.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ListViewHolder> {
    private Context context;
    private List<RecordModel> record_items;

    public RecyclerAdapter(Context context, List<RecordModel> record_items) {
        this.context = context;
        this.record_items = record_items;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_showlist, parent, false);
        ListViewHolder viewHolder = new ListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        RecordModel item = record_items.get(position);
        holder.txtAddress.setText(item.getAddress());
        holder.txtCity.setText(item.getCityName()+" - "+item.getRegionName());
        holder.txtLimit.setText(item.getLimit());
        holder.txtDirect.setText(item.getDirect());
        holder.txtFromNm.setText(item.getDeptNm()+" - "+item.getBranchNm());
    }

    @Override
    public int getItemCount() {
        return record_items.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView txtCity, txtAddress, txtDirect, txtLimit, txtFromNm;

        public ListViewHolder(View itemView) {
            super(itemView);
            txtCity = (TextView) itemView.findViewById(R.id.txt_city);
            txtAddress = (TextView) itemView.findViewById(R.id.txt_address);
            txtDirect = (TextView) itemView.findViewById(R.id.txt_direct);
            txtLimit = (TextView) itemView.findViewById(R.id.txt_limit);
            txtFromNm = (TextView) itemView.findViewById(R.id.txt_fromNm);
        }
    }
}
