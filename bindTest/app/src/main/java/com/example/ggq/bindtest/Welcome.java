package com.example.ggq.bindtest;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Welcome extends AppCompatActivity {

    ArrayList<View> listview = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pageview);
        intiback();
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return listview.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(listview.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(listview.get(position));
                return listview.get(position);
            }
        });
    }
    public void intiback()
    {
        listview = new ArrayList<>();
        View view ;
        view = LayoutInflater.from(this).inflate(R.layout.viewpageitem,null);
        view.findViewById(R.id.imageView).setBackgroundResource(R.drawable.city);
        listview.add(view);
        view = LayoutInflater.from(this).inflate(R.layout.viewpageitem,null);
        view.findViewById(R.id.imageView).setBackgroundResource(R.drawable.hotpot);
        listview.add(view);
    }
}
