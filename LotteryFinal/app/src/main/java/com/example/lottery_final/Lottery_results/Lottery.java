package com.example.lottery_final.Lottery_results;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.lottery_final.Module.RequireWebModule;
import com.example.lottery_final.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Lottery extends AppCompatActivity {

    TextView Tinh, Day, Code, GIAIDB, GIAI1, GIAI2;

    private ImageView ImgBack;
//    private RecyclerView recyclerView;
//    private RequireWeb_Adapter requireWeb_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kqxs_mb_layout);

        Intent intent = getIntent();
        String KQXS = intent.getStringExtra("KQXS");




        Tinh = (TextView) findViewById(R.id.TvProvince1);
        Day = (TextView) findViewById(R.id.TVThu);
        Code = (TextView) findViewById(R.id.TvCode);
        GIAIDB = (TextView) findViewById(R.id.TVGiaiDB);
        GIAI1 = (TextView) findViewById(R.id.TVGiai1);
        GIAI2 = (TextView) findViewById(R.id.TVGiai2);
        //  recyclerView = (RecyclerView) findViewById(R.id.RcGiai3);
        //  recyclerView = (RecyclerView) findViewById(R.id.RcGiai4);
        //  recyclerView = (RecyclerView) findViewById(R.id.RcGiai5);
        //  recyclerView = (RecyclerView) findViewById(R.id.RcGiai6);
        //  recyclerView = (RecyclerView) findViewById(R.id.RcGiai7);


        ImgBack = (ImageView) findViewById(R.id.back_arrow);
        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        switch (KQXS) {
            case "MB":

                final String URL_KQXS_MB = "https://www.minhngoc.net/xo-so-mien-bac.html";
                // configRecyclerView();
                new DownloadTask().execute(URL_KQXS_MB);
                System.out.println(KQXS);
                break;
            case "MT":

                final String URL_KQXS_MT = "https://www.minhngoc.net/xo-so-mien-trung.html";
                // configRecyclerView();
                new DownloadTask().execute(URL_KQXS_MT);
                System.out.println(KQXS);
                break;
            case "MN":

                final String MURL_KQXS_MN = "https://www.minhngoc.net/xo-so-mien-nam.html";
                // configRecyclerView();
                new DownloadTask().execute(MURL_KQXS_MN);
                System.out.println(KQXS);
                break;
            case "MEGA":
                final String MURL_KQXS_MEGA = "https://www.minhngoc.net/ket-qua-xo-so/dien-toan-vietlott/mega-6x45.html";
                // configRecyclerView();
                new DownloadTask().execute(MURL_KQXS_MEGA);
                System.out.println(KQXS);
                break;
            case "POWER":
                final String MURL_KQXS_POWER655 = "https://www.minhngoc.net/ket-qua-xo-so/dien-toan-vietlott/power-6x55.html";
                //configRecyclerView();
                new DownloadTask().execute(MURL_KQXS_POWER655);
                System.out.println(KQXS);
                break;
        }
    }

//    private void configRecyclerView() {
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.hasFixedSize();
//        recyclerView.setLayoutManager(layoutManager);
//    }


    public class DownloadTask extends AsyncTask<String, Void, ArrayList<RequireWebModule>> {
        String ProvinceTitle;

        private static final String TAG = "DownloadTask";

        @Override
        protected ArrayList<RequireWebModule> doInBackground(String... strings) {

            Document document = null;
            ArrayList<RequireWebModule> ListRequire = new ArrayList<>();
            RequireWebModule requireWebModule = new RequireWebModule();


            try {
                document = (Document) Jsoup.connect(strings[0]).get();
                Log.e("doc", document.text());
                if (document != null) {


                    Elements subTitleProvince = document.select("div#noidung");
                    Elements subcode = document.select("div#noidung>div>div#bangkqxsmien>div.box_kqxs>div.content>table.bkqmiennam>tbody>tr>td>table>tbody>tr>td>table.bkqtinhmienbac>tbody>tr>td.ngay>div.phathanh>div.loai_ves>div.loaive_content");
                    Elements subPrizeDB = document.select("div#noidung>div>div#bangkqxsmien>div.box_kqxs>div.content>table.bkqmiennam>tbody>tr>td>table>tbody>tr>td>table.bkqtinhmienbac>tbody>tr>td.giaidb>div");
                    Elements subGIAI1 = document.select("div#noidung>div>div#bangkqxsmien>div.box_kqxs>div.content>table.bkqmiennam>tbody>tr>td>table>tbody>tr>td>table.bkqtinhmienbac>tbody>tr>td.giai1>div");
                    Elements subGIAI2 = document.select("div#noidung>div>div#bangkqxsmien>div.box_kqxs>div.content>table.bkqmiennam>tbody>tr>td>table>tbody>tr>td>table.bkqtinhmienbac>tbody>tr>td.giai2>div");


                    for (Element element : subTitleProvince) {
                        Element ProvinceSubject = element.getElementsByTag("h1").first();
                        ProvinceTitle = ProvinceSubject.text();
                       Tinh.setText(ProvinceTitle);;
                    }
//                    Elements sub1 = document.select("div#noidung>div>div#bangkqxsmien>div.box_kqxs>div.content>table.bkqmiennam>tbody>tr>td>table>tbody>tr>td>table.bkqtinhmienbac>tbody>tr>td.thu");
//                    Log.e("sub1", sub1.toString());

//                    for (Element element1 : sub1) {
//                        Element DaySubject = element1.getElementsByTag("a").first();
//                        String DayTitle = DaySubject.text();
//                        Log.e("thu", DayTitle);
//                        Day.setText(DayTitle);
//                    }

                    for (Element element2 : subcode) {
                        Element CodeSubject = element2.getElementsByTag("div").first();
                        String CodeTitle = CodeSubject.text();
                          Code.setText("Mã vé số: " + CodeTitle);
                    }

                    for (Element element3 : subPrizeDB) {
                        Element PrizeSubjectDB = element3.getElementsByTag("div").first();
                        String PrizeTitleDB = PrizeSubjectDB.text();
                        GIAIDB.setText(PrizeTitleDB);
                    }

                    for (Element element4 : subGIAI1) {
                        Element GIAI1Subject = element4.getElementsByTag("div").first();
                        String Giai1 = GIAI1Subject.text();
                        GIAI1.setText(Giai1);
                    }

                    for (Element element5 : subGIAI2) {
                        Element GIAI2Subject = element5.getElementsByTag("div").first();
                        String Giai2 = GIAI2Subject.text();
                        GIAI2.setText(Giai2);
                    }

                }


            } catch (
                    IOException e) {
                e.printStackTrace();
            }
            return ListRequire;
        }

//        protected void onPostExecute(ArrayList<RequireWebModule> requireWebModules) {
//            super.onPostExecute(requireWebModules);
//            //Setup data recyclerView
//            requireWeb_adapter = new RequireWeb_Adapter(Lottery.this, requireWebModules);
//            recyclerView.setAdapter(requireWeb_adapter);
//        }

    }

}