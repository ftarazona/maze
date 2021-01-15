package graph;

public class ReadingException extends MazeException	{

	public static final long serialVersionUID = 202101151944L;
	private String str;

	public ReadingException(String str)	{ this.str = str; }
	public String getMessage()	{ return str; }
}
