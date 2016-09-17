package net.nicopoiduang.shuangshisb;

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
