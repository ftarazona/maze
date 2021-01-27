package ui;

import java.util.*;
import java.io.*;
import maze.*;
import dijkstra.*;
import graphics.*;
import javax.swing.event.*;

public class GraphicalInterface extends CoreInterface implements UserInterface	{

	private ArrayList<ChangeListener> listeners 	= new ArrayList<ChangeListener>();

	private InterfaceableMaze 	maze		= new Maze();
	private PiFunction 		pi		= new PiFunction();
	private PreviousFunction 	previous	= new PreviousFunction();

	public GraphicalInterface()	
		throws IOException	{
		super();
		MazeApp app = new MazeApp(this);
		addObserver(app);
	}

	public void addObserver(ChangeListener listener)	{
		listeners.add(listener);
	}

	public void stateChanges()	{
		ChangeEvent evt = new ChangeEvent(this);
		for(ChangeListener listener: listeners)
			listener.stateChanged(evt);
	}

	public InterfaceableMaze getMaze()	{ return maze; }
	public Pi getPi()			{ return pi; }
	public Previous getPrevious()		{ return previous; }

	public void addMaze(int width, int height)	{
		try	{
			maze.newMaze(height, width);
		} catch (Exception e)	{ raiseException(e); }
		stateChanges();
	}
	public void addMaze(String filename)	{
		try	{	
			InputStream file	= new FileInputStream(filename);
			BufferedInputStream buf	= new BufferedInputStream(file);
			maze.read(buf);
			buf.close();
			file.close();
		} catch (Exception e)	{ raiseException(e); }
		stateChanges();
	}
	public void saveMaze(String filename)	{
		try	{
			FileOutputStream file	= new FileOutputStream(filename);
			BufferedOutputStream buf = new BufferedOutputStream(file);
			maze.write(buf);
			buf.close();
			file.close();
		} catch (Exception e)	{ raiseException(e); }
		stateChanges();
	}

	public void addScript(String filename)	{}
	
	public void displayMaze()	{ stateChanges(); }

	public void run()	{} 
}
