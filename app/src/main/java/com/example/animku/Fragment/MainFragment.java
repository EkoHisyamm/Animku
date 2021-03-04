package com.example.animku.Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.animku.Fragment.GenreFragment;
import com.example.animku.Fragment.HomeFragment;
import com.example.animku.GetAnime;
import com.example.animku.Model.AnimeModel;
import com.example.animku.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainFragment extends AppCompatActivity {

    boolean doubleBackPressOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new HomeFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.navHome:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navGenre:
                    selectedFragment = new GenreFragment();
                    break;
                case R.id.navOngoing:
                    selectedFragment = new OngoingFragment();
                    break;
                case R.id.navBookmark:
                    selectedFragment = new BookmarkFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, selectedFragment).commit();

            return true;
        }
    };

    @Override
    public void onBackPressed() {
        if (doubleBackPressOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackPressOnce = true;
        Toast.makeText(this, "Klik back lagi untuk keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackPressOnce=false;
            }
        }, 3000);

        /*new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
                        realm = Realm.getInstance(configuration);
                        realm.beginTransaction();
                        realm.deleteAll();
                        realm.commitTransaction();
                        Intent a = new Intent(Intent.ACTION_MAIN);
                        a.addCategory(Intent.CATEGORY_HOME);
                        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(a);
                    }
                }).create().show();*/
    }
}