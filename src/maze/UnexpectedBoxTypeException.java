package maze;


/** Signals a box type was not found. */
public class UnexpectedBoxTypeException extends MazeException	{

	public static final long serialVersionUID = 202101151626L;
	private int boxType;

	/** Constructs an UnexpectedBoxTypeException indicating
	 *  a string could be converted. */
	public UnexpectedBoxTypeException()	{ boxType = 255; }

	/** Constructs an UnexpectedBoxTypeException with specified
	 *  box type. */
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
