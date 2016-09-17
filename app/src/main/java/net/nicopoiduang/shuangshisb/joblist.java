package net.nicopoiduang.shuangshisb;

public enum joblist
{
	ct(0),
	merlin(1),
	morgana(2),
	common1(3),
	assassin(4),
	common2(5),
	vassels(6),
	Oberyn(7),
	common3(8),
	common4(9),
	mordred(10);


	private int intValue;
	private static java.util.HashMap<Integer, joblist> mappings;
	private static java.util.HashMap<Integer, joblist> getMappings()
	{
		if (mappings == null)
		{
			synchronized (joblist.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, joblist>();
				}
			}
		}
		return mappings;
	}

	private joblist(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static joblist forValue(int value)
	{
		return getMappings().get(value);
	}
}
