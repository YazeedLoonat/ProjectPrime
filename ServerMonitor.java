import java.math.BigInteger;
public class ServerMonitor {
	private BigInteger current;
	private BigInteger two = new BigInteger("2");

	public ServerMonitor() {
		current = two;
		insert(current);
		current = current.add(new BigInteger("1"));
	}

	public synchronized void insert(BigInteger toInsert) {
		//TODO: figure out how to insert into an AWS database
	}

	public synchronized Response getNext() {
		Response resp = new Response(Response.ResponseType.RESPONSE, current);
		current = current.add(two);
		return resp;
	}

	public BigInteger getCurrent() {
		return current;
	}
}