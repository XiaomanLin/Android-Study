package com.example.a2048;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小木木曼 on 2017/5/5.
 */

public class GameView extends LinearLayout{

    private final int LINES = 4;
    private Card[][] cardsMap = new Card[LINES][LINES];
    private List<Point> emptyPoints = new ArrayList<Point>();
    private MainActivity.Score score;


    public GameView(Context context) {
        super(context);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
    }

    //初始化界面
    private void initGameView(){
        setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(Color.WHITE);

        setOnTouchListener(new View.OnTouchListener(){

            private float startX,startY,offsetX,offsetY;

            //判断手势滑动方向
            @Override
            public boolean onTouch(View v, MotionEvent event){

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN: //按下
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:  //离开
                        offsetX = event.getX()-startX;
                        offsetY = event.getY()-startY;

                        if (Math.abs(offsetX) > Math.abs(offsetY)){
                            if (offsetX < -5){
                                swipeLeft();
                            }else if (offsetX > 5){
                                swipeRight();
                            }
                        }else {
                            if (offsetY < -5){
                                swipeUp();
                            }else if (offsetY > 5){
                                swipeDown();
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }

    //一开始布局发生变化，调用以下函数
    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight){
        super.onSizeChanged(width, height, oldWidth, oldHeight);

        Card.width = (Math.min(width, height) - 10)/LINES;

        addCards();
        startGame();
    }

    private void addCards(){
        Card c;
        LinearLayout line;
        LinearLayout.LayoutParams lineLp;

        for (int y = 0; y < LINES; y++){
            line = new LinearLayout(getContext());
            lineLp = new LinearLayout.LayoutParams(-1, Card.width);
            addView(line, lineLp);

            for (int x = 0; x < LINES; x++) {
                c = new Card(getContext());
                line.addView(c, Card.width, Card.width);
                cardsMap[x][y] = c;
            }
        }
    }

    public void startGame(){
        for (int y = 0; y < LINES; y++){
            for (int x = 0; x < LINES; x++){
                cardsMap[x][y].setNum(0);
            }
        }
        addRandomNum();
        addRandomNum();
    }

    //随机产生2、4
    private void addRandomNum(){

        emptyPoints.clear();

        for (int y = 0; y < LINES; y++) {
            for (int x = 0; x < LINES; x++) {
                if (cardsMap[x][y].getNum()<=0) {
                    emptyPoints.add(new Point(x, y));
                }
            }
        }

        if (emptyPoints.size()>0) {

            //随机移除emptyPoint中的一个单元
            Point p = emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
            cardsMap[p.x][p.y].setNum(Math.random()>0.1?2:4);
            cardsMap[p.x][p.y].addScaleAnimation();
        }
    }

    //向左滑动
    private void swipeLeft(){

        boolean merge = false;

        for (int y = 0; y < LINES; y++) {
            for (int x = 0; x < LINES; x++) {

                for (int x1 = x+1; x1 < LINES; x1++) {
                    if (cardsMap[x1][y].getNum()>0) {

                        if (cardsMap[x][y].getNum()<=0) {
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);

                            x--;
                            merge = true;

                        }else if (cardsMap[x][y].equals(cardsMap[x1][y])) {

                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x1][y].setNum(0);

                            score.addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }

                        break;
                    }
                }
            }
        }

        if (merge) {
            addRandomNum();
            checkComplete();
        }
    }

    //向右滑动
    private void swipeRight(){

        boolean merge = false;

        for (int y = 0; y < LINES; y++) {
            for (int x = LINES-1; x >=0; x--) {

                for (int x1 = x-1; x1 >=0; x1--) {
                    if (cardsMap[x1][y].getNum()>0) {

                        if (cardsMap[x][y].getNum()<=0) {

                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);

                            x++;
                            merge = true;
                        }else if (cardsMap[x][y].equals(cardsMap[x1][y])) {

                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x1][y].setNum(0);
                            score.addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }

                        break;
                    }
                }
            }
        }

        if (merge) {
            addRandomNum();
            checkComplete();
        }
    }

    //向上滑动
    private void swipeUp(){

        boolean merge = false;

        for (int x = 0; x < LINES; x++) {
            for (int y = 0; y < LINES; y++) {

                for (int y1 = y+1; y1 < LINES; y1++) {
                    if (cardsMap[x][y1].getNum()>0) {

                        if (cardsMap[x][y].getNum()<=0) {

                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);

                            y--;

                            merge = true;
                        }else if (cardsMap[x][y].equals(cardsMap[x][y1])) {

                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x][y1].setNum(0);
                            score.addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }

                        break;

                    }
                }
            }
        }

        if (merge) {
            addRandomNum();
            checkComplete();
        }
    }

    //向下滑动
    private void swipeDown(){

        boolean merge = false;

        for (int x = 0; x < LINES; x++) {
            for (int y = LINES-1; y >=0; y--) {

                for (int y1 = y-1; y1 >=0; y1--) {
                    if (cardsMap[x][y1].getNum()>0) {

                        if (cardsMap[x][y].getNum()<=0) {

                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);

                            y++;
                            merge = true;
                        }else if (cardsMap[x][y].equals(cardsMap[x][y1])) {

                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x][y1].setNum(0);
                            score.addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }

                        break;
                    }
                }
            }
        }

        if (merge) {
            addRandomNum();
            checkComplete();
        }
    }

    private void checkComplete(){

        boolean complete = true;
        boolean num = false;

        //判断是否已经达到2048
        A:
        for (int y = 0; y < LINES; y++) {
            for (int x = 0; x < LINES; x++) {
                if (cardsMap[x][y].getNum() == 2048) {
                    num = true;
                    break A;
                }
            }
        }

        //判断还有没有空位或者还能不能相加
        ALL:
        if (num == false){
            for (int y = 0; y < LINES; y++) {
                for (int x = 0; x < LINES; x++) {
                    if (cardsMap[x][y].getNum() == 0 ||
                            (x > 0 && cardsMap[x][y].equals(cardsMap[x - 1][y])) ||
                            (x < LINES - 1 && cardsMap[x][y].equals(cardsMap[x + 1][y])) ||
                            (y > 0 && cardsMap[x][y].equals(cardsMap[x][y - 1])) ||
                            (y < LINES - 1 && cardsMap[x][y].equals(cardsMap[x][y + 1]))) {

                        complete = false;
                        break ALL;
                    }
                }
            }
        }

        if (complete && num) {
            new AlertDialog.Builder(
                    getContext()).setTitle("游戏结束").setMessage("你赢了！！！").
                    setPositiveButton("再玩一次？", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //startGame();
                    MainActivity.getMainActivity().startNewGame();
                }
            }
                    ).show();
        }else if (complete && !num){
            new AlertDialog.Builder(
                    getContext()).setTitle("游戏结束").setMessage("你输了！！！").
                    setPositiveButton("再玩一次？", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //startGame();
                                    MainActivity.getMainActivity().startNewGame();
                                }
                            }
                    ).show();
        }

    }

    public void setScore(MainActivity.Score score) {
        this.score = score;
    }
}
