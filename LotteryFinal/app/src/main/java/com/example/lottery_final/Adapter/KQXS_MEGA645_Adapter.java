package com.example.lottery_final.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lottery_final.Lottery_results.Lottery_MB;
import com.example.lottery_final.Lottery_results.Lottery_Vietlott655;
import com.example.lottery_final.Module.KQXS_MEGA655_Module;
import com.example.lottery_final.R;

import java.util.ArrayList;

public class KQXS_MEGA645_Adapter extends RecyclerView.Adapter<KQXS_MEGA645_Adapter.KQXSHolder> {
    private ArrayList<KQXS_MEGA655_Module> ListKQXS;

    private Lottery_Vietlott655 lottery_vietlott655;
    private Lottery_MB lottery_mb;



    public KQXS_MEGA645_Adapter(ArrayList<KQXS_MEGA655_Module> listKQXS, Lottery_Vietlott655 lottery_vietlott655) {
        ListKQXS = listKQXS;
        this.lottery_vietlott655 = lottery_vietlott655;
    }



    @NonNull
    @Override
    public KQXSHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mega_layout, parent, false);
        return new KQXSHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KQXSHolder holder, int position) {
        final KQXS_MEGA655_Module kqxs_mega655_module = ListKQXS.get(position);
        holder.TvGIAITHUONG.setText(kqxs_mega655_module.getGIAITHUONG());

    }

    @Override
    public int getItemCount() {
        return ListKQXS.size();
    }

    public class KQXSHolder extends RecyclerView.ViewHolder {
        private TextView TvGIAITHUONG;
        public KQXSHolder(@NonNull View itemView) {
            super(itemView);
            TvGIAITHUONG = (TextView)itemView.findViewById(R.id.TVResults655);
        }
    }
}
