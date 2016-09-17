package net.nicopoiduang.shuangshisb;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView textView;
    game game=new game();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().
                detectDiskWrites().detectNetwork().penaltyLog().build());
        textView=(TextView)findViewById(R.id.textview);

        MyHttpServerListener serverListener = new MyHttpServerListener(2333,game);
        serverListener.start();

    }

    public void btnClick(View view) throws Exception {
        if(game.isStart=false||game.getPlayerCount()>=5) {
            game.isStart = true;
            textView.setText("游戏开始");
            Button button = (Button) findViewById(R.id.button);
            button.setText("重新开始");
        }
        else if(game.isOver)
        {
            game=new game();
        }
        //TODO:Debug
    }
}


