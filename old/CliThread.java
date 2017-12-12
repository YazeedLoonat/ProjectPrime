import java.util.ArrayList;
public class CliThread extends Thread
{
	
	CliMon mon;
	public CliThread(CliMon m)
	{
		mon = m;
	}
	
	public void run()
	{
		Response r;
		
		String c;
		Long l;
		ArrayList<Long> primes;
		
		boolean test;
		Long emergency = new Long(0);
		while(true)
		{
			if(mon.sStatus() == true)
				break;
			r = new Response("request", emergency, null);
			r = mon.passOn(r);
			
			c = r.getCommand();
			l = r.getNum();
			primes = r.getPrimes();
			
			if( l.compareTo(emergency) < 0)
			{
				r = new Response("done", l, null);
				r = mon.passOn(r);
			}			
			
			else if(c.equals("done"))
				break;
				
			else if(c.equals("response"))
			{
				test = primeTest(l, primes);
				if(test == true)
				{
					System.out.println("found something " + l);
					r = new Response("found", l, null);
					r = mon.passOn(r);
				}
			}
		}
	}
	
	private boolean primeTest(Long l, ArrayList<Long> primes)
	{
		
	
		Long x = new Long(-1);
		for(int i = 0; i < primes.size(); i++)
		{
			x = primes.get(i);
			if( l % x == 0)
			{
				return false;
			}
		}
		while(x < l)
		{
			if(l % x == 0)
				return false;
			x += 1 ;
		}
		return true;
	}
	
}
