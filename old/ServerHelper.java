import java.net.*;
import java.io.*;
import java.util.ArrayList;
/*
	Supporting server thread
	gets, sends and forwards responses from the client that is connected to this server support
*/
public class ServerHelper extends Thread
{
    Socket s;	
	Response r;	
	SerMon mon;
	boolean test;
    public ServerHelper(Socket socket, SerMon m, boolean t) 
	{
        s = socket;
		mon = m;
		test = t;
    }
	
    public void run()
	{
		System.out.println("started");
		try 
		{	
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
		
			int x = 0;
			while(test)
			{
				try 
				{		
					if(test)
						r = (Response)ois.readObject();
					else
					{
						Response r5 = new Response("done",new Long(15), mon.getPrimes());
						parse(r5, oos);
						break;
					}
					if (r != null)
					{
						x = parse(r, oos);
					}				
					if(x == 1)
						break;
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			ois.close();
			is.close();
			oos.close();			
			os.close();			
			s.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
    }
	
	private int parse(Response r, ObjectOutputStream oos) throws IOException
	{
		String c = r.getCommand();
		Long l = r.getNum();
		Response r2;
		Long emergency = new Long(-1);
		
		if (c.equals("found"))
		{
			System.out.println("Found something " + l);
			mon.insert(l);
			r2 = new Response("ack", emergency, null);
			oos.writeObject(r2);
			return 0;
		}
		
		else if (c.equals("request"))
		{
			r2 = new Response ("response", mon.curr(), mon.getPrimes());
			mon.up();
			oos.writeObject(r2);
			return 0;
		}
		else
		{
			r2 = new Response("done", emergency, null);
			oos.writeObject(r2);
			return 1;
		}
	}	
}