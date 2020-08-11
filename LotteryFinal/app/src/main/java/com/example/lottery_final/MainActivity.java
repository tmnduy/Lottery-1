package com.example.lottery_final;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lottery_final.Lottery_results.Lottery;
import com.example.lottery_final.Lottery_results.Lottery_MN_MT;
import com.example.lottery_final.Lottery_results.Lottery_Vietlott;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    LinearLayout KQXS_MB, KQXS_MT, KQXS_MN, KQXS_MEGA, KQXS_POWER;
    final String URL_KQXS_MN = "https://www.minhngoc.net/xo-so-mien-nam.html";
    final String URL_KQXS_MT = "https://www.minhngoc.net/xo-so-mien-trung.html";
    String ProvinceTitle = null;

    String[] MienTrung = {};
    String[] MienNam = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        KQXS_MB = (LinearLayout) findViewById(R.id.KQXS_MB);
        KQXS_MT = (LinearLayout) findViewById(R.id.KQXS_MT);
        KQXS_MN = (LinearLayout) findViewById(R.id.KQXS_MN);
        KQXS_MEGA = (LinearLayout) findViewById(R.id.KQXS_MEGA);
        KQXS_POWER = (LinearLayout) findViewById(R.id.KQXS_POWER655);


        KQXS_MB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Lottery.class);
                intent.putExtra("KQXS", "MB");
                startActivity(intent);
            }
        });
        new DownloadTask().execute(URL_KQXS_MT);
        new DownloadTask().execute(URL_KQXS_MN);

        KQXS_MT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Chọn tỉnh bạn muốn xem kết quả");
                MienTrung = DownloadTask.Aray.toArray(new String[0]);
                alertDialog.setItems(MienTrung, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mientrung = MienTrung[which];
                        Log.e("mientrung", mientrung);
                        dialog.dismiss();
                        Intent intent = new Intent(MainActivity.this, Lottery_MN_MT.class);
                        intent.putExtra("KQXS_MT", mientrung);
                        intent.putExtra("Code", "MT");
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, mientrung, Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.setCancelable(true);
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(MainActivity.this, "You choose No button",
                                Toast.LENGTH_SHORT).show();
                        //  Cancel
                        dialog.cancel();
                    }
                });

                // Create AlertDialog:
                AlertDialog alert = alertDialog.create();
                alert.show();


            }
        });


        KQXS_MN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Chọn tỉnh bạn muốn xem kết quả");
                MienNam = DownloadTask.Aray.toArray(new String[0]);
                alertDialog.setItems(MienNam, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String miennam = MienNam[which];
                        dialog.dismiss();
                        Intent intent = new Intent(MainActivity.this, Lottery_MN_MT.class);
                        intent.putExtra("KQXS_MN", miennam);
                        intent.putExtra("Code", "MN");
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, miennam, Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.setCancelable(true);
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(MainActivity.this, "You choose No button",
                                Toast.LENGTH_SHORT).show();
                        //  Cancel
                        dialog.cancel();
                    }
                });

                // Create AlertDialog:
                AlertDialog alert = alertDialog.create();
                alert.show();

            }
        });
        KQXS_MEGA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Lottery_Vietlott.class);
                intent.putExtra("KQXS", "MEGA");
                startActivity(intent);
            }
        });
        KQXS_POWER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Lottery_Vietlott.class);
                intent.putExtra("KQXS", "POWER");
                startActivity(intent);
            }
        });
    }

    public static class DownloadTask extends AsyncTask<String, Void, String> {
        private static ArrayList<String> Aray = new ArrayList<>();


        private static final String TAG = "DownloadTask";
        private String ProvinceTitle;

        @Override
        protected String doInBackground(String... strings) {

            Document document = null;
            try {
                document = (Document) Jsoup.connect(strings[0]).get();
                Log.e("doc", document.text());
                if (document != null) {

                    Elements subTitleProvince = document.select("div#noidung>div>div#bangkqxsmien>div.box_kqxs>div.content>table.bkqmiennam>tbody>tr>td>table>tbody>tr>td>table>tbody>tr>td.tinh");
                    Log.e("1a", subTitleProvince.toString());

                    for (Element element : subTitleProvince) {
                        Element ProvinceSubject = element.getElementsByTag("a").first();
                        ProvinceTitle = ProvinceSubject.text();
                        Aray.add(ProvinceTitle);
                        Log.e("loa", ProvinceTitle);
                    }
                    Log.e("aray", Aray.toString());

                }


            } catch (
                    IOException e) {
                e.printStackTrace();
            }

            return ProvinceTitle;
        }

//        protected void onPostExecute(ArrayList<RequireWebModule> requireWebModules) {
//            super.onPostExecute(requireWebModules);
//            //Setup data recyclerView
//            requireWeb_adapter = new RequireWeb_Adapter(Lottery.this, requireWebModules);
//            recyclerView.setAdapter(requireWeb_adapter);
//        }

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