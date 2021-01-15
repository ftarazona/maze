package graph;

import java.util.ArrayList;

public interface InterfaceableMaze	{

	public void newMaze(int height, int width, int boxType);
	public void read(InputStream in);
	public void write(OutputStream out);

	public void display(PrintStream out);
	public void show(BoxFlag flag);
	public void hide(BoxFlag flag);
	public void showAllFlags();
	public void hideAllFlags();

	public int size();
	public int getHeight();
	public int getWidth();

	public ArrayList<Vertex> getSecetion(BoxFlag flag);
	public void setSelection(ArrayList<Vertex> sel, BoxFlag flag);
	public void clearSelection(ArrayList<Vertex> sel, BoxFlag flag);
	public void clear();
	public void addFlag(int x, int y, BoxFlag flag);
	public void remFlag(int x, int y, BoxFlag flag);
	public boolean setRoot(int x, int y);

	public void addRow(int pos, int boxType);
	public void remRow(int pos);
	public void addCol(int pos, int boxType);
	public void remCol(int pos);
	public void addBox(int type, int[] args);
	public void remBox(int x, int y);
	public Vertex getBox(int x, int y);
}
