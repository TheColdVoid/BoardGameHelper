package net.nicopoiduang.shuangshisb;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
        serverTherad sth=new serverTherad(game);
        sth.start();
    }

    public void btnClick(View view) throws Exception{
        game.isStart =true;
        if(textView.getText().equals("nico"))
        textView.setText("poi");
        else
            textView.setText("nico");

        }

}


