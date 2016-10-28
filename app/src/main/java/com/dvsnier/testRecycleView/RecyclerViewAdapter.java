package com.dvsnier.testRecycleView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dvsnier.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/28.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<String> dataSet = new ArrayList<>();

    public RecyclerViewAdapter() {
    }

    public RecyclerViewAdapter(Context context, List<String> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.layout_recycler_view_item, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String value = dataSet.get(position);
        holder.content.setText(value);
    }

    @Override
    public int getItemCount() {
        return null != dataSet ? dataSet.size() : 0;
    }

    public List<String> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<String> dataSet) {
        this.dataSet = dataSet;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView content;

        public ViewHolder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }
}
