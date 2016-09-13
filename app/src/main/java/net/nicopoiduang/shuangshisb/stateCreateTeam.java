package net.nicopoiduang.shuangshisb;

/**
 * Created by The_Void on 2016/9/7.
 */
public class stateCreateTeam extends avalonState{
    @Override
    public void init() {
        game.setPlayerStatus("请等待队长组队");
    }
    public stateCreateTeam(game game)
    {
        super(game);
    }
    @Override
    void handle() {

    }
}
