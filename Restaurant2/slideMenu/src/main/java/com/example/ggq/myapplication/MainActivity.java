package com.example.ggq.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ggq.myapplication.View.SlideView;
import com.yalantis.phoenix.PullToRefreshView;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshView mPullToRefreshView;
    private SlideView slidingMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slidingMenu = (SlideView) findViewById(R.id.id_menu);
        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }
    public void toggleMenu(View v)
    {
        slidingMenu.toggle();
    }
}
