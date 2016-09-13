package net.nicopoiduang.shuangshisb;

import java.net.SocketAddress;
import java.util.Hashtable;
import java.util.Random;
import java.util.*;
public class game
{
	public boolean start=false;
	public int round = 1;
	public int CTScore=1;
	public int TScore=1;
	public String roundResult[]={null,null,null,null,null};
	public boolean tWin=false,ctWin=false;
	private HashMap< String,player > nameTable = new HashMap<String, player>();
	public int agreeCount = 0;
	public int disagreeCount = 0;
	public int flowCount = 0;
	public boolean isOver=false;
	public player actPlayer;
	public ArrayList<player> playerList=new ArrayList<player>();
	public ArrayList<player> team=new ArrayList<player>();
	public ArrayList<player> selection=new ArrayList<player>();
	public player leader;
	public dataPoster dataPoster=new dataPoster(this);
	public responseCombiner responseCombiner=new responseCombiner(this);
	public int[][] teamSize;
	public ArrayList<joblist[]> characterConfig=new ArrayList();
	public avalonState nowState=new stateWaitForStart(this);
	public ArrayList<joblist> merlinCanSeeList =new ArrayList<>();
	public ArrayList<joblist> TCanSeeList =new ArrayList<>();
	public ArrayList<joblist> CTCanSeeList =new ArrayList<>();
	public ArrayList<joblist> CTJobList =new ArrayList<>();
	public ArrayList<joblist> TJobList=new ArrayList<>();
	public void swapPlayer()
	{
		int temp=selection.get(0).index;
		selection.get(0).index=selection.get(1).index;
		selection.get(1).index=temp;
		playerList.set(selection.get(0).index,selection.get(0));
		playerList.set(selection.get(1).index,selection.get(1));
	}
	public final void nextLeader()
	{
		if(leader!=null)
		{
			if (leader.index != 4) {
				leader = playerList.get(leader.index+1);
			} else {
				leader = playerList.get(0);
			}
		}
		else
			leader=getRandomPlayer();
	}
	public final player getRandomPlayer()
	{
//ORIGINAL LINE: Random ran = new Random(unchecked((int)DateTime.Now.Ticks));
		Random ran = new Random(System.currentTimeMillis());
		return playerList.get(ran.nextInt(playerList.size()-1));
	}
	public game()
	{
		//playerCount = Count;
		dataInit();
		;
	}

	public final void getRandomJob(int count)
	{


		Hashtable hashtable = new Hashtable();
		Random rm = new Random(System.currentTimeMillis());
		int RmNum = count;
		int j = 0;
		for (; hashtable.size() < RmNum;)
		{
			int nValue = rm.nextInt(count);
			if (!hashtable.containsValue(nValue))
			{
				hashtable.put(nValue, nValue);
				playerList.get(j).job = characterConfig.get(playerList.size()-5)[nValue];
				j++;
			}
		}
			//gameplayer[j].job = (joblist)index[id];
	}
	public final void ctGetScore()
	{
		CTScore++;
	}
	public final void tGetScore()
	{
		TScore++;
	}
	public player getPlayer(int index)
	{
		return playerList.get(index);
	}
	public synchronized player getPlayer(SocketAddress socketIp)
	{
		String ip=socketIp.toString().substring(1,socketIp.toString().indexOf(":"));
		if(nameTable.containsKey(ip))
		{
			System.out.println(ip+"Contain");
			return nameTable.get(ip);
		}
		else
		{
			playerList.add(new player(playerList.size()));
			nameTable.put(ip,playerList.get(playerList.size()-1));
			System.out.println(ip+"Connect");
			return playerList.get(playerList.size()-1);
		}
	}
	public synchronized player getNewPlayer(SocketAddress socketIp)
	{
		String ip=socketIp.toString().substring(1,socketIp.toString().indexOf(":"));
		if(nameTable.containsKey(ip))
			return nameTable.get(ip);
		else return null;
	}
	public int getPlayerCount(){
		return playerList.size();
	}
	public void setPlayerStatus(String status)
	{
		Iterator ite=playerList.iterator();
		while (ite.hasNext())
		{
			player i = (player)ite.next();
			i.status=status;
		}
	}
	public boolean isTeamAvailable()
	{
		if(teamSize[playerList.size()-5][round]==selection.size())
		{
			return true;
		}
		else return false;
	}
	public String getJobColor(joblist job)
	{
		if(TJobList.contains(job))
			return "#73B599";
		if(CTJobList.contains(job))
			return "#CD5755";
		return null;
	}
	public String getJobName(joblist job)
	{
		switch (job)
		{
			case ct:
				return "派西维尔";
			case merlin:
				return "梅林";
			case morgana:
				return "莫甘娜";
			case Oberyn:
				return "奥伯伦";
			case vassels:
				return "爪牙";
			case assassin:
				return "刺客";
			case common1:
				return "平民";
			case common2:
				return "平民";
			case common3:
				return "平民";
			case common4:
				return "平民";
		}
		return "null";
	}
	private void dataInit()
	{
		joblist[] joblists5={joblist.assassin,joblist.merlin,joblist.common1,joblist.ct,joblist.morgana};
		joblist[] joblists6={joblist.assassin,joblist.merlin,joblist.common1,joblist.ct,joblist.morgana,joblist.common2};
		joblist[] joblists7={joblist.assassin,joblist.merlin,joblist.common1,joblist.ct,joblist.morgana,joblist.common2,joblist.Oberyn};
		joblist[] joblists8={joblist.assassin,joblist.merlin,joblist.common1,joblist.ct,joblist.morgana,joblist.common2,joblist.common3,joblist.vassels};
		joblist[] joblists9={joblist.assassin,joblist.merlin,joblist.common1,joblist.ct,joblist.morgana,joblist.common2,joblist.common3,joblist.common4,joblist.mordred};
		joblist[] joblists10={joblist.assassin,joblist.merlin,joblist.common1,joblist.ct,joblist.morgana,joblist.common2,joblist.common3,joblist.common4,joblist.mordred,joblist.Oberyn};
		characterConfig.add(joblists5);
		characterConfig.add(joblists6);
		characterConfig.add(joblists7);
		characterConfig.add(joblists8);
		characterConfig.add(joblists9);
		characterConfig.add(joblists10);
		int[][] temp={
				{2,3,2,3,3},
				{2,3,4,3,4},
				{2,3,3,4,4},
				{3,4,4,5,5},
				{3,4,4,5,5},
				{3,4,4,5,5},
		};
		teamSize=temp;
		merlinCanSeeList.add(joblist.morgana);
		merlinCanSeeList.add(joblist.assassin);
		merlinCanSeeList.add(joblist.vassels);
		merlinCanSeeList.add(joblist.Oberyn);
		TCanSeeList.add(joblist.assassin);
		TCanSeeList.add(joblist.morgana);
		TCanSeeList.add(joblist.mordred);
		TCanSeeList.add(joblist.vassels);
		CTCanSeeList.add(joblist.merlin);
		CTCanSeeList.add(joblist.morgana);
		CTJobList.add(joblist.common1);
		CTJobList.add(joblist.common2);
		CTJobList.add(joblist.common3);
		CTJobList.add(joblist.common4);
		CTJobList.add(joblist.ct);
		CTJobList.add(joblist.merlin);
		TJobList.add(joblist.morgana);
		TJobList.add(joblist.assassin);
		TJobList.add(joblist.vassels);
		TJobList.add(joblist.mordred);
		TJobList.add(joblist.Oberyn);
	}

}