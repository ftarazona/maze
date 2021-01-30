package maze;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;

/** Sample box for developpers. */
public class SampleBox extends Box	{

	public static final int		ID	= 0;
	private static final int	ARGS	= 0;
	private static final int	SPRITE	= -1;

	public SampleBox(int[] args)	
		throws InvalidBoxArgumentsException	{
		super(args);
		if(args.length < ARGS)	{
			throw new InvalidBoxArgumentsException(args.length, ARGS);
		}
		drawid = SPRITE;

		//You may add other instructions here
	}

	public int getPracticability()	{return 0;}

	public void write(OutputStream out)	
		throws IOException	{

		out.write(ID);
		writeGeneralData(out);

		//You may write other information to record.
		//write must be consistent with the constructor
		//(order of the arguments)
	}

	public int compatibilityID()	{
		return ' ';
	}

	public void display(PrintStream out)	{}
}
