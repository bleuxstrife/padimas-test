package com.interview.test.padimas_test.modul.dashboard.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.interview.test.padimas_test.R;
import com.interview.test.padimas_test.modul.dashboard.model.CarListModel;
import com.interview.test.padimas_test.utils.UIUtils;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.ViewHolder> {

    Context context;
    List<CarListModel> modelList;

    public CarListAdapter(Context context, List<CarListModel> modelList){
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_special_car, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        CarListModel model = modelList.get(i);
        Picasso.with(context).load(model.getCarImage()).networkPolicy(NetworkPolicy.NO_CACHE).error(android.R.color.black).into(holder.carIV);
        holder.carNameTV.setText(model.getCarName()+"\n"+ UIUtils.setCurrency(model.getCarPrice()));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.carIV)
        ImageView carIV;
        @BindView(R.id.carNameTV)
        TextView carNameTV;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
