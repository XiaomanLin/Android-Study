package com.example.myxmldesign1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    RadioButton r1 = null;
    RadioButton r2 = null;
    RadioButton r3 = null;
    RadioButton r4 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获得单选按钮
        r1 = (RadioButton) findViewById(R.id.r1);
        r2 = (RadioButton) findViewById(R.id.r2);
        r3 = (RadioButton) findViewById(R.id.r3);
        r4 = (RadioButton) findViewById(R.id.r4);
        r1.setClickable(true);
        // 监听单选按钮
        //radioGroup.setOnCheckedChangeListener(mChangeRadio);
        Button botton1 =(Button)findViewById(R.id.sure);
        Button botton2 =(Button)findViewById(R.id.cancel);
    }
}