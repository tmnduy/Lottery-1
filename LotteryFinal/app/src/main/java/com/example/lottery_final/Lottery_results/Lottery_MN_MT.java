package com.example.lottery_final.Lottery_results;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lottery_final.Adapter.KQXS_Giai3_Adapter;
import com.example.lottery_final.Adapter.KQXS_Giai4_Adapter;
import com.example.lottery_final.Adapter.KQXS_Giai6_Adapter;
import com.example.lottery_final.Module.KQXS_Giai3_Module;
import com.example.lottery_final.Module.KQXS_Giai4_Module;
import com.example.lottery_final.Module.KQXS_Giai6_Module;
import com.example.lottery_final.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static androidx.recyclerview.widget.RecyclerView.*;

public class Lottery_MN_MT extends AppCompatActivity {
    private ImageView ImgBack;
    private RecyclerView RcGiai3, RcGiai4, RcGiai6;
    private static TextView Province1, TVThu, TVNgay, GIAIDB_MT_MN, GIAI1_MT_MN, GIAI2_MT_MN, GIAI5_MT_MN, GIAI7_MT_MN, GIAI8_MT_MN, TVCode;
    private static KQXS_Giai3_Adapter kqxs_giai3Adapter;
    private static KQXS_Giai4_Adapter kqxs_giai4Adapter;
    private static KQXS_Giai6_Adapter kqxs_giai6Adapter;
    String URL_KQXS_TINH = "https://www.minhngoc.net/xo-so-mien-trung.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kqxs_mn_layout);


        Intent intent = getIntent();
        String Code = intent.getStringExtra("Code");

        Province1 = (TextView) findViewById(R.id.TvProvince1);


        GIAIDB_MT_MN = (TextView) findViewById(R.id.TVGiaiDB_MT_MN);
        GIAI1_MT_MN = (TextView) findViewById(R.id.TVGiai1);
        GIAI2_MT_MN = (TextView) findViewById(R.id.TVGiai2);
        GIAI5_MT_MN = (TextView) findViewById(R.id.TVGiai5);
        GIAI7_MT_MN = (TextView) findViewById(R.id.TVGiai7);
        GIAI8_MT_MN = (TextView) findViewById(R.id.TVGiai8);


        RcGiai3 = (RecyclerView) findViewById(R.id.RcGiai3);
        RcGiai4 = (RecyclerView) findViewById(R.id.RcGiai4);
        RcGiai6 = (RecyclerView) findViewById(R.id.RcGiai6);


        TVThu = (TextView) findViewById(R.id.TVThu1);
        TVNgay = (TextView) findViewById(R.id.TVNgay);
        TVCode = (TextView) findViewById(R.id.TvCode);


        configRecyclerView();


        if (Code.equals("MT")) {
            String KQXS_MT = intent.getStringExtra("KQXS_MT");

            if (KQXS_MT.equals("Thừa T. Huế")) {
                URL_KQXS_TINH = "https://www.minhngoc.net/xo-so-mien-trung/thua-thien-hue.html";
            } else {

                KQXS_MT = VNCharacterUtils.removeAccent(KQXS_MT);
                KQXS_MT = KQXS_MT.replaceAll("\\s+", "-");
                URL_KQXS_TINH = "https://www.minhngoc.net/xo-so-mien-trung/" + KQXS_MT + ".html";
            }
        }
        if (Code.equals("MN")) {
            String KQXS_MN = intent.getStringExtra("KQXS_MN");
            if (KQXS_MN.equals("TP. HCM")) {
                URL_KQXS_TINH = "https://www.minhngoc.net/xo-so-mien-nam/tp-hcm.html";
            } else {
                KQXS_MN = VNCharacterUtils.removeAccent(KQXS_MN);
                KQXS_MN = KQXS_MN.replaceAll("\\s+", "-");
                URL_KQXS_TINH = "https://www.minhngoc.net/xo-so-mien-nam/" + KQXS_MN + ".html";
            }
        }


        new DownloadTask().execute(URL_KQXS_TINH);
        new DownloadTask2().execute(URL_KQXS_TINH);
        new DownloadTask3().execute(URL_KQXS_TINH);


        ImgBack = (ImageView) findViewById(R.id.back_arrow);
        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void configRecyclerView() {
        LayoutManager layoutManager3 = new GridLayoutManager(this, 2);
        RcGiai3.hasFixedSize();
        RcGiai3.setLayoutManager(layoutManager3);
        LayoutManager layoutManager4 = new GridLayoutManager(this, 2);
        RcGiai4.hasFixedSize();
        RcGiai4.setLayoutManager(layoutManager4);
        LayoutManager layoutManager6 = new GridLayoutManager(this, 3);
        RcGiai6.hasFixedSize();
        RcGiai6.setLayoutManager(layoutManager6);
    }


    private class DownloadTask extends AsyncTask<String, Void, ArrayList<KQXS_Giai3_Module>> {


        @Override
        protected ArrayList<KQXS_Giai3_Module> doInBackground(String... strings) {

            Document document = null;
            ArrayList<KQXS_Giai3_Module> List_KQXS = new ArrayList<>();

            try {

                document = (Document) Jsoup.connect(strings[0]).get();

                if (!document.toString().isEmpty()) {

                    String Giai[] = {"1", "2", "3", "5", "7", "8"};

                    Element subTitleProvince = document.select("div#noidung>h1").first();
                    Element subTitleThu = document.select("div#noidung>div>div.box_kqxs>div.content>table.bkqtinhmiennam>tbody>tr>td>div.thu>a").first();
                    Element subTitleNgay = document.select("div#noidung>div>div.box_kqxs>div.content>table.bkqtinhmiennam>tbody>tr>td>div.ngay>a").first();
                    Element subCode = document.select("div#noidung>div>div.box_kqxs>div.content>table.bkqtinhmiennam>tbody>tr>td>span.loaive").first();
                    Element subGIAIDB = document.select("div#noidung>div>div.box_kqxs>div.content>table.bkqtinhmiennam>tbody>tr>td>table.box_kqxs_content>tbody>tr>td.giaidb>div").first();


                    if (!subTitleProvince.toString().isEmpty()) {
                        Province1.setText(subTitleProvince.text());
                    }
                    if (!subTitleThu.toString().isEmpty()) {
                        TVThu.setText(subTitleThu.text());
                    }
                    if (!subTitleNgay.toString().isEmpty()) {
                        TVNgay.setText(subTitleNgay.text());
                    }
                    if (!subCode.toString().isEmpty()) {
                        TVCode.setText(subCode.text());
                    }
                    if (!subGIAIDB.toString().isEmpty()) {
                        GIAIDB_MT_MN.setText(subGIAIDB.text());
                    }


                    for (int i = 0; i < Giai.length; i++) {
                        Elements subGIAI = document.select("div#noidung>div>div.box_kqxs>div.content>table.bkqtinhmiennam>tbody>tr>td>table.box_kqxs_content>tbody>tr>td.giai" + Giai[i] + ">div");

                        for (Element element2 : subGIAI) {
                            KQXS_Giai3_Module KQXSGiai3_module = new KQXS_Giai3_Module();
                            Element PrizeSubject = element2.getElementsByTag("div").first();
                            if (Giai[i].equals("1")) {
                                if (!PrizeSubject.toString().isEmpty()) {
                                    String Prize1 = PrizeSubject.text();
                                    GIAI1_MT_MN.setText(Prize1);
                                }
                            }
                            if (Giai[i].equals("2")) {
                                if (!PrizeSubject.toString().isEmpty()) {
                                    String Prize2 = PrizeSubject.text();
                                    GIAI2_MT_MN.setText(Prize2);
                                }
                            }
                            if (Giai[i].equals("5")) {
                                if (!PrizeSubject.toString().isEmpty()) {
                                    String Prize5 = PrizeSubject.text();
                                    GIAI5_MT_MN.setText(Prize5);
                                }
                            }
                            if (Giai[i].equals("7")) {
                                if (!PrizeSubject.toString().isEmpty()) {
                                    String Prize7 = PrizeSubject.text();
                                    GIAI7_MT_MN.setText(Prize7);
                                }
                            }
                            if (Giai[i].equals("8")) {
                                if (!PrizeSubject.toString().isEmpty()) {
                                    String Prize8 = PrizeSubject.text();
                                    GIAI8_MT_MN.setText(Prize8);
                                }
                            }
                            if (Giai[i].equals("3")) {
                                if (!PrizeSubject.toString().isEmpty()) {
                                    String Prize3 = PrizeSubject.text();
                                    KQXSGiai3_module.setGIAITHUONG(Prize3);
                                }
                                List_KQXS.add(KQXSGiai3_module);
                            }
                        }

                    }

                }

            } catch (
                    IOException e) {
                e.printStackTrace();
            }
            return List_KQXS;
        }


        @Override
        protected void onPostExecute(ArrayList<KQXS_Giai3_Module> KQXSGiai3_modules) {
            super.onPostExecute(KQXSGiai3_modules);
            kqxs_giai3Adapter = new KQXS_Giai3_Adapter(KQXSGiai3_modules, Lottery_MN_MT.this);
            RcGiai3.setAdapter(kqxs_giai3Adapter);
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

                    Elements subGIAI = document.select("div#noidung>div>div.box_kqxs>div.content>table.bkqtinhmiennam>tbody>tr>td>table.box_kqxs_content>tbody>tr>td.giai4>div");

                    for (Element element2 : subGIAI) {
                        KQXS_Giai4_Module KQXSGiai4Module = new KQXS_Giai4_Module();
                        Element PrizeSubject = element2.getElementsByTag("div").first();
                        if (!PrizeSubject.toString().isEmpty()) {
                            String Prize4 = PrizeSubject.text();
                            KQXSGiai4Module.setGIAITHUONG(Prize4);
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
            kqxs_giai4Adapter = new KQXS_Giai4_Adapter(kqxs_modules4, Lottery_MN_MT.this);
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

                    Elements subGIAI = document.select("div#noidung>div>div.box_kqxs>div.content>table.bkqtinhmiennam>tbody>tr>td>table.box_kqxs_content>tbody>tr>td.giai6>div");

                    for (Element element2 : subGIAI) {
                        KQXS_Giai6_Module kqxs_module6 = new KQXS_Giai6_Module();
                        Element PrizeSubject = element2.getElementsByTag("div").first();
                        if (!PrizeSubject.toString().isEmpty()) {
                            String Prize6 = PrizeSubject.text();
                            kqxs_module6.setGIAITHUONG(Prize6);
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
            kqxs_giai6Adapter = new KQXS_Giai6_Adapter(kqxs_modules6, Lottery_MN_MT.this);
            RcGiai6.setAdapter(kqxs_giai6Adapter);
        }

    }


    public static class VNCharacterUtils {

        private static final char[] SOURCE_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É',
                'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â',
                'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý',
                'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ',
                'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ',
                'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ',
                'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ',
                'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
                'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ',
                'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ',
                'ữ', 'Ự', 'ự',};

        private static final char[] DESTINATION_CHARACTERS = {'A', 'A', 'A', 'A', 'E',
                'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a',
                'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u',
                'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u',
                'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
                'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e',
                'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
                'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
                'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
                'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
                'U', 'u', 'U', 'u',};

        public static char removeAccent(char ch) {
            int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
            if (index >= 0) {
                ch = DESTINATION_CHARACTERS[index];
            }
            return ch;
        }

        public static String removeAccent(String str) {
            StringBuilder sb = new StringBuilder(str);
            for (int i = 0; i < sb.length(); i++) {
                sb.setCharAt(i, removeAccent(sb.charAt(i)));
            }
            return sb.toString();
        }
    }


}
