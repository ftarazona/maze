package maze;

/** Signals an error occured while reading a maze from a stream. */
public class ReadingException extends MazeException	{

	public static final long serialVersionUID = 202101151944L;
	private String str;

	/** Construcs a new ReadingException specifying the index of
	 *  the box currently being read and a detailed report a the
	 *  error. */
	public ReadingException(int iBox, String str)	{ this.str = String.format("Could not parse given stream : box %d : %s", iBox, str); }
	public String getMessage()	{ return str; }
}
