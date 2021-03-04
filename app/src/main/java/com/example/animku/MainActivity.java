package com.example.animku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.animku.Fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            int x = 1000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                GetAnime getAnime = new GetAnime();
                getAnime.tes();
                Intent intent = new Intent(MainActivity.this, MainFragment.class);
                startActivity(intent);
                finish();
            }
        }, x);

    }
}