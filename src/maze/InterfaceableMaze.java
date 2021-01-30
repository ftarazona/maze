package maze;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;

import java.util.ArrayList;

/** This interface describes a maze which can be modified by an
 *  interface. */
public interface InterfaceableMaze extends Graph	{

	/** Creates a new maze of given dimensions, filling it with
	 *  a given type of boxes.
	 *  @param height height of the new maze.
	 *  @param width width of the new maze.
	 *  @param boxType type of the boxes to fill the maze with.
	 *  @throws UnexpectedBoxTypeException if the given box type
	 *  does not match with any. */
	public void newMaze(int height, int width, int[] boxArgs)
		throws UnexpectedBoxTypeException, InvalidBoxArgumentsException;

	public void newMaze(Box[][] boxes);

	/** Reads a new maze from a given input stream.
	 *  @param in input stream to be read.
	 *  @throws IOException if an I/O error occurs.
	 *  @throws ReadingException if the method was unable to parse
	 *  the stream correctly (bad format for example) */
	public void read(InputStream in)
		throws IOException, ReadingException;

	/** Writes the maze in a given output stream.
	 *  @param out output stream to be written.
	 *  @throws IOException if an I/O error occurs. */
	public void write(OutputStream out)
		throws IOException;

	/** Indicates whether the maze is opened. 
	 *  @return true if the maze is opened, false otherwise. */
	public boolean isOpened();

	/** Removes the current boxes and closes the maze. */
	public void close();

	/** Displays the maze in a given output stream.
	 *  @param out output stream to be written. */
	public void display(PrintStream out);

	/** Requires the maze to show a flag when displaying.
	 *  @param flag flag to be displayed. */
	public void show(BoxFlag flag);
	
	/** Requires the maze to hide a flag when displaying.
	 *  @param flag flag to be hidden. */
	public void hide(BoxFlag flag);

	/** Requires the maze to show all flags when displaying. */
	public void showAllFlags();
	
	/** Requires the maze to hide all flags when displaying. */
	public void hideAllFlags();

	/** Returns the number of vertices in the maze.
	 *  @return The number of vertices in the maze. */
	public int getArea();
	
	/** Returns the height of the maze.
	 *  @return The height of the maze. */
	public int getHeight();

	/** Returns the width of the maze.
	 *  @return The width of the maze. */
	public int getWidth();

	/** Returns a list of all the boxes carrying a flag.
	 *  @param flag flag to be searched.
	 *  @return A list of all the boxes carrying flag. */
	public ArrayList<Vertex> getSelection(BoxFlag flag);

	/** Adds a flag to several boxes.
	 *  @param sel boxes to be flagged.
	 *  @param flag flag to be added. */
	public void setSelection(ArrayList<Vertex> sel, BoxFlag flag);

	/** Removes a flag to several boxes.
	 *  @param sel boxes to be unflagged.
	 *  @param flag flag to be removed. */
	public void clearSelection(ArrayList<Vertex> sel, BoxFlag flag);

	/** Clears all flags of all boxes. */
	public void clear();

	/** Adds a flag to a box.
	 *  @param x X coordinate of the box.
	 *  @param y Y coordinate of the box.
	 *  @param flag flag to be added.
	 *  @throws MazeOutOfBoundsException if the coordinates are
	 *  out of the maze dimensions.
	 *  @throws NullBoxException if there is no box to the given
	 *  coordinates. */
	public void addFlag(int x, int y, BoxFlag flag)
		throws MazeOutOfBoundsException, NullBoxException;
	
	/** Removes a flag from a box.
	 *  @param x X coordinate of the box.
	 *  @param y Y coordinate of the box.
	 *  @param flag flag to be removed.
	 *  @throws MazeOutOfBoundsException if the coordinates are
	 *  out of the maze dimensions.
	 *  @throws NullBoxException if there is no box to the given
	 *  coordinates. */
	public void remFlag(int x, int y, BoxFlag flag)
		throws MazeOutOfBoundsException, NullBoxException;

	/** Returns the current root.
	 *  @return The root of the maze. If there is none, null is
	 *  returned. */
	public Vertex getRoot();

	/** Removes the current root if it exists. */
	public void remRoot();
	
	/** Adds a BOX_START flag to a box.
	 *  Only one box in the maze can be flagged as root at a time.
	 *  @param x X coordinate of the box.
	 *  @param y Y coordinate of the box.
	 *  @throws MazeOutOfBoundsException if the coordinates are
	 *  out of the maze dimensions.
	 *  @throws NullBoxException if there is no box to the given
	 *  coordinates. */
	public boolean setRoot(int x, int y)
		throws MazeOutOfBoundsException, NullBoxException;

	/** Adds a row.
	 *  @param pos position of the new row.
	 *  @param boxType box type to fill the row with.
	 *  @throws MazeOutOfBoundsException if pos exceeds the height
	 *  of the maze.
	 *  @throws UnexpectedBoxTypeException if the given box type
	 *  does not match any. */
	public void addRow(int pos, int[] boxArgs)
		throws MazeOutOfBoundsException, UnexpectedBoxTypeException, InvalidBoxArgumentsException;

	/** Removes a row.
	 *  @param pos position of the row to be removed.
	 *  @throws MazeOutOfBoundsException if pos exceeds the height
	 *  of the maze. */
	public void remRow(int pos)
		throws MazeOutOfBoundsException;
	
	/** Adds a column.
	 *  @param pos position of the new column.
	 *  @param boxType box type to fill the column with.
	 *  @throws MazeOutOfBoundsException if pos exceeds the width
	 *  of the maze.
	 *  @throws UnexpectedBoxTypeException if the given box type
	 *  does not match any. */
	public void addCol(int pos, int[] boxArgs)
		throws MazeOutOfBoundsException, UnexpectedBoxTypeException, InvalidBoxArgumentsException;
	
	/** Removes a column.
	 *  @param pos position of the column to be removed.
	 *  @throws MazeOutOfBoundsException if pos exceeds the width
	 *  of the maze. */
	public void remCol(int pos)
		throws MazeOutOfBoundsException;

	/** Adds a new box with given type and arguments.
	 *  @param type type of the new box.
	 *  @param args an array of arguments to provide the
	 *  constructor with.
	 *  @throws MazeOutOfBoundsException if the first two
	 *  arguments given, corresponding to x and y, are out of maze
	 *  borders.
	 *  @throws InvalidBoxArgumentsException if the given
	 *  arguments could not be parsed by the Box constructor.
	 *  @throws UnexpectedBoxTypeException if the given box type
	 *  does not match any. */
	public void addBox(int[] args)
		throws MazeOutOfBoundsException, InvalidBoxArgumentsException, UnexpectedBoxTypeException;

	/** Removes the box at the given coordinates.
	 *  @param x X coordinate of the box.
	 *  @param y Y coordinate of the box.
	 *  @throws MazeOutOfBoundsException if the coordinates are
	 *  out of maze borders. */
	public void remBox(int x, int y)
		throws MazeOutOfBoundsException;

	/** Returns the vertex at given coordinates.
	 *  @param x X coordinate of the vertex.
	 *  @param y Y coordinate of the vertex.
	 *  @return The vertex at the given coordinate, might be null.
	 *  @throws MazeOutOfBoundsException if the coordinates are
	 *  out of maze border. */
	public Vertex getBox(int x, int y)
		throws MazeOutOfBoundsException;
	

	/** Returns a matrix of drawing IDs for drawing a tile map.
	 *  @return A matrix of drawing IDs. */
	public int[][] drawIDs();
	public int[][] drawFlags();
}
