import java.net.*;
import java.io.*;
import java.util.ArrayList;
/*
	This is the main server file 
	It holds: the list of found primes, The current long, The monitor, and all the sub server threads
*/
public class PrimeServer  
{
	static ArrayList<Long> primes = new ArrayList<Long>();
	static Long curr;	
	static SerMon mon;
	
	public static void main(String args[]) throws IOException 
	{
		curr = new Long(2);
		primes.add(curr);
		curr++;
		boolean test = true;
		mon = new SerMon(primes, curr, test);		
		
		ServerSocket ss;
		Socket s;
		
		try 
		{
			ss = new ServerSocket(1917);
			while(test)
			{
				System.out.println("ready");
				s = ss.accept();
				ServerHelper helper = new ServerHelper(s, mon, test);
				helper.start();
			}
			ss.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		for(int i = 0; i < primes.size(); i++)
		{
			System.out.println(" " + i + " :- " + primes.get(i));
		}
	}
}