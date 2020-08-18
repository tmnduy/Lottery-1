package com.example.lottery_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Poster extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poster);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //do something
                Intent intent = new Intent(Poster.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000 );
    }
}