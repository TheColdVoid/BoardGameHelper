package net.nicopoiduang.shuangshisb;

/**
 * Created by The_Void on 2016/9/11.
 */
public class stateRevoteTeam extends avalonState{

    @Override
    public void init() {
        game.setPlayerStatus("请等待游戏开始");
    }
    public stateRevoteTeam(game game)
    {
        super(game);
    }
    @Override
    void handle() {

    }
}
