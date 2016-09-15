package net.nicopoiduang.shuangshisb;

/**
 * Created by The_Void on 2016/9/16.
 */
public class stateTWin extends avalonState{
    @Override
    public void init() {
    }
    public stateTWin(game game,player assassin)
    {
        super(game);
        if(assassin==null)
            game.setPlayerStatus("坏人胜利！");
        else
            game.setPlayerStatus("刺客"+assassin.name+"刺杀胜利");
        game.isOver=true;
    }
    @Override
    void handle() {
        ;
    }

    @Override
    void judgeSituation() {
        ;
    }
}
