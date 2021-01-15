package graph;

public class BadFormatException extends MazeException	{

	public static final long serialVersionUID = 202101151616L;
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
		return String.format("Maze : Received %d arguments, %d expected.", expected, received);
	}
}
