package com.example.ggq.my_swy;

import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.widget.TextView;
    import android.widget.Button;

    import org.w3c.dom.Text;

    public class MainActivity extends AppCompatActivity {
        private TextView mytextview;
        int i=0;
        int life;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("@@@@@@@@@@","你好");
        life=0;
        mytextview=(TextView) findViewById(R.id.life);
        //mytextview.setText(life);
        Button button1= (Button)findViewById(R.id.button1);
        Button button2=(Button)findViewById(R.id.button2);
        mytextview.setText("预备开始！");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                life--;
                if(life<=-5){
                    mytextview.setText("成功杀死！");
                    life=0;
                }else{
                    mytextview.setText("血量"+life);
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                life++;
                if(life>=5){
                    mytextview.setText("满血复活！！");
                    life=0;
                }else{
                    mytextview.setText("血量"+life);
                }
            }
        });
    }
}
