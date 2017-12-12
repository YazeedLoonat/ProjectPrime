import java.math.BigInteger;
public class ClientHelper extends Thread {
	private ClientMonitor mon;
	private int name;
	private BigInteger two = new BigInteger("2");
	private BigInteger zero = new BigInteger("0");

	public ClientHelper(ClientMonitor clientMonitor, int i) {
		mon = clientMonitor;
		name = i;
	}

	public void run() {
		try {
			Response resp;
			Response.ResponseType respType;
			BigInteger num;
			while(true) {
				resp = mon.reply(new Response(Response.ResponseType.REQUEST, null));
				respType = resp.getCommand();
				num = resp.getNum();

				if(respType == Response.ResponseType.RESPONSE && primeTest(num)) {
					mon.reply(new Response(Response.ResponseType.FOUND, num));
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	private boolean primeTest(BigInteger num) {
		BigInteger val;

		//TODO: get values from API
		//TODO: figure out how to loop through those values
			if(num.mod(val).equals(zero)) {
				return false;
			}
		val = val.add(two);
		while(val.compareTo(num) <= 0) {
			if(num.mod(val).equals(zero)) {
				return false;
			}
			val = val.add(two);
		}
		return true;
	}
}