package com.example.a2048;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by 小木木曼 on 2017/5/5.
 * 初始化2048游戏中的方块
 */

public class Card extends FrameLayout{

    public static int width;

    private TextView label;

    //设置游戏界面
    public Card(Context context) {
        super(context);

        LayoutParams lp = null;

        View background = new View(getContext());
        lp = new LayoutParams(-1, -1);
        lp.setMargins(10, 10, 0, 0);
        background.setBackgroundColor(Color.WHITE);
        addView(background, lp);

        label = new TextView(getContext());
        label.setTextSize(28);
        label.setTextColor(Color.RED);
        label.setGravity(Gravity.CENTER);

        lp = new LayoutParams(-1, -1);
        lp.setMargins(10, 10, 0, 0); //设置card的间距
        addView(label, lp);

        setNum(0);
    }


    private int num = 0;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;

        if (num<=0) {
            label.setText("");
        }else{
            label.setText(String.valueOf(num));//将num转换成字符串

        }

        switch (num) {
            case 0:
                label.setBackgroundColor(Color.parseColor("#D1D1D1"));
                break;
            case 2:
                label.setBackgroundColor(Color.parseColor("#F6CACA"));
                break;
            case 4:
                label.setBackgroundColor(Color.parseColor("#FDE1B6"));
                break;
            case 8:
                label.setBackgroundColor(Color.parseColor("#FFC076"));
                break;
            case 16:
                label.setBackgroundColor(Color.parseColor("#FFA275"));
                break;
            case 32:
                label.setBackgroundColor(Color.parseColor("#FC776D"));
                break;
            case 64:
                label.setBackgroundColor(Color.parseColor("#FFD700"));
                break;
            case 128:
                label.setBackgroundColor(Color.parseColor("#C4D1FB"));
                break;
            case 256:
                label.setBackgroundColor(Color.parseColor("#8181E2"));
                break;
            case 512:
                label.setBackgroundColor(Color.parseColor("#5555DF"));
                break;
            case 1024:
                label.setBackgroundColor(Color.parseColor("#A5F8AB"));
                break;
            case 2048:
                label.setBackgroundColor(Color.parseColor("#FDFF00"));
                break;
            default:
                label.setBackgroundColor(Color.parseColor("#D1D1D1"));
                break;
        }
    }

    public boolean equals(Card another) {
        return getNum()==another.getNum();
    }

    public TextView getLabel() {
        return label;
    }

    //设置动画效果
    public void addScaleAnimation(){
        //前四个参数表示从原来大小的10%增大到100%，后四个参数是为确定“中心点”
        ScaleAnimation sa = new ScaleAnimation(0.1f, 1, 0.1f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //设置动画执行的时间（单位：毫秒）
        sa.setDuration(1000);
        setAnimation(null);
        getLabel().startAnimation(sa);
    }
}
