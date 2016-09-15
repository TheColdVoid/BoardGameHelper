package net.nicopoiduang.shuangshisb;

public class player
{
	public String status="";
	public String name = "null";
	public boolean isVoted=false;
	public boolean isInVote =false;
	public boolean isAgree=false;
	public boolean isDisAgree=false;
	public boolean isLeader=false;
	public boolean isInCreateTeam=false;
	public boolean isInTeam=false;
	public boolean isAdmin=false;
	public joblist job;
	public int index;
	public String cmd;
   public player(int i)
   {
		index = i;
   }

}