package net.nicopoiduang.shuangshisb;

/**
 * Created by The_Void on 2016/9/16.
 */
public class stateWaitingForAssassination extends avalonState{
    @Override
    public void init() {
        game.setPlayerStatus("请刺客杀人");
    }
    public stateWaitingForAssassination(game game)
    {
        super(game);
    }
    @Override
    void handle() {
        ;
    }

    @Override
    void judgeSituation() {
        if(player.job==joblist.assassin)
            if(player.cmd.equals("刺杀"))
                if(game.selection.size()==1)
                {
                    if(game.selection.get(0).job==joblist.merlin)
                        game.nowState=new stateTWin(game,player);
                    else
                        game.nowState=new stateCTWin(game,player);
                }
        game.selection.clear();
    }
}
