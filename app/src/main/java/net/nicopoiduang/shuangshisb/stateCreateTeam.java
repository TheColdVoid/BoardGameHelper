package net.nicopoiduang.shuangshisb;

import java.util.ArrayList;

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
        game.nextLeader();
        game.leader.isInCreateTeam=true;
        game.leader.status="请你组队";
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
            game.nowState=new stateVoteTeam(game);
        }
    }
}
