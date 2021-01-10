package graph;

public class InvalidBoxArgumentsException extends Exception	{

	public static final long serialVersionUID = 202101090118L;
	private int expected;
	private int received;

	public InvalidBoxArgumentsException(int received, int expected)	{
		this.expected = expected;
		this.received = received;
	}

	public int getExpected()	{
		return expected;
	}

	public int getReceived()	{
		return received;
	}

	public String getMessage()	{
		return String.format("Box received %d arguments, %d expected.", expected, received);
	}
}
