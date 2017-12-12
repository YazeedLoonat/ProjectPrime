import java.util.ArrayList;
/*
	Server thread monitor 
	handles inserts for the master list as well as bumping up the current long and returns it as well
*/
public class SerMon 
{
	ArrayList<Long> primes;
	Long current;
	boolean test;
	
	public SerMon(ArrayList<Long> p, Long c, boolean t)
	{
		primes = p; 
		current = c;
		test = t;
	}

	public synchronized void insert(Long l)
	{
		Long last = primes.get(primes.size() - 1);
		if( last < l)
		{
			primes.add(l);
		}
		else
		{
			int pos = primes.size() - 2;
			while(pos >= 0)
			{
				if(primes.get(pos) < l)
				{
					primes.add(pos, l);
					break;
				}
				else
					pos--;
			}
		}
	}
	
	public synchronized void up()
	{
		current += 2;
		if(current > 11)
			test = false;
	}
	
	public long curr()
	{
		return current;
	}
	
	public ArrayList<Long> getPrimes()
	{
		return primes;
	}
}