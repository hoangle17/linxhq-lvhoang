package com.example.videodemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    public static String API_KEY = "AIzaSyDOkP_6oEdK1cnmtzMr2I20_sn6YhSu0sA";

    String url = "https://www.googleapis.com/youtube/v3/videos?part=contentDetails,snippet&id=ur6I5m2nTvk,W6NZfCO5SIk,eIrMbAQSU34,bjFvcFjJpE0,vLnPwxZdW4Y,KJgsSFOSQv0,GhQdlIFylQ8,HXV3zeQKqGY,GZvSYJDk-us&key=AIzaSyDOkP_6oEdK1cnmtzMr2I20_sn6YhSu0sA&maxResults=9";
    ListView listViewVideo;
    ArrayList<VideoYoutube> arrayVideo;
    VideoAdapter adapter;
    Toolbar toolbar;
    ArrayList<String> listDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
        init();
    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new SearchUser(), "Video ");
        mainViewPagerAdapter.addFragment(new FragmentAccount(), "Account");


        viewPager.setAdapter((mainViewPagerAdapter));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.iconmv);
        tabLayout.getTabAt(1).setIcon(R.drawable.person);
    }
}