package com.example.ggq.mysqlfarcon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ggq.mysqlfarcon.Util.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    BaseDao baseDao = new BaseDao();
    TextView t1 = null;
    TextView t2 = null;
    Button button = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (TextView) findViewById(R.id.textView);
        t2 = (TextView) findViewById(R.id.textView2);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new oos());
    }

    class oos implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            ResultSet resultSet = null;
            String sql = "select * from table1 where id=?";
            Object[] para = {"2"};
            try {
                    resultSet = baseDao.util.executeQuery(sql,para);
                    if (resultSet.next()) {
                        t1.setText(resultSet.getString(0));
                        t2.setText(resultSet.getString(1));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
            }

        }
    }
}