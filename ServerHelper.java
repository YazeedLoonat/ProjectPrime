import java.net.*;
import java.io.*;
import java.math.BigInteger;
public class ServerHelper extends Thread {
	private Socket socket;
	private ServerMonitor mon;
	private Response resp;

	public ServerHelper(Socket so, ServerMonitor sm) {
		socket = so;
		mon = sm;
	}

	public void run() {
		try {
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);

			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);

			while(true) {
				resp = (Response) ois.readObject();
				respond(resp, oos);
			}

			/* THESE LINES ARE CURRENTLY UNREACHABLE
				ois.close();
				is.close();
				oos.close();
				os.close();
				socket.close();
			*/
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	private void respond(Response resp, ObjectOutputStream oos) throws IOException {
		Response.ResponseType respType = resp.getResponseType();

		if(respType == Response.ResponseType.FOUND) {
			mon.insert(resp.getNum());
		}
		else if(respType == Response.ResponseType.REQUEST) {
			oos.writeObject(mon.getNext());
		}
	}
}