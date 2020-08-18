package com.example.lottery_final.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lottery_final.Lottery_results.Lottery_MB;
import com.example.lottery_final.Lottery_results.Lottery_MN_MT;
import com.example.lottery_final.Module.KQXS_Giai4_Module;
import com.example.lottery_final.R;

import java.util.ArrayList;

public class KQXS_Giai4_Adapter extends RecyclerView.Adapter<KQXS_Giai4_Adapter.KQXSHolder> {
    private ArrayList<KQXS_Giai4_Module> ListKQXS;

    private Lottery_MN_MT lottery_mn_mt;
    private Lottery_MB lotteryMB;


    public KQXS_Giai4_Adapter(ArrayList<KQXS_Giai4_Module> listKQXS, Lottery_MN_MT lottery_mn_mt) {
        ListKQXS = listKQXS;
        this.lottery_mn_mt = lottery_mn_mt;
    }

    public KQXS_Giai4_Adapter(ArrayList<KQXS_Giai4_Module> listKQXS, Lottery_MB lotteryMB) {
        ListKQXS = listKQXS;
        this.lotteryMB = lotteryMB;
    }


    @NonNull
    @Override
    public KQXSHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new KQXSHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KQXSHolder holder, int position) {
        final KQXS_Giai4_Module KQXSGiai4Module = ListKQXS.get(position);
        holder.TvGIAITHUONG.setText(KQXSGiai4Module.getGIAITHUONG());

    }

    @Override
    public int getItemCount() {
        return ListKQXS.size();
    }

    public class KQXSHolder extends RecyclerView.ViewHolder {
        private TextView TvGIAITHUONG;
        public KQXSHolder(@NonNull View itemView) {
            super(itemView);
            TvGIAITHUONG = (TextView)itemView.findViewById(R.id.TVGIAITHUONG);
        }
    }
}
