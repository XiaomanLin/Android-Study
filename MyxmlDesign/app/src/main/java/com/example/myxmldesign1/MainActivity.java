package com.example.myxmldesign1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {
    RadioButton r1 = null;
    RadioButton r2 = null;
    RadioButton r3 = null;
    RadioButton r4 = null;
    RadioGroup radioGroup = null;
    RadioButton currentRadioButton = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获得单选按钮
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        r1 = (RadioButton) findViewById(R.id.r1);
        r2 = (RadioButton) findViewById(R.id.r2);
        r3 = (RadioButton) findViewById(R.id.r3);
        r4 = (RadioButton) findViewById(R.id.r4);
        r1.setClickable(true);
        // 监听单选按钮
        radioGroup.setOnCheckedChangeListener(mChangeRadio);
        Button botton1 =(Button)findViewById(R.id.sure);
        Button botton2 =(Button)findViewById(R.id.cancel);
        botton1.setOnClickListener(new botton1());//为button设置监听事件
        botton2.setOnClickListener(new botton2());
    }
    class botton1 implements OnClickListener {
        @Override
        public void onClick(View v) {
            if (currentRadioButton.getText().equals("2")) {
                setTitle("你选择的答案是：是正确的！");
            } else {
                setTitle("你选择的答案是:是错误的！");
            }
        }
    }
    class botton2 implements OnClickListener {
        /*
        (API中查找，android.widget public class RadioGroup
        void clearCheck()
        Clears the selection.
         */
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            radioGroup.clearCheck();
            setTitle("");
        }
    }
    private RadioGroup.OnCheckedChangeListener mChangeRadio = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == r1.getId()) {
                // 获得按钮的名称
                currentRadioButton = r1;
            } else if (checkedId == r2.getId()) {
                currentRadioButton = r2;
            } else if (checkedId == r3.getId()) {
                currentRadioButton = r3;
            } else if (checkedId == r4.getId()) {
                currentRadioButton = r4;
            }
        }
    };
}