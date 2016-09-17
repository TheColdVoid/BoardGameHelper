package net.nicopoiduang.shuangshisb;

import java.util.ArrayList;


public class stateReCreateTeam extends avalonState{
    @Override
    public void init() {
        game.setPlayerStatus(game.agreeCount+"同意"+game.disAgreeCount+"反对,请等待队长重新组队");
    }
    public stateReCreateTeam(game game)
    {
        super(game);
        game.leader.isInCreateTeam=true;
        game.leader.status=game.agreeCount+"同意"+game.disAgreeCount+"反对,请你重新组队";
    }
    @Override
    void handle() {
        if(game.isTeamAvailable() && player.isLeader &&player.cmd.equals("组队"))
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
