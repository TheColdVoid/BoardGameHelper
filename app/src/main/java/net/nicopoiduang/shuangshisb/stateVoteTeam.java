package net.nicopoiduang.shuangshisb;

/**
 * Created by The_Void on 2016/9/16.
 */
public class stateVoteTeam extends avalonState {
    @Override
    public void init() {
        game.setPlayerStatus("请对组队进行投票");
    }
    public stateVoteTeam(game game)
    {
        super(game);//
        game.agreeCount=0;
        game.disAgreeCount =0;
        for (player i :
                game.playerList) {
            i.isInVote = true;
        }
    }
    @Override
    void handle() {
        if(player.cmd=="同意"||player.isInVote)
        {
            player.isAgree=true;
            player.isInVote =false;
            game.agreeCount++;
            player.isVoted=true;
        }
        if(player.cmd=="反对"||player.isInVote)
        {
            player.isDisAgree=true;
            player.isInVote =false;
            game.disAgreeCount++;
            player.isVoted=true;
        }
        if(game.agreeCount+game.disAgreeCount>=game.getPlayerCount())
        {
            for (player i :
                    game.playerList) {
                i.isAgree = false;
                i.isDisAgree=false;
                i.isInVote =false;
                i.isVoted=false;
            }
            if(game.agreeCount>game.disAgreeCount)
            {
                game.nowState=new stateExecuteMission(game);
            }
            else
            {
                game.nowState=new stateReCreateTeam(game);
            }
        }
    }
}
