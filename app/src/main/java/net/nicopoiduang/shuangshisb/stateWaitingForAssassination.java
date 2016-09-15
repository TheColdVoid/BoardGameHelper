package net.nicopoiduang.shuangshisb;

/**
 * Created by The_Void on 2016/9/16.
 */
public class stateWaitingForAssassination extends avalonState{
    @Override
    public void init() {
        game.setPlayerStatus("请刺客杀人");
    }
    public stateWaitingForAssassination(game game)
    {
        super(game);
    }
    @Override
    void handle() {
        ;
    }
}
