import java.net.*;
import java.io.*;
import java.math.BigInteger;
public class ClientMonitor {
	private Socket socket;
	private InputStream inputStream;
	private ObjectInputStream objectInputStream;
	private OutputStream outputStream;
	private ObjectOutputStream objectOutputStream;

	public ClientMonitor(Socket s, InputStream is, ObjectInputStream ois, OutputStream os, ObjectOutputStream oos) {
		socket = s;
		inputStream = is;
		objectInputStream = ois;
		outputStream = os;
		objectOutputStream = oos;
	}

	public synchronized Response reply(Response resp) throws IOException {
		if(socket.isClosed()) {
			objectInputStream.close();
			is.close();
			objectOutputStream.close();
			outputStream.close();
		}

		try {
			objectOutputStream.writeObject(resp);
			return (Response)objectInputStream.readObject();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
}