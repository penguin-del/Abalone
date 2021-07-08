package utilities;

import java.util.Random;

public class LocalRandom
{
	protected static Random _rng;
	
	static
	{
		_rng= new Random();
	}
	
	public static int nextInt(int ub) { return _rng.nextInt(ub); }
}
