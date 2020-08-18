package com.example.lottery_final.Lottery_results;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lottery_final.Adapter.KQXS_MEGA655_Adapter;
import com.example.lottery_final.Module.KQXS_MEGA655_Module;
import com.example.lottery_final.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Lottery_Vietlott655 extends AppCompatActivity {

    private ImageView ImgBack;
    private RecyclerView RcMega655;
    private TextView TVMega655, TVSLGIAI3,TVTitle_MEGA655, TVSLGIAI2, TVSLGIAI1, TVSLGIAI_JACKPOT,TVSLGIAI_JACKPOT2, TVMega2655,TVResult655_7,TVThu_Mega655, Kive;
    final static String URL_MEGA = "https://www.minhngoc.net/ket-qua-xo-so/dien-toan-vietlott/power-6x55.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery_vietlott655);

        TVSLGIAI3 = (TextView) findViewById(R.id.TVSL_Giai3);
        TVSLGIAI2 = (TextView) findViewById(R.id.TVSL_Giai2);
        TVSLGIAI1 = (TextView) findViewById(R.id.TVSL_Giai1);
        TVSLGIAI_JACKPOT = (TextView) findViewById(R.id.TVSL_Jackpot1);
        TVSLGIAI_JACKPOT2 = (TextView) findViewById(R.id.TVSL_Jackpot2);

        TVResult655_7 = (TextView)findViewById(R.id.TVResults655_7);

        TVMega655 = (TextView) findViewById(R.id.TVJackpot_655);
        TVMega2655 = (TextView) findViewById(R.id.TVJackpot2_655);
        TVTitle_MEGA655 = (TextView)findViewById(R.id.TVTitle_MEGA655);

        TVThu_Mega655 = (TextView)findViewById(R.id.TVThu_MEGA655);
        Kive = (TextView)findViewById(R.id.TVKiVe655);



        ImgBack = (ImageView) findViewById(R.id.back_arrow);
        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RcMega655 = (RecyclerView) findViewById(R.id.RCMega655);

        configRecyclerView();
        new DownloadTask().execute(URL_MEGA);

    }

    private void configRecyclerView() {
        RecyclerView.LayoutManager layoutManagerMega = new GridLayoutManager(this, 6);
        RcMega655.hasFixedSize();
        RcMega655.setLayoutManager(layoutManagerMega);
    }

    public class DownloadTask extends AsyncTask<String, Void, ArrayList<KQXS_MEGA655_Module>> {
        int i = 0;

        @SuppressLint("WrongThread")
        @Override
        protected ArrayList<KQXS_MEGA655_Module> doInBackground(String... strings) {
            Document document = null;
            ArrayList<KQXS_MEGA655_Module> List_KQXS = new ArrayList<>();
            try {
                document = (Document) Jsoup.connect(strings[0]).get();
                if (document != null) {

                    String[] SL = {"G1", "G2", "G3", "JACKPOT","JACKPOT2"};

                    Elements Number = document.select("div#result-games>div.box-result-detail>ul.result-number>li");


                    Element ThuJackpot655 = document.select("div#result-games>div.box-result-detail>table>tbody>tr>td.thu_mega>a").first();
                    Element Number7 = document.select("div#result-games>div.box-result-detail>ul.result-number>li>div.finnish7").first();
                    Element Jackpot655 = document.select("b#DT6X55_G_JACKPOT").first();
                    Element Jackpot2655 = document.select("b#DT6X55_G_JACKPOT2").first();
                    Element Kive645 = document.select("span#DT6X45_KY_VE").first();
                    Element TVTitle = document.select("div#noidung>center>div.boxkqxsdientoan>h4").first();


                    if (!TVTitle.toString().isEmpty()) {
                        TVTitle_MEGA655.setText(TVTitle.text());
                    }

                    if (!Jackpot655.toString().isEmpty()) {
                        Log.e("String", Jackpot655.toString());
                        TVMega655.setText(Jackpot655.text());
                        Log.e("ssa", String.valueOf(TVMega655));
                    }
                    if (!Jackpot2655.toString().isEmpty()) {
                        Log.e("String", Jackpot2655.toString());
                        TVMega2655.setText(Jackpot2655.text());
                        Log.e("ssa", String.valueOf(TVMega2655));
                    }
                    if (!Number7.toString().isEmpty()){
                        TVResult655_7.setText(Number7.text());
                    }
                    if (!ThuJackpot655.toString().isEmpty()){
                        TVThu_Mega655.setText(ThuJackpot655.text());
                    }
                    if (!Kive645.toString().isEmpty()){
                        Kive.setText("Kì vé: "+Kive645.text());
                    }

                    for (Element element : Number) {

                        KQXS_MEGA655_Module kqxs_mega655_module = new KQXS_MEGA655_Module();
                        Element NumberSubject = element.getElementsByTag("div").first();
                        if (!NumberSubject.toString().isEmpty()) {
                            String PrizeDB = NumberSubject.text();
                            kqxs_mega655_module.setGIAITHUONG(PrizeDB);
                            List_KQXS.add(kqxs_mega655_module);
                            i++;
                        }
                        if (i == 6) {
                            break;
                        }
                    }
                    for (int i = 0; i < SL.length; i++) {
                        Elements SLJackpot645 = document.select("td#DT6X55_S_" + SL[i]);
                        for (Element elementSL : SLJackpot645) {
                            Element SLSubject = elementSL.getElementsByTag("td").first();
                            if (SL[i].equals("G1")) {
                                if (!SLSubject.toString().isEmpty()) {
                                    String SL645 = SLSubject.text();
                                    TVSLGIAI1.setText(SL645);
                                }
                                break;
                            }
                            if (SL[i].equals("G2")) {
                                if (!SLSubject.toString().isEmpty()) {
                                    String SL645 = SLSubject.text();
                                    TVSLGIAI2.setText(SL645);
                                }
                                break;
                            }
                            if (SL[i].equals("G3")) {
                                if (!SLSubject.toString().isEmpty()) {
                                    String SL645 = SLSubject.text();
                                    TVSLGIAI3.setText(SL645);
                                }
                                break;
                            }
                            if (SL[i].equals("JACKPOT")) {
                                if (!SLSubject.toString().isEmpty()) {
                                    String SL645 = SLSubject.text();
                                    TVSLGIAI_JACKPOT.setText(SL645);
                                }
                                break;
                            }
                            if (SL[i].equals("JACKPOT2")) {
                                if (!SLSubject.toString().isEmpty()) {
                                    String SL645 = SLSubject.text();
                                    TVSLGIAI_JACKPOT2.setText(SL645);
                                }
                                break;
                            }
                        }
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return List_KQXS;
        }

        @Override
        protected void onPostExecute(ArrayList<KQXS_MEGA655_Module> kqxs_mega655_modules) {
            super.onPostExecute(kqxs_mega655_modules);
            KQXS_MEGA655_Adapter kqxs_giai6_adapter = new KQXS_MEGA655_Adapter(kqxs_mega655_modules, Lottery_Vietlott655.this);
            RcMega655.setAdapter(kqxs_giai6_adapter);
        }
    }

}