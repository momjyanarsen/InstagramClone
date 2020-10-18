package com.example.instagramclone.Profile;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.instagramclone.R;
import com.example.instagramclone.Utils.BottomNavigationViewHelper;
import com.example.instagramclone.Utils.GridImageAdapter;
import com.example.instagramclone.Utils.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUM = 4;
    private static final int NUM_GRID_COLUMNS = 3;

    private Context mContext = ProfileActivity.this;

    private ProgressBar mprogressBar;
    private ImageView profilePhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.i("INFO", "onCreat started");

        setupBottomNavigationView();
        setupToolbar();
        setupActivityWidgets();
        setProfileImage();

        tempGridSetup();
    }

    private void tempGridSetup() {
        ArrayList<String> imgURLs = new ArrayList<>();

        imgURLs.add("https://0.academia-photos.com/7002858/2654574/15364492/s200_lusine.kosakyan.png");
        imgURLs.add("https://0.academia-photos.com/7002858/2654574/15364492/s200_lusine.kosakyan.png");
        imgURLs.add("https://0.academia-photos.com/7002858/2654574/15364492/s200_lusine.kosakyan.png");
        imgURLs.add("https://0.academia-photos.com/7002858/2654574/15364492/s200_lusine.kosakyan.png");
        imgURLs.add("https://0.academia-photos.com/7002858/2654574/15364492/s200_lusine.kosakyan.png");
        imgURLs.add("https://0.academia-photos.com/7002858/2654574/15364492/s200_lusine.kosakyan.png");
        imgURLs.add("https://0.academia-photos.com/7002858/2654574/15364492/s200_lusine.kosakyan.png");
        imgURLs.add("https://0.academia-photos.com/7002858/2654574/15364492/s200_lusine.kosakyan.png");
        imgURLs.add("https://0.academia-photos.com/7002858/2654574/15364492/s200_lusine.kosakyan.png");
        imgURLs.add("https://0.academia-photos.com/7002858/2654574/15364492/s200_lusine.kosakyan.png");
        imgURLs.add("https://0.academia-photos.com/7002858/2654574/15364492/s200_lusine.kosakyan.png");
        imgURLs.add("https://0.academia-photos.com/7002858/2654574/15364492/s200_lusine.kosakyan.png");
        imgURLs.add("https://0.academia-photos.com/7002858/2654574/15364492/s200_lusine.kosakyan.png");
        imgURLs.add("https://0.academia-photos.com/7002858/2654574/15364492/s200_lusine.kosakyan.png");
        imgURLs.add("https://0.academia-photos.com/7002858/2654574/15364492/s200_lusine.kosakyan.png");
        imgURLs.add("https://0.academia-photos.com/7002858/2654574/15364492/s200_lusine.kosakyan.png");
        imgURLs.add("https://0.academia-photos.com/7002858/2654574/15364492/s200_lusine.kosakyan.png");
        imgURLs.add("https://0.academia-photos.com/7002858/2654574/15364492/s200_lusine.kosakyan.png");
        imgURLs.add("https://0.academia-photos.com/7002858/2654574/15364492/s200_lusine.kosakyan.png");

        imgURLs.add("https://mojautomobil.net/wp-content/uploads/2017/12/ANDROID.png");
        imgURLs.add("https://mojautomobil.net/wp-content/uploads/2017/12/ANDROID.png");
        imgURLs.add("https://mojautomobil.net/wp-content/uploads/2017/12/ANDROID.png");
        imgURLs.add("https://mojautomobil.net/wp-content/uploads/2017/12/ANDROID.png");
        imgURLs.add("https://mojautomobil.net/wp-content/uploads/2017/12/ANDROID.png");
        imgURLs.add("https://mojautomobil.net/wp-content/uploads/2017/12/ANDROID.png");
        imgURLs.add("https://mojautomobil.net/wp-content/uploads/2017/12/ANDROID.png");
        imgURLs.add("https://3v6x691yvn532gp2411ezrib-wpengine.netdna-ssl.com/wp-content/uploads/2019/05/imagetext01.jpg");

        setupImageGrid(imgURLs);
    }

    private void setupImageGrid(ArrayList<String> imgURLs) {
        GridView gridView = findViewById(R.id.gridView);

        int gridWidth = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth/NUM_GRID_COLUMNS;
        gridView.setColumnWidth(imageWidth);


        GridImageAdapter adapter = new GridImageAdapter(mContext, R.layout.layout_grid_imageview, "", imgURLs);
        gridView.setAdapter(adapter);
    }

    private void setProfileImage() {
        Log.d(TAG, "setProfileImage, setting profile photo.");
        String imgURL = "https://mojautomobil.net/wp-content/uploads/2017/12/ANDROID.png";
        UniversalImageLoader.setImage(imgURL, profilePhoto, mprogressBar, "");
    }

    private void setupActivityWidgets() {
        mprogressBar = (ProgressBar) findViewById(R.id.profileProgressBar);
        mprogressBar.setVisibility(View.GONE);
        profilePhoto = (ImageView) findViewById(R.id.profile_photo);
    }

    /**
     * Responsible for setting up the profile toolbar
     */
    private void setupToolbar() {
        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);

        ImageView profileMenu = (ImageView) findViewById(R.id.profileMenu);
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick navigating to account settings");
                Intent intent = new Intent(mContext, AccountSettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * BottomNavigationView setup
     */
    private void setupBottomNavigationView() {
        Log.i("Info", "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
