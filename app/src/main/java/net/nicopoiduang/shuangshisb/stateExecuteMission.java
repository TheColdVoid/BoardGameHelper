package net.nicopoiduang.shuangshisb;

/**
 * Created by The_Void on 2016/9/16.
 */
public class stateExecuteMission extends avalonState {
    @Override
    public void init() {
        game.setPlayerStatus("请队员执行任务");
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
        if(player.cmd=="同意"&&player.isInVote)
        {
            player.isInVote=false;
            player.isVoted=true;
            game.agreeCount++;
        }
        if(player.cmd=="反对"&&player.isInVote)
        {
            player.isInVote=false;
            player.isVoted=true;
            game.agreeCount++;
        }
        if(game.agreeCount+game.disAgreeCount>=game.team.size()) {
            if (game.disAgreeCount > 0) {
                game.tGetScore();
                game.roundResult[game.round] = "失败";
            } else {
                game.ctGetScore();
                game.roundResult[game.round] = "成功";
            }
            game.nowState=new stateCreateTeam(game);
        }
    }
}
