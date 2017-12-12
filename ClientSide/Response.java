import java.math.BigInteger;
public class Response {
	private ResponseType type;
	private BigInteger num;

	public Response(ResponseType respType, BigInteger bint) {
		type = respType;
		num = bint;
	}

	public ResponseType getCommand() {
		return type;
	}

	public BigInteger getNum() {
		return num;
	}

	public enum getResponseType {
		FOUND,		// if a prime number was found
		REQUEST,	// if a new number is required
		RESPONSE	// if responding to a client
	}
}