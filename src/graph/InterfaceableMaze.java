package graph;

import java.util.ArrayList;
import java.io.PrintStream;
import java.io.*;

public interface InterfaceableMaze extends Graph	{

	public void newMaze(int height, int width, int boxType)
		throws UnexpectedBoxTypeException;
	public void read(InputStream in)
		throws IOException, ReadingException;
	public void write(OutputStream out)
		throws IOException;
	public boolean isOpened();

	public void display(PrintStream out);
	public void show(BoxFlag flag);
	public void hide(BoxFlag flag);
	public void showAllFlags();
	public void hideAllFlags();

	public int size();
	public int getHeight();
	public int getWidth();

	public ArrayList<Vertex> getSelection(BoxFlag flag);
	public void setSelection(ArrayList<Vertex> sel, BoxFlag flag);
	public void clearSelection(ArrayList<Vertex> sel, BoxFlag flag);
	public void clear();
	public void addFlag(int x, int y, BoxFlag flag)
		throws MazeOutOfBoundsException;
	public void remFlag(int x, int y, BoxFlag flag)
		throws MazeOutOfBoundsException;
	public void remRoot();
	public boolean setRoot(int x, int y)
		throws MazeOutOfBoundsException;

	public void addRow(int pos, int boxType)
		throws MazeOutOfBoundsException, UnexpectedBoxTypeException;
	public void remRow(int pos)
		throws MazeOutOfBoundsException;
	public void addCol(int pos, int boxType)
		throws MazeOutOfBoundsException, UnexpectedBoxTypeException;
	public void remCol(int pos)
		throws MazeOutOfBoundsException;
	public void addBox(int type, int[] args)
		throws MazeOutOfBoundsException, InvalidBoxArgumentsException, UnexpectedBoxTypeException;
	public void remBox(int x, int y)
		throws MazeOutOfBoundsException;
	public Vertex getBox(int x, int y)
		throws MazeOutOfBoundsException;
}
