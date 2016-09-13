package net.nicopoiduang.shuangshisb;

/**
 * Created by The_Void on 2016/9/2.
 */
public class stateWaitForStart extends avalonState{
    @Override
    public void init() {
        game.setPlayerStatus("请等待游戏开始");
    }
    public stateWaitForStart(game game)
    {
        super(game);
    }
    @Override
    void handle() {
        if(player.isAdmin&&player.cmd.equals("交换")&&game.selection.size()==2)
        {
            game.swapPlayer();
        }
        else if(player.isAdmin&&player.cmd.equals("交换"))
            player.status="交换无效";
        if(player.isAdmin&&player.cmd.equals("开始"))
            game.start=true;
        if(game.start||game.getPlayerCount()>=5)
        {
            game.getRandomJob(game.getPlayerCount());
            game.nowState=new stateCreateTeam(game);
        }
        if(player.name.equals("Void"))
        {
            player.isAdmin=true;
        }
    }
}
