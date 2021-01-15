package ui;

import maze.*;

public interface CommandInterface	{

	public void run(String[] args)
		throws UIException, MazeException;

	public String usage();
	public String description();
}
