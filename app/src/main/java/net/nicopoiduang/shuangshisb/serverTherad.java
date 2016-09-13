package net.nicopoiduang.shuangshisb;

/**
 * Created by The_Void on 2016/8/24.
 */
public class serverTherad extends Thread{
    game game;
    public serverTherad(game game){

        this.game=game;
    }
    @Override
    public void run() {
        super.run();
        MyHttpServerListener serverListener = new MyHttpServerListener(2333,game);
        serverListener.start();
    }
}
