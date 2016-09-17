package net.nicopoiduang.shuangshisb;


public abstract class State {
    protected game game;
    protected String postData;
    protected player player;
    public void updateState(String postData,player player)
    {
        this.postData=postData;
        this.player=player;
        resolveData();
        handle();
        judgeSituation();
    }
    abstract void handle();
    abstract void judgeSituation();

    public void init()
    {
        ;
    }
    public State(game game)
    {
        this.game=game;
        init();
    }

    abstract void resolveData();//name button checkbox

}
