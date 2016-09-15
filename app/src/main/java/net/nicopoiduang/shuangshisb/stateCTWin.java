package net.nicopoiduang.shuangshisb;

/**
 * Created by The_Void on 2016/9/16.
 */
public class stateCTWin extends avalonState{
    @Override
    public void init() {
        game.setPlayerStatus("请队员执行任务");
    }
    public stateCTWin(game game,player assassin)
    {
        super(game);
        if(assassin==null)
            game.setPlayerStatus("好人胜利");
        else
            game.setPlayerStatus("刺客"+assassin.name+"刺杀失败！背锅吧~！");
        game.isOver=true;
    }

    @Override
    void judgeSituation() {
        ;
    }

    @Override
    void handle() {
        ;
    }
}
