package net.nicopoiduang.shuangshisb;

import java.net.URLDecoder;


public abstract class avalonState extends State {
    public void updateState(String postData,player player)
    {
        this.postData=postData;
        this.player=player;
        resolveData();
        handle();
        judgeSituation();
    }
    public avalonState(game game)
    {
        super(game);
    }
    abstract void handle();
    void judgeSituation()
    {
        if(player.job==joblist.assassin)
            if(player.cmd.equals("刺杀"))
                if(game.selection.size()==1)
                {
                    if(game.selection.get(0).job==joblist.merlin)
                        game.nowState=new stateTWin(game,player);
                    else
                        game.nowState=new stateCTWin(game,player);
                }
        if(game.TScore>=3)
        {
            game.tWin=true;
            game.setPlayerStatus("坏人胜利");
            game.nowState=new stateTWin(game,null);
        }
        if(game.CTScore>=3)
        {
            game.tWin=true;
            game.setPlayerStatus("请刺客杀人");
            game.nowState=new stateWaitingForAssassination(game);
        }
        game.selection.clear();
    }

     void resolveData()//name button checkbox
    {
        parameterList parameterList=new parameterList(postData);
        for (parameter parameter:
             parameterList.elements) {
            if (parameter.key.equals("name"))
               player.name=parameter.value;
            if(parameter.key.equals("button"))
                player.cmd= URLDecoder.decode(parameter.value);
            if(parameter.key.equals("checkbox"))
                game.selection.add(game.playerList.get(Integer.parseInt(parameter.value)));
        }
    }
}
