package net.nicopoiduang.shuangshisb;

/**
 * Created by The_Void on 2016/9/3.
 */
public class dataPoster {
    private State statePreMatch;
    private game game;
    public dataPoster(game game){
        {
            this.game=game;
        }
    }
    public synchronized void update(String postData,player player)
    {
        game.nowState.updateState(postData,player);
    }
}
