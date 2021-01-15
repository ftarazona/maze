package graph;

public abstract class MazeException extends Exception	{
	public static final long serialVersionUID = 202101151546L;
	private final String str;

	public MazeException()	{ str = new String(); }
	public MazeException(String str)	{ this.str = str; }

	public String getMessage()	{ return str; }
}
