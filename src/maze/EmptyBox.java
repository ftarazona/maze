package maze;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;


/** Basic box with maximum practicability. */
public class EmptyBox extends Box	{

	public static final int		ID	= 'E';
	private static final int	ARGS	= 4;
	private static final int	SPRITE	= 0;

	/** Constructs an EmptyBox with specified arguments.
	 *  @param args arguments, must contain 4 arguments.
	 *  @throws InvalidBoxArgumentsException if not given correct
	 *  number of arguments. */
	public EmptyBox(int[] args)	
		throws InvalidBoxArgumentsException	{
		super(args);
		if(args.length < ARGS)	{
			throw new InvalidBoxArgumentsException(args.length, ARGS);
		}
		drawid = SPRITE;
	}

	public int getPracticability()	{
		return 1;
	}

	public void write(OutputStream out)
		throws IOException	{
		
		out.write(ID);
		writeGeneralData(out);
	}

	public void display(PrintStream out)	{
		out.print("O");
	}
}
