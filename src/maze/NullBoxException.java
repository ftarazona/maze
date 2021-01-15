package maze;

public class NullBoxException extends MazeException	{

	public static final long serialVersionUID = 202101152327L;

	public NullBoxException()	{}
	public String getMessage()	{ return "Trying to modify a null box."; }
}
