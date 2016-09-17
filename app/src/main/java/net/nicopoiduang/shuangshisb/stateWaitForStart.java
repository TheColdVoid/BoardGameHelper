package net.nicopoiduang.shuangshisb;


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
        game.setPlayerStatus("请等待游戏开始");
        if(player.isAdmin&&player.cmd.equals("交换")&&game.selection.size()==2)
        {
            game.swapPlayer();
        }
        else if(player.isAdmin&&player.cmd.equals("交换"))
            player.status="交换无效";
        if(player.isAdmin&&player.cmd.equals("开始")&&game.getPlayerCount()>=5)
            game.isStart =true;
        if(game.isStart &&game.getPlayerCount()>=5)
        {
            game.getRandomJob(game.getPlayerCount());
            game.nowState=new stateCreateTeam(game);
            game.nowState.updateState("",player);
        }
        if(player.name.equals("Void"))
        {
            player.isAdmin=true;
        }

    }
}
