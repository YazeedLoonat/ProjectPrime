import java.net.*;
import java.io.*;
import java.math.BigInteger;
public class Client {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("", 1917);

			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

			OutputStream outputStream = socket.getOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		ClientMonitor mon = new ClientMonitor(socket, inputStream, objectInputStream, outputStream, objectOutputStream);
		ClientHelper ch;
		for(int i = 0; i < 5; i++) {
			ch = new ClientHelper(mon, i);
			ch.start();
		}
	}
}