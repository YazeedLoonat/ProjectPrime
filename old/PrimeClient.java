import java.net.*;
import java.io.*;
import java.util.ArrayList;
/*
	main client holds the list of primes, and the monitor, as well as the client support threads
*/
public class PrimeClient
{
	static CliMon mon;
	public static void main(String args[])
	{		
		try
		{
			//Socket s = new Socket("10.192.144.85",1917);
			Socket s = new Socket("harpoon.cs.arizona.edu",1917);
			System.out.println("connected");
			
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			
			mon = new CliMon(s, is, ois, os, oos);
			CliThread t;
			for(int i = 0; i < 3; i++)
			{
				t = new CliThread(mon);
				t.start();
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
}
