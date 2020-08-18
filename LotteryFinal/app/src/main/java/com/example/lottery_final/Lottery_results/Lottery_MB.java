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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.lottery_final.Adapter.KQXS_Giai3_Adapter;
import com.example.lottery_final.Adapter.KQXS_Giai4_Adapter;
import com.example.lottery_final.Adapter.KQXS_Giai5_Adapter;
import com.example.lottery_final.Adapter.KQXS_Giai6_Adapter;
import com.example.lottery_final.Adapter.KQXS_Giai7_Adapter;
import com.example.lottery_final.Module.KQXS_Giai3_Module;
import com.example.lottery_final.Module.KQXS_Giai4_Module;
import com.example.lottery_final.Module.KQXS_Giai5_Module;
import com.example.lottery_final.Module.KQXS_Giai6_Module;
import com.example.lottery_final.Module.KQXS_Giai7_Module;
import com.example.lottery_final.Module.RequireWebModule;
import com.example.lottery_final.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Lottery_MB extends AppCompatActivity {

    TextView Tinh, Day, Code, GIAIDB, GIAI1, GIAI2;

    private ImageView ImgBack;
    private RecyclerView RcGiai4, RcGiai6, RcGiai3, RcGiai5, RcGiai7;
    final String URL_KQXS_MB = "https://www.minhngoc.net/xo-so-mien-bac.html";


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


        RcGiai3 = (RecyclerView) findViewById(R.id.RcGiai3);
        RcGiai4 = (RecyclerView) findViewById(R.id.RcGiai4);
        RcGiai5 = (RecyclerView) findViewById(R.id.RcGiai5);
        RcGiai6 = (RecyclerView) findViewById(R.id.RcGiai6);
        RcGiai7 = (RecyclerView) findViewById(R.id.RcGiai7);


        ImgBack = (ImageView) findViewById(R.id.back_arrow);
        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        configRecyclerView();

        new DownloadTask().execute(URL_KQXS_MB);
        new DownloadTask2().execute(URL_KQXS_MB);
        new DownloadTask3().execute(URL_KQXS_MB);
        new DownloadTask4().execute(URL_KQXS_MB);
        new DownloadTask5().execute(URL_KQXS_MB);
        new DownloadTask6().execute(URL_KQXS_MB);


    }

    private void configRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        RcGiai4.hasFixedSize();
        RcGiai4.setLayoutManager(layoutManager);

        RecyclerView.LayoutManager layoutManager6 = new GridLayoutManager(this, 3);
        RcGiai6.hasFixedSize();
        RcGiai6.setLayoutManager(layoutManager6);

        RecyclerView.LayoutManager layoutManager3 = new GridLayoutManager(this, 3);
        RcGiai3.hasFixedSize();
        RcGiai3.setLayoutManager(layoutManager3);

        RecyclerView.LayoutManager layoutManager5 = new GridLayoutManager(this, 3);
        RcGiai5.hasFixedSize();
        RcGiai5.setLayoutManager(layoutManager5);

        RecyclerView.LayoutManager layoutManager7 = new GridLayoutManager(this, 4);
        RcGiai7.hasFixedSize();
        RcGiai7.setLayoutManager(layoutManager7);
    }


    public class DownloadTask extends AsyncTask<String, Void, ArrayList<RequireWebModule>> {


        @SuppressLint("WrongThread")
        @Override
        protected ArrayList<RequireWebModule> doInBackground(String... strings) {

            Document document = null;
            ArrayList<RequireWebModule> ListRequire = new ArrayList<>();


            try {

                document = (Document) Jsoup.connect(strings[0]).get();

                if (!document.toString().isEmpty()) {


                    Element subTitleProvince = document.select("div#noidung>h1").first();
                    Element sub1 = document.select("div#noidung>div>div#bangkqxsmien>div.box_kqxs>div.content>table.bkqmiennam>tbody>tr>td>table>tbody>tr>td>table.bkqtinhmienbac>tbody>tr>td.thu>a").first();
                    Element subcode = document.select("div#noidung>div>div#bangkqxsmien>div.box_kqxs>div.content>table.bkqmiennam>tbody>tr>td>table>tbody>tr>td>table.bkqtinhmienbac>tbody>tr>td.ngay>div.phathanh>div.loai_ves>div.loaive_content").first();
                    Element subPrizeDB = document.select("div#noidung>div>div#bangkqxsmien>div.box_kqxs>div.content>table.bkqmiennam>tbody>tr>td>table>tbody>tr>td>table.bkqtinhmienbac>tbody>tr>td.giaidb>div").first();
                    Element subGIAI1 = document.select("div#noidung>div>div#bangkqxsmien>div.box_kqxs>div.content>table.bkqmiennam>tbody>tr>td>table>tbody>tr>td>table.bkqtinhmienbac>tbody>tr>td.giai1>div").first();
                    Element subGIAI2 = document.select("div#noidung>div>div#bangkqxsmien>div.box_kqxs>div.content>table.bkqmiennam>tbody>tr>td>table>tbody>tr>td>table.bkqtinhmienbac>tbody>tr>td.giai2>div").first();


                    if (!subTitleProvince.toString().isEmpty()){
                        Tinh.setText(subTitleProvince.text());
                    }

                    if (!sub1.toString().isEmpty()) {
                        Day.setText(sub1.text());
                    }

                    if (!subcode.toString().isEmpty()) {
                        Code.setText("Mã vé số: " + subcode.text());
                    }

                    if (!subPrizeDB.toString().isEmpty()) {
                        GIAIDB.setText(subPrizeDB.text());
                    }

                    if (!subGIAI1.toString().isEmpty()) {
                        GIAI1.setText(subGIAI1.text());
                    }

                    if (!subGIAI2.toString().isEmpty()) {
                        GIAI2.setText(subGIAI2.text());
                    }

                }


            } catch (
                    IOException e) {
                e.printStackTrace();
            }
            return ListRequire;
        }

        @Override
        protected void onPostExecute(ArrayList<RequireWebModule> requireWebModules) {
            super.onPostExecute(requireWebModules);
        }
    }

    private class DownloadTask2 extends AsyncTask<String, Void, ArrayList<KQXS_Giai4_Module>> {


        @Override
        protected ArrayList<KQXS_Giai4_Module> doInBackground(String... strings) {

            Document document = null;

            ArrayList<KQXS_Giai4_Module> List_KQXS4 = new ArrayList<>();
            try {

                document = (Document) Jsoup.connect(strings[0]).get();

                if (!document.toString().isEmpty()) {

                    Elements subGIAI = document.select("div#noidung>div>div#bangkqxsmien>div.box_kqxs>div.content>table.bkqmiennam>tbody>tr>td>table>tbody>tr>td>table.bkqtinhmienbac>tbody>tr>td.giai4>div");

                    for (Element element2 : subGIAI) {

                        KQXS_Giai4_Module KQXSGiai4Module = new KQXS_Giai4_Module();

                        Element PrizeSubject = element2.getElementsByTag("div").first();

                        if (!PrizeSubject.toString().isEmpty()) {
                            KQXSGiai4Module.setGIAITHUONG(PrizeSubject.text());
                        }
                        List_KQXS4.add(KQXSGiai4Module);
                    }
                }


            } catch (
                    IOException e) {
                e.printStackTrace();
            }
            return List_KQXS4;
        }

        @Override
        protected void onPostExecute(ArrayList<KQXS_Giai4_Module> kqxs_modules4) {
            super.onPostExecute(kqxs_modules4);
            KQXS_Giai4_Adapter kqxs_giai4Adapter = new KQXS_Giai4_Adapter(kqxs_modules4, Lottery_MB.this);
            RcGiai4.setAdapter(kqxs_giai4Adapter);
        }

    }

    private class DownloadTask3 extends AsyncTask<String, Void, ArrayList<KQXS_Giai6_Module>> {



        @Override
        protected ArrayList<KQXS_Giai6_Module> doInBackground(String... strings) {

            Document document = null;

            ArrayList<KQXS_Giai6_Module> List_KQXS6 = new ArrayList<>();
            try {
                document = (Document) Jsoup.connect(strings[0]).get();

                if (!document.toString().isEmpty()) {

                    Elements subGIAI = document.select("div#noidung>div>div#bangkqxsmien>div.box_kqxs>div.content>table.bkqmiennam>tbody>tr>td>table>tbody>tr>td>table.bkqtinhmienbac>tbody>tr>td.giai6>div");

                    for (Element element2 : subGIAI) {

                        KQXS_Giai6_Module kqxs_module6 = new KQXS_Giai6_Module();

                        Element PrizeSubject = element2.getElementsByTag("div").first();
                        if (!PrizeSubject.toString().isEmpty()) {
                            kqxs_module6.setGIAITHUONG(PrizeSubject.text());
                        }
                        List_KQXS6.add(kqxs_module6);
                    }
                }


            } catch (
                    IOException e) {
                e.printStackTrace();
            }
            return List_KQXS6;
        }

        @Override
        protected void onPostExecute(ArrayList<KQXS_Giai6_Module> kqxs_modules6) {
            super.onPostExecute(kqxs_modules6);
            KQXS_Giai6_Adapter kqxs_giai6Adapter = new KQXS_Giai6_Adapter(kqxs_modules6, Lottery_MB.this);
            RcGiai6.setAdapter(kqxs_giai6Adapter);
        }

    }

    private class DownloadTask4 extends AsyncTask<String, Void, ArrayList<KQXS_Giai3_Module>> {

        @Override
        protected ArrayList<KQXS_Giai3_Module> doInBackground(String... strings) {

            Document document = null;

            ArrayList<KQXS_Giai3_Module> List_KQXS3 = new ArrayList<>();
            try {
                document = (Document) Jsoup.connect(strings[0]).get();

                if (!document.toString().isEmpty()) {

                    Elements subGIAI = document.select("div#noidung>div>div#bangkqxsmien>div.box_kqxs>div.content>table.bkqmiennam>tbody>tr>td>table>tbody>tr>td>table.bkqtinhmienbac>tbody>tr>td.giai3>div");

                    for (Element element2 : subGIAI) {
                        KQXS_Giai3_Module kqxs_module3 = new KQXS_Giai3_Module();
                        Element PrizeSubject = element2.getElementsByTag("div").first();
                        if (!PrizeSubject.toString().isEmpty()) {
                            kqxs_module3.setGIAITHUONG(PrizeSubject.text());
                        }
                        List_KQXS3.add(kqxs_module3);
                    }
                }


            } catch (
                    IOException e) {
                e.printStackTrace();
            }
            return List_KQXS3;
        }

        @Override
        protected void onPostExecute(ArrayList<KQXS_Giai3_Module> kqxs_modules3) {
            super.onPostExecute(kqxs_modules3);
            KQXS_Giai3_Adapter kqxs_giai3Adapter = new KQXS_Giai3_Adapter(kqxs_modules3, Lottery_MB.this);
            RcGiai3.setAdapter(kqxs_giai3Adapter);
        }

    }

    private class DownloadTask5 extends AsyncTask<String, Void, ArrayList<KQXS_Giai5_Module>> {


        @Override
        protected ArrayList<KQXS_Giai5_Module> doInBackground(String... strings) {

            Document document = null;

            ArrayList<KQXS_Giai5_Module> List_KQXS5 = new ArrayList<>();
            try {
                document = (Document) Jsoup.connect(strings[0]).get();

                if (!document.toString().isEmpty()) {

                    Elements subGIAI = document.select("div#noidung>div>div#bangkqxsmien>div.box_kqxs>div.content>table.bkqmiennam>tbody>tr>td>table>tbody>tr>td>table.bkqtinhmienbac>tbody>tr>td.giai5>div");

                    for (Element element2 : subGIAI) {
                        KQXS_Giai5_Module kqxs_module5 = new KQXS_Giai5_Module();
                        Element PrizeSubject = element2.getElementsByTag("div").first();
                        if (!PrizeSubject.toString().isEmpty()) {
                            kqxs_module5.setGIAITHUONG(PrizeSubject.text());
                        }
                        List_KQXS5.add(kqxs_module5);
                    }
                }


            } catch (
                    IOException e) {
                e.printStackTrace();
            }
            return List_KQXS5;
        }

        @Override
        protected void onPostExecute(ArrayList<KQXS_Giai5_Module> kqxs_modules5) {
            super.onPostExecute(kqxs_modules5);
            KQXS_Giai5_Adapter kqxs_giai5Adapter = new KQXS_Giai5_Adapter(kqxs_modules5, Lottery_MB.this);
            RcGiai5.setAdapter(kqxs_giai5Adapter);
        }

    }

    private class DownloadTask6 extends AsyncTask<String, Void, ArrayList<KQXS_Giai7_Module>> {


        @Override
        protected ArrayList<KQXS_Giai7_Module> doInBackground(String... strings) {

            Document document = null;

            ArrayList<KQXS_Giai7_Module> List_KQXS7 = new ArrayList<>();
            try {
                document = (Document) Jsoup.connect(strings[0]).get();

                if (!document.toString().isEmpty()) {

                    Elements subGIAI = document.select("div#noidung>div>div#bangkqxsmien>div.box_kqxs>div.content>table.bkqmiennam>tbody>tr>td>table>tbody>tr>td>table.bkqtinhmienbac>tbody>tr>td.giai7>div");

                    for (Element element2 : subGIAI) {
                        KQXS_Giai7_Module kqxs_module7 = new KQXS_Giai7_Module();
                        Element PrizeSubject = element2.getElementsByTag("div").first();
                        if (!PrizeSubject.toString().isEmpty()) {
                            kqxs_module7.setGIAITHUONG(PrizeSubject.text());
                        }
                        List_KQXS7.add(kqxs_module7);
                    }
                }


            } catch (
                    IOException e) {
                e.printStackTrace();
            }
            return List_KQXS7;
        }

        @Override
        protected void onPostExecute(ArrayList<KQXS_Giai7_Module> kqxs_modules7) {
            super.onPostExecute(kqxs_modules7);
            KQXS_Giai7_Adapter kqxs_giai7Adapter = new KQXS_Giai7_Adapter(kqxs_modules7, Lottery_MB.this);
            RcGiai7.setAdapter(kqxs_giai7Adapter);
        }

    }


}