package ui;

import maze.*;
import dijkstra.*;
import java.util.*;
import java.io.*;

/** This interface describes a user interface. */
public interface UserInterface	{

	/** Runs the interface.
	 *  @param args arguments passed to be treated when launching
	 *  the interface. */
	public void run(String[] args);
}
