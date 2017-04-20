package com.example.myxmldesign1;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;


public class otherActivity extends MainActivity{
    private Intent intent;
    private Bundle bundle;

    @Override
    public void onCreate(Bundle savedInstanceState){
        //TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);
        Bundle bundle = this.getIntent().getExtras();
        String ans = bundle.getString("ans");
        String sexText = "";
        if (ans.equals("2")){
            sexText = "正确";
        } else {
            sexText = "错误";
        }
        TextView tv1 = (TextView) findViewById(R.id.text1);
        tv1.setText("您选择的答案是：" + sexText);
        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick (View v){
            // TODO Auto-generated method stub
			/* 返回result 回上一个activity */
            otherActivity.this.setResult(RESULT_OK, intent);
			/* 结束这个activity */
            otherActivity.this.finish();
        }
        });
    }
}
