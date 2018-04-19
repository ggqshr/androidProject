package com.example.ggq.bindtest;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ggq.bindtest.databinding.ActivityDataBindTestBinding;
import com.example.ggq.bindtest.util.user;

import java.util.ArrayList;

public class s extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = (RecyclerView) findViewById(R.id.recycle);
    }

    static class myad extends RecyclerView.Adapter<myad.ViewHolder> {
        private ArrayList<user> mdate;
        private Context context;

        public myad(Context context, ArrayList<user> muser) {
            this.context = context;
            this.mdate = muser;
        }

        @Override
        public myad.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ActivityDataBindTestBinding ab = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.activity_data_bind_test, parent, false
            );
            ViewHolder viewHolder = new ViewHolder(ab.getRoot());
            viewHolder.setBinding(ab);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(myad.ViewHolder holder, int position) {
            user user = mdate.get(position);
            Glide.with(context)
                    .load("http://192.168.191.1:8080/Hotpotserver/img/118474-106.jpg")
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.binding.imageView);
            holder.binding.setUser(user);
            holder.binding.executePendingBindings();
        }

        @Override
        public int getItemCount() {
            return mdate.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private ActivityDataBindTestBinding binding;

            ViewHolder(View v) {
                super(v);
            }

            public ActivityDataBindTestBinding getBinding() {
                return binding;
            }

            void setBinding(ActivityDataBindTestBinding binding) {
                this.binding = binding;
            }
        }
    }
}
