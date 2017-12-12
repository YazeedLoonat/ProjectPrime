import java.net.*;
import java.io.*;
import java.math.BigInteger;
public class Server {
	public static void main(String[] args) throws IOException {
		ServerMonitor mon = new ServerMonitor();

		ServerSocket ss;
		Socket so;
		try {
			ss = new ServerSocket(1917);
			while(true) {
				so = ss.accept();
				ServerHelper sh = new ServerHelper(so, mon);
				sh.start();
			}
			//ss.close(); this line is currently unreachable
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}


/*
	Notes:
		As each client connects spin up a new ServerHelper to manage the connection.
		The ServerMonitor serves to hold, increment, and insert the current value as atomic statements. Thus protecting against some of the dangers of parallel programing
	Resources:
		//http://docs.oracle.com/javase/6/docs/api/java/math/BigInteger.html
*/