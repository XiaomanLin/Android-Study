package com.example.a2048;

import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.KeyEvent;

public class MainActivity extends AppCompatActivity {

    private TextView scoreTextView,bestScoreTextView;
    private Button buttoNewGame;
    private GameView gameView = null;
    private Score score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttoNewGame = (Button) findViewById(R.id.btnNewGame);
        scoreTextView = (TextView) findViewById(R.id.score);
        bestScoreTextView = (TextView) findViewById(R.id.bestScore);

        gameView = (GameView) findViewById(R.id.gameView);

        score = new Score();
        gameView.setScore(score);

        buttoNewGame.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startNewGame();
            }
        });
    }

    //获取MainActivity实例，以便调用其函数的方法：
    public MainActivity() {
        mainActivity = this;
    }

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    private static MainActivity mainActivity;
    public void startNewGame(){
        score.clearScore();
        showScore();
        showBestScore();
        gameView.startGame();
    }

//    private void startNewGame(){
//        score.clearScore();
//        showScore();
//        showBestScore();
//        gameView.startGame();
//    }

    private void showBestScore(){
        bestScoreTextView.setText(String.valueOf(score.getBestScore()));
    }
    private void showScore(){
        scoreTextView.setText(String.valueOf(score.getScore()));
    }

    class Score{
        private int score = 0;
        private static final String SP_KEY_BEST_SCORE = "bestScore";

        public void clearScore() {
            score = 0;
        }

        public int getScore() {
            return score;
        }

        public void addScore(int s){
            score += s;
            showScore();

            saveBestScore(Math.max(score, getBestScore()));
            showBestScore();
        }

        public void saveBestScore(int s){
            Editor e =getPreferences(MODE_PRIVATE).edit();
            e.putInt(SP_KEY_BEST_SCORE, s);
            e.commit();
        }

        public int getBestScore(){
            return getPreferences(MODE_PRIVATE).getInt(SP_KEY_BEST_SCORE,0);
        }
    }

    //返回键退出提醒
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            dialog();
            return true;
        }
        return true;
    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("确定要退出吗?");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                });
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();

    }

}
