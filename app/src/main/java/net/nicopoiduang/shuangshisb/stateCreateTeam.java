package net.nicopoiduang.shuangshisb;

import java.util.ArrayList;

/**
 * Created by The_Void on 2016/9/7.
 */
public class stateCreateTeam extends avalonState{
    @Override
    public void init() {
        if(game.round==1)
            ;
        else game.setPlayerStatus(game.agreeCount+"同意"+game.disAgreeCount+"反对，请队长组队");
    }
    public stateCreateTeam(game game)
    {
        super(game);
        if(game.leader!=null) {
            game.leader.isLeader = false;
            for (player i :
                    game.playerList) {
                i.isInTeam = false;
            }
        }
        game.nextLeader();
        game.leader.isLeader=true;
        game.leader.isInCreateTeam=true;
    }
    @Override
    void handle() {
        if(game.round==1) {
            game.setPlayerStatus("你是" + game.getJobName(player.job) + ",请等待队长组队~");
            game.leader.status="你是" + game.getJobName(player.job) + ",请你组队~";
        }
        if(game.isTeamAvailable() && player.isLeader&&player.cmd.equals("组队"))
        {
            game.team=(ArrayList<net.nicopoiduang.shuangshisb.player>) game.selection.clone();
            for (player i :
                    game.team) {
                i.isInTeam = true;
            }
            game.leader.isInCreateTeam=false;
            game.nowState=new stateVoteTeam(game);
        }
        else if(player.cmd.equals("组队"))
        player.status="组队不合法，人数不符或者你不是队长";
    }
}
