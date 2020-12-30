package fileops;

public class BadFormatException extends Exception	{

	public static final long serialVersionUID = 202012301431L;
	private int expected;
	private int received;

	public BadFormatException(int expected, int received)	{
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
		return String.format("Received %d arguments, %d expected.", expected, received);
	}
}
