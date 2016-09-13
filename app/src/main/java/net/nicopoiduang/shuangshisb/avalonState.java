package net.nicopoiduang.shuangshisb;

/**
 * Created by The_Void on 2016/9/4.
 */
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
            if(player.cmd.equals("assassinate"))
                if(game.selection.size()==1)
                {
                    //TODO
                }
        if(game.TScore>=3)
        {
            game.tWin=true;
            game.setPlayerStatus("坏人胜利");
            //TODo game.state=TWinState;
        }
        if(game.CTScore>=3)
        {
            game.setPlayerStatus("请刺客杀人");
            //TODo game.state=assasinState;
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
                player.cmd=parameter.value;
            if(parameter.key.equals("checkBox"))
                game.selection.add(game.playerList.get(Integer.parseInt(parameter.value)));
        }
    }
}
