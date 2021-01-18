package maze;

/** Signals the maze has encountered an issue of some sort. */
public abstract class MazeException extends Exception	{

	public static final long serialVersionUID = 202101151546L;
	private final String str;

	/** Constructs a new MazeException with no message. */
	public MazeException()	{ str = new String(); }

	/** Constructs a new MazeException with specified message.
	 *  @param str a detailed report about the error. */
	public MazeException(String str)	{ this.str = str; }

	public String getMessage()	{ return str; }
}
