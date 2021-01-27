package ui;

import java.io.*;
import maze.*;

/** Interfaces a command callable by a user interface. */
public interface CommandInterface	{

	/** Runs the command with specified arguments. 
	 *  @param args Arguments passed to the command. 
	 *  @throws IncorrectUsageException if the number of arguments
	 *  passed is incorrect. */
	public void run()
		throws UIException, MazeException, IOException;

	/** Returns details about how to use the command. 
	 *  @return Details about how to use the command. */
	public String usage();

	/** Returns a description of the command. 
	 *  @return A description of the command. */
	public String help();
}
