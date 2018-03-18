package wuziqi.cbt.com.wuziqi;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {
    private WuziqiPanel mGamePanel;
    private Builder alertBuilder;
    private AlertDialog alertDialog;
    private Button btn,btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //游戏结束时弹出对话框
        alertBuilder = new Builder(MainActivity.this);

        alertBuilder.setPositiveButton("继续", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mGamePanel.retoGame();
            }
        });

      /*

       alertBuilder.setNegativeButton("退出游戏", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
            }
        });

        */

        alertBuilder.setCancelable(false);
        alertBuilder.setTitle("此局结束");

        mGamePanel = (WuziqiPanel) findViewById(R.id.wuziqi2);
        mGamePanel.setOnGameStatusChangeListener(new OnGameStatusChangeListener() {
            @Override
            public void onGameOver(int gameWinResult) {
                switch (gameWinResult) {
                    case WuziqiPanel.WHITE_WIN:
                        alertBuilder.setMessage("白棋胜利!");
                        break;
                    case WuziqiPanel.BLACK_WIN:
                        alertBuilder.setMessage("黑棋胜利!");
                        break;
                    case WuziqiPanel.NO_WIN:
                        alertBuilder.setMessage("和棋!");
                        break;
                }
                alertDialog = alertBuilder.create();
                alertDialog.show();
            }
        });

     btn= (Button) findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mGamePanel.restartGame();
            }
        });
        btn2= (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
            }
        });

    }
}
