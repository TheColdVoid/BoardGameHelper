package net.nicopoiduang.shuangshisb;

import java.util.ArrayList;

/**
 * Created by The_Void on 2016/9/16.
 */
public class stateReCreateTeam extends avalonState{
    @Override
    public void init() {
        game.setPlayerStatus("请等待队长重新组队");
    }
    public stateReCreateTeam(game game)
    {
        super(game);
        game.leader.isInCreateTeam=true;
        game.leader.status="请你重新组队";
    }
    @Override
    void handle() {
        if(game.isTeamAvailable() && player.isLeader)
        {
            game.team=(ArrayList<net.nicopoiduang.shuangshisb.player>) game.selection.clone();
            for (player i :
                    game.team) {
                i.isInTeam = true;
            }
            game.leader.isInCreateTeam=false;
            game.nowState=new stateReVoteTeam(game);
        }
    }
}
