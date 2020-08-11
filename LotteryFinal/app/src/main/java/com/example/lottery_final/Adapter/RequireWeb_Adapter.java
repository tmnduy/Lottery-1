package com.example.lottery_final.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.lottery_final.Lottery_results.Lottery;
import com.example.lottery_final.Module.RequireWebModule;
import com.example.lottery_final.R;

import java.util.ArrayList;

public class RequireWeb_Adapter extends RecyclerView.Adapter<RequireWeb_Adapter.RequireWebHolder> {
    private ArrayList<RequireWebModule> ListRequireWeb;

    public RequireWeb_Adapter(Lottery lottery, ArrayList<RequireWebModule> listRequireWeb) {
        this.ListRequireWeb = listRequireWeb;
    }


    @NonNull
    @Override
    public RequireWeb_Adapter.RequireWebHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kqxs_mb_layout, parent, false);
        return new RequireWebHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequireWeb_Adapter.RequireWebHolder holder, int position) {
        final RequireWebModule requireWebModule = ListRequireWeb.get(position);
        holder.TvPrize.setText(requireWebModule.getPrize());
        holder.TvNumberPrize.setText(requireWebModule.getNumberPrize());
        holder.TvDay.setText(requireWebModule.getDay());
        holder.TvTime.setText(requireWebModule.getTime());
        holder.TvCode.setText(requireWebModule.getCode());
        holder.TvProvince.setText(requireWebModule.getProvince());
    }

    @Override
    public int getItemCount() {
        return ListRequireWeb.size();
    }

    public class RequireWebHolder extends RecyclerView.ViewHolder {
        private TextView TvPrize, TvNumberPrize;
        private TextView TvDay, TvTime, TvCode;
        private TextView TvProvince;
        public RequireWebHolder(View itemView) {
            super(itemView);
        }
    }
}
