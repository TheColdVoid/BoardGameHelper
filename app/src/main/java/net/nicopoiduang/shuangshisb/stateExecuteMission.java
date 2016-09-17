package net.nicopoiduang.shuangshisb;


public class stateExecuteMission extends avalonState {
    @Override
    public void init() {
        game.setPlayerStatus(game.agreeCount+"同意"+game.disAgreeCount+"反对,请队员执行任务");
        game.flowCount=0;
        for (player i :
                game.team) {
            i.isInVote=true;
        }
        game.agreeCount=0;
        game.disAgreeCount=0;
    }
    public stateExecuteMission(game game)
    {
        super(game);
    }
    @Override
    void handle() {
        if(player.cmd.equals("同意")&&player.isInVote)
        {
            player.isInVote=false;
            player.isVoted=true;
            game.agreeCount++;
        }
        if(player.cmd.equals("反对")&&player.isInVote)
        {
            player.isInVote=false;
            player.isVoted=true;
            game.disAgreeCount++;
        }
        if(game.agreeCount+game.disAgreeCount>=game.team.size()) {
            if (game.disAgreeCount > 0) {
                game.tGetScore();
                game.roundResult[game.round-1] = "失败";
            } else {
                game.ctGetScore();
                game.roundResult[game.round-1] = "成功";
            }
            for (player i :
                    game.playerList) {
                i.isVoted = false;
                i.isInTeam=false;
            }
            game.round++;
            game.nowState=new stateCreateTeam(game);
        }
    }
}
