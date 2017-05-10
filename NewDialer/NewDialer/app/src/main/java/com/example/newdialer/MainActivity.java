package com.example.newdialer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.widget.Button;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button=(Button)findViewById(R.id.button1);
        button.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View b){
                Intent I=new Intent(Intent.ACTION_DIAL,Uri.parse("tel://114"));
                startActivity(I);
            }
        }
        );
    }
}
