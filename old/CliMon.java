import java.util.ArrayList;
import java.net.*;
import java.io.*;
/*
	client monitor
	gets responses from threads and forwards them to the server
	then gets responses from the server and forwards them to the threads
	
	as well as handling inserting into the list of primes
*/
public class CliMon 
{
	ArrayList<Long> primes;
	Socket s;
	OutputStream os;
	ObjectOutputStream oos;
	
	InputStream is;
	ObjectInputStream ois;
	
	public CliMon(Socket so, InputStream i, ObjectInputStream oi, OutputStream o, ObjectOutputStream oo)
	{
		s = so;		
		os = o;
		oos = oo;
		
		is = i;
		ois = oi;	
		
		System.out.println("started");
	}

	public synchronized Response passOn(Response r)
	{
		if(sStatus() == true)
			return r;
		try 
		{			
			oos.writeObject(r);
			Response r2 = (Response)ois.readObject();
			if(r2.getCommand().equals("done"))
			{
				try 
				{
					ois.close();
					is.close();
					oos.close();
					os.close();
					if (s.isClosed() == false)
						s.close();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			return r2;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return r;
	}
	
	public boolean sStatus()
	{
		return s.isClosed();
	}

}