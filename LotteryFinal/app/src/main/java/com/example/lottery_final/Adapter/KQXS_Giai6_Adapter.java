package com.example.lottery_final.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lottery_final.Lottery_results.Lottery_MB;
import com.example.lottery_final.Lottery_results.Lottery_MN_MT;
import com.example.lottery_final.Module.KQXS_Giai3_Module;
import com.example.lottery_final.Module.KQXS_Giai6_Module;
import com.example.lottery_final.R;

import java.util.ArrayList;

public class KQXS_Giai6_Adapter extends RecyclerView.Adapter<KQXS_Giai6_Adapter.KQXSHolder> {
    private ArrayList<KQXS_Giai6_Module> ListKQXS6;

    private Lottery_MN_MT lottery_mn_mt;
    private Lottery_MB lotteryMB;



    public KQXS_Giai6_Adapter(ArrayList<KQXS_Giai6_Module> listKQXS6, Lottery_MN_MT lottery_mn_mt) {
        ListKQXS6 = listKQXS6;
        this.lottery_mn_mt = lottery_mn_mt;
    }

    public KQXS_Giai6_Adapter(ArrayList<KQXS_Giai6_Module> listKQXS6, Lottery_MB lotteryMB) {
        ListKQXS6 = listKQXS6;
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
        final KQXS_Giai6_Module kqxs_module6 = ListKQXS6.get(position);
        holder.TvGIAITHUONG.setText(kqxs_module6.getGIAITHUONG());

    }

    @Override
    public int getItemCount() {
        return ListKQXS6.size();
    }

    public class KQXSHolder extends RecyclerView.ViewHolder {
        private TextView TvGIAITHUONG;
        public KQXSHolder(@NonNull View itemView) {
            super(itemView);
            TvGIAITHUONG = (TextView)itemView.findViewById(R.id.TVGIAITHUONG);
        }
    }
}
