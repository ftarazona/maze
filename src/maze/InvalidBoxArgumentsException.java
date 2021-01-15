package maze;

public class InvalidBoxArgumentsException extends MazeException	{

	public static final long serialVersionUID = 202101151553L;
	private int expected;
	private int received;

	public InvalidBoxArgumentsException(int received, int expected)	{
		this.received = received;
		this.expected = expected;
	}

	public int getExpected()	{
		return expected;
	}

	public int getReceived()	{
		return received;
	}

	@Override
	public String getMessage()	{
		return String.format("Maze : Box constructor received %d arguments, %d expected at least.", received, expected);
	}
}
