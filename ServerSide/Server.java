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