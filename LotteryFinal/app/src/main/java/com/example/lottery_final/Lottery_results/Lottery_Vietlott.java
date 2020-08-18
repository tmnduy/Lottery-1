package com.example.lottery_final.Lottery_results;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lottery_final.Adapter.KQXS_Giai6_Adapter;
import com.example.lottery_final.Adapter.KQXS_MEGA655_Adapter;
import com.example.lottery_final.Module.KQXS_Giai6_Module;
import com.example.lottery_final.Module.KQXS_MEGA655_Module;
import com.example.lottery_final.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;

public class Lottery_Vietlott extends AppCompatActivity {
    private ImageView ImgBack;
    private TextView TVMega645, TVSLGIAI3, TVSLGIAI2, TVSLGIAI1, TVSLGIAI_JACKPOT, TVTHU;
    private TextView TVResults645_1, TVResults645_2, TVResults645_3, TVResults645_4, TVResults645_5, TVResults645_6,TVTitle_MEGA645, Kive;

    final private String URL_MEGA_645 = "https://www.minhngoc.net/ket-qua-xo-so/dien-toan-vietlott/mega-6x45.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery_vietlott);

        TVTHU = (TextView) findViewById(R.id.TVThu_MEGA);
        TVTitle_MEGA645 = (TextView)findViewById(R.id.TVTitle_MEGA645);
        Kive = (TextView)findViewById(R.id.TVKiVe);

        TVMega645 = (TextView) findViewById(R.id.TVJackpot_645);
        TVSLGIAI3 = (TextView) findViewById(R.id.TVSL_Giai3);
        TVSLGIAI2 = (TextView) findViewById(R.id.TVSL_Giai2);
        TVSLGIAI1 = (TextView) findViewById(R.id.TVSL_Giai1);
        TVSLGIAI_JACKPOT = (TextView) findViewById(R.id.TVSL_Jackpot);

        TVResults645_1 = (TextView) findViewById(R.id.TVResults645_1);
        TVResults645_2 = (TextView) findViewById(R.id.TVResults645_2);
        TVResults645_3 = (TextView) findViewById(R.id.TVResults645_3);
        TVResults645_4 = (TextView) findViewById(R.id.TVResults645_4);
        TVResults645_5 = (TextView) findViewById(R.id.TVResults645_5);
        TVResults645_6 = (TextView) findViewById(R.id.TVResults645_6);


        ImgBack = (ImageView) findViewById(R.id.back_arrow);
        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        new DownloadTask().execute(URL_MEGA_645);

    }

    public class DownloadTask extends AsyncTask<String, Void, ArrayList<KQXS_MEGA655_Module>> {

        @SuppressLint("WrongThread")
        @Override
        protected ArrayList<KQXS_MEGA655_Module> doInBackground(String... strings) {
            Document document = null;
            ArrayList<KQXS_MEGA655_Module> List_KQXS = new ArrayList<>();
            try {
                document = (Document) Jsoup.connect(strings[0]).get();
                if (!document.toString().isEmpty()) {

                    Element Jackpot645 = document.select("b#DT6X45_G_JACKPOT").first();
                    Element TVTitle = document.select("div#noidung>center>div.boxkqxsdientoan>h4").first();
                    Element ThuJackpot645 = document.select("div#result-games>div.box-result-detail>table>tbody>tr>td.thu_mega").first();
                    Element Kive645 = document.select("span#DT6X45_KY_VE").first();

                    Log.e("thaa", ThuJackpot645.toString());

                    if (!Jackpot645.toString().isEmpty()) {
                        TVMega645.setText(Jackpot645.text());
                    }
                    if (!TVTitle.toString().isEmpty()) {
                        TVTitle_MEGA645.setText(TVTitle.text());
                    }

                    if (!ThuJackpot645.toString().isEmpty()) {
                        TVTHU.setText(ThuJackpot645.text());
                    }
                    if (!Kive645.toString().isEmpty()){
                        Kive.setText("Kì vé: "+Kive645.text());
                    }


                    String[] SL = {"G1", "G2", "G3", "JACKPOT"};
                    String[] Result645 = {"1", "2", "3", "4", "5", "6"};

                    for (int i = 0; i < SL.length; i++) {
                        Elements SLJackpot645 = document.select("td#DT6X45_S_" + SL[i]);
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
                        }
                    }

                    for (int i = 0; i < Result645.length; i++) {

                        Elements Number645 = document.select("div#result-games>div.box-result-detail>ul.result-number>li>div.finnish" + Result645[i]);

                        for (Element element : Number645) {
                            Elements Result = element.getElementsByTag("div");
                            if (Result645[i].equals("1")) {
                                if (!Result.toString().isEmpty()) {
                                    String result = Result.text();
                                    TVResults645_1.setText(result);
                                }
                            }
                            if (Result645[i].equals("2")) {
                                if (!Result.toString().isEmpty()) {
                                    String result = Result.text();
                                    TVResults645_2.setText(result);
                                }
                            }
                            if (Result645[i].equals("3")) {
                                if (!Result.toString().isEmpty()) {
                                    String result = Result.text();
                                    TVResults645_3.setText(result);
                                }
                            }
                            if (Result645[i].equals("4")) {
                                if (!Result.toString().isEmpty()) {
                                    String result = Result.text();
                                    TVResults645_4.setText(result);
                                }
                            }
                            if (Result645[i].equals("5")) {
                                if (!Result.toString().isEmpty()) {
                                    String result = Result.text();
                                    TVResults645_5.setText(result);
                                }
                            }
                            if (Result645[i].equals("6")) {
                                if (!Result.toString().isEmpty()) {
                                    String result = Result.text();
                                    TVResults645_6.setText(result);
                                    Log.e("ree", result);
                                }
                            }
                            break;
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
        }
    }


}