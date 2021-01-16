package maze;

public class UnexpectedBoxTypeException extends MazeException	{

	public static final long serialVersionUID = 202101151626L;
	private int boxType;

	public UnexpectedBoxTypeException()	{ boxType = 255; }
	public UnexpectedBoxTypeException(int boxType)	{ this.boxType = boxType; }

	@Override
	public String getMessage()	{
		if(boxType == 255)	{
			return "No box type found.\n";
		}
		else	{
			return String.format("Unexpected box type %d.\n", boxType);
		}
	}
}
