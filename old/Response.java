import java.util.ArrayList;
import java.io.*;
class Response implements Serializable 
{
	String command;
	long num;
	ArrayList<Long> primes;
	public  Response(String s, Long l,ArrayList<Long> p)
	{
		command = s;
		num = l;
		primes = p;
	}
	
	public String getCommand()
	{
		return command;
	}
	
	public long getNum()
	{
		return num;
	}
	
	public ArrayList<Long> getPrimes()
	{
		return primes;
	}
}