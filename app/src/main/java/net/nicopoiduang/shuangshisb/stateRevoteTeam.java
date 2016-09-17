package net.nicopoiduang.shuangshisb;


public class stateReVoteTeam extends avalonState{
    @Override
    public void init() {
        game.setPlayerStatus("请对组队进行投票");
    }
    public stateReVoteTeam(game game)
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
        if(player.cmd.equals("同意")&&player.isInVote)
        {
            player.isAgree=true;
            player.isInVote =false;
            game.agreeCount++;
            player.isVoted=true;
        }
        if(player.cmd.equals("反对")&&player.isInVote)
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
                game.flowCount++;
                if(game.flowCount>=5)
                    game.nowState=new stateTWin(game,null);
                else
                game.nowState=new stateCreateTeam(game);
            }
        }
    }
}
