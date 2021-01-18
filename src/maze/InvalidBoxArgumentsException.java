package maze;

/** Signals that a box could not be built because of an incorrect
 *  number of arguments. */
public class InvalidBoxArgumentsException extends MazeException	{

	public static final long serialVersionUID = 202101151553L;
	private int expected;
	private int received;

	/** Constructs a InvalidBoxArgumentsException with specified
	 *  expected and received number of arguments. */
	public InvalidBoxArgumentsException(int received, int expected)	{
		this.received = received;
		this.expected = expected;
	}

	/** Returns the expected number of arguments.
	 *  @return The expected number of arguments. */
	public int getExpected()	{
		return expected;
	}

	/** Returns the received number of arguments.
	 *  @return The received number of arguments. */
	public int getReceived()	{
		return received;
	}

	@Override
	public String getMessage()	{
		return String.format("Box constructor received %d arguments, %d expected at least.", received, expected);
	}
}
