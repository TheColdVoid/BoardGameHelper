package net.nicopoiduang.shuangshisb;
/**
 * Created by The_Void on 2016/8/30.
 */
public class responseCombiner {
    private String HTTPHeader,nameBox,loginButton,updateButton, createTeamButton,agreeButton,disagreeButton, assassinateButton,adminButton;
    player player;
    game game;
    private void initString()
    {
        StringBuffer result=new StringBuffer();
        result.append( "HTTP/1.1 200 OK\n");
        result.append( "Server: ShuangShiSB 1.0\n");
        result.append( "Content-Type: text/html;charset=utf-8\n");
        result.append( "\n");
        HTTPHeader=result.toString();
        nameBox="<input name=\"name\" type=\"text\" value=\"\" />";
        loginButton="<input name=\"button\" type=\"submit\" value=\"登陆\" /><br>";
        updateButton="<input name=\"button\" type=\"submit\" value=\"更新\" /><br>";
        createTeamButton ="<input name=\"button\" type=\"submit\" value=\"组队\" />";
        agreeButton="<input name=\"button\" type=\"submit\" value=\"同意\" />";
        disagreeButton="<input name=\"button\" type=\"submit\" value=\"反对\" />";
        assassinateButton="<input name=\"button\" type=\"submit\" value=\"刺杀\" />";
        adminButton="<input name=\"button\" type=\"submit\" value=\"开始\" /><input name=\"button\" type=\"submit\" value=\"交换\" />";
    }
    public responseCombiner(game game)
    {
        this.game=game;
        initString();
    }
    public synchronized String getResponsePage(player player)
    {
        this.player=player;
        StringBuffer result=new StringBuffer();
        result.append(result);
        result.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style type=\"text/css\">\n" +
                "body {zoom:1;}\n" +
                " h1{color:#407D6E;}\n" +
                "</style>\n" +
                "<title></title>\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "<meta charset=\"utf-8\">"+
                "</head>\n" +
                "<body>\n" +
                "<font color=#407D6E>\n" +
                "<form action=\"/\" method=\"post\">");
        result.append("<h1>阿瓦隆Beta</h1>");
        result.append(getNameBoxAndStatusBox());

        if(!game.isStart &&player.isAdmin)
            result.append(adminButton);
        result.append(getPlayerList());
        if(player.job==joblist.assassin)
            result.append(assassinateButton);
        if(player.isInCreateTeam)
            result.append(createTeamButton);
        if(player.isInVote) {
            result.append(agreeButton);
            result.append(disagreeButton);
        }

        result.append(getUpdateButton());
        if(game.isStart)
            result.append(getScoreTable());
        result.append("\t</form>\n" +
                "</font>\n" +
                "</body>\n" +
                "</html>\n");
        return result.toString();
    }
    public synchronized String getNewUserPage(player player)
    {
        StringBuffer result=new StringBuffer();
        result.append(result);
        result.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style type=\"text/css\">\n" +
                "body {zoom:1;}\n" +
                " h1{color:#407D6E;}\n" +
                "</style>\n" +
                "<title></title>\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "<meta charset=\"utf-8\">"+
                "</head>\n" +
                "<body>\n" +
                "<font color=#407D6E>\n" +
                "<form action=\"/\" method=\"post\">");
        result.append("<h1>阿瓦隆</h1>");
        result.append(nameBox);
        result.append(loginButton);
        result.append("\t</form>\n" +
                "</font>\n" +
                "</body>\n" +
                "</html>\n");
        return result.toString();
    }

    private String getNameBoxAndStatusBox()
    {
        if (player.name==null||player.name=="没起名儿")
        {
            return nameBox+loginButton;
        }
        else
        {
            return "<p>"+player.status+"</p>";
        }
    }
    private String getScoreTable()
    {
        StringBuffer result=new StringBuffer();
        result.append("<table border=\"1\"><tr>");
        result.append("<tr>\n" +
                "\t\t\t<td>任务：</td>\n" +
                "\t\t\t<td>1</td>\n" +
                "\t\t\t<td>2</td>\n" +
                "\t\t\t<td>3</td>\n" +
                "\t\t\t<td>4</td>\n" +
                "\t\t\t<td>5</td>\n" +
                "\t\t</tr>");
        result.append("<tr>\n" +
                "\t\t\t<td>状态：</td>");
        for (int i=0;i<=4;i++)
        {
            result.append("<td>");
            if(game.roundResult[i]!=null)
            {
                if(game.roundResult[i].equals("成功"))
                    result.append("<span style=\"color:#407D6E;\">成功</span>");
                if(game.roundResult[i].equals("失败"))
                    result.append("<span style=\"color:#7A2127;\">失败</span>");
            }
            else result.append(game.teamSize[game.getPlayerCount()-5][i]+"人");
            result.append("</td>");
        }
        result.append("</tr></table>");
        return result.toString();
    }
    private String getUpdateButton()
    {
        if(player.name==null){
            return loginButton;
        }
        else
        {
            return updateButton;
        }
    }
    private String getPlayerList()
    {
        StringBuffer result=new StringBuffer();
        result.append("<table border=\"0\">");
        for (player i:
             game.playerList) {
            result.append("<tr>");
            result.append("<td><input type=\"checkbox\" name=\"checkbox\" value=\"");
            result.append(String.valueOf(i.index));
            result.append("\" />");
            result.append(i.name);
            result.append("</td>");
            if (game.isStart) {
                if (i==player||game.isOver) {
                    result.append("<td><span style=\"color:"+game.getJobColor(i.job)+";\">");
                    result.append(game.getJobName(i.job));
                    result.append("</span></td>");
                }
                else
                {

                    if (player.job==joblist.merlin) {
                        if (game.merlinCanSeeList.contains(i.job)) {
                            result.append("<td><span style=\"color:#73B599;\">");
                            result.append("坏人");
                        }
                        else result.append("<td><span style=\"color:#CD5755;\">");
                    }
                    else if(player.job==joblist.ct)
                            if (game.CTCanSeeList.contains(i.job)) {
                                result.append("<td><span style=\"color:#CD5755;\">");
                                result.append(game.getJobName(joblist.merlin) + "/" + game.getJobName(joblist.morgana));
                            }
                            else result.append("<td><span style=\"color:#CD5755;\">");
                    else if (game.TCanSeeList.contains(player.job))
                            if (game.TCanSeeList.contains(i.job)) {
                                result.append("<td><span style=\"color:#73B599;\">");
                                result.append(game.getJobName(i.job));
                            }
                            else result.append("<td><span style=\"color:#CD5755;\">");
                    else result.append("<td><span style=\"color:#CD5755;\">");
                    result.append("</span></td>");
                }
            } else {
                ;
            }

            if(i.isVoted)
                result.append("<td> <span style=\"color:#CD5755;\">已投</span> </td>");
            else
                result.append("<td> <span style=\"color:#CD5755;\"></span> </td>");
            if (i.isAgree)
                result.append("<td> <span style=\"color:#407D6E;\">同意</span> </td>");
            else if(i.isDisAgree)
                result.append("<td> <span style=\"color:#7A2127;\">反对</span> </td>");
            else
                result.append("<td> <span style=\"color:#7A2127;\"></span> </td>");
            if(i.isInTeam)
                result.append("<td> <span style=\"color:#CD5755;\">在队</span> </td>");
            else if(i.isLeader)
                result.append("<td> <span style=\"color:#CD5755;\">队长</span> </td>");
            else
                result.append("<td> <span style=\"color:#CD5755;\"></span> </td>");
            result.append("</tr>");
        }
        result.append("</table>");
        return result.toString();
    }
}
