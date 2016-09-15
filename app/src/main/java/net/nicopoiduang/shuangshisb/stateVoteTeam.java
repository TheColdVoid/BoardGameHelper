package net.nicopoiduang.shuangshisb;

/**
 * Created by The_Void on 2016/9/16.
 */
public class stateVoteTeam extends avalonState {
    @Override
    public void init() {
        game.setPlayerStatus("请等待游戏开始");
    }
    public stateVoteTeam(game game)
    {
        super(game);
    }
    @Override
    void handle() {

    }
}
