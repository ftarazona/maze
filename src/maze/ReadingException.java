package maze;

public class ReadingException extends MazeException	{

	public static final long serialVersionUID = 202101151944L;
	private String str;

	public ReadingException(int iBox, String str)	{ this.str = String.format("Could not parse given stream : box %d : %s", iBox, str); }
	public String getMessage()	{ return str; }
}
