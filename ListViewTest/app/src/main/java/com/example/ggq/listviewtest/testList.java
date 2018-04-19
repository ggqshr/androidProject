package com.example.ggq.listviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class testList extends AppCompatActivity {

    private ArrayList<HashMap<String, Object>> value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListView listView = (ListView) findViewById(R.id.listview);
        value = getdate();
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, value, R.layout.item, new String[]{"t1", "t2", "img"},
                new int[]{R.id.textview1, R.id.textview2, R.id.imageView});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view.findViewById(R.id.textview1);
                String text = (String) textView.getText();
                Toasty.info(testList.this, text, Toast.LENGTH_SHORT, true).show();
            }
        });
    }

    public ArrayList<HashMap<String, Object>> getdate() {
        ArrayList<HashMap<String, Object>> v = new ArrayList<>();
        HashMap<String, Object> map;
        map = new HashMap<>();
        map.put("t1", "11");
        map.put("t2", "t");
        map.put("img", R.mipmap.ic_launcher1);
        v.add(map);

        map = new HashMap<>();
        map.put("t1", "22");
        map.put("t2", "y");
        map.put("img", R.mipmap.ic_launcher2);
        v.add(map);

        map = new HashMap<>();
        map.put("t1", "33");
        map.put("t2", "u");
        map.put("img", R.mipmap.ic_launcher3);
        v.add(map);

        return v;
    }
}
