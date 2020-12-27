package graph;

import java.util.Arrays;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import fileops.IOInterface;
import fileops.BadFormatException;
import fileops.ReadingException;

public class Maze
	implements Graph, IOInterface	{

	private Box[][] boxes;
	private int width;
	private int height;

	/** Constructs an empty maze */
	public Maze()	{}

	/** Constructs a maze from a matrix of boxes.
	 *  @param boxes a matrix of boxes. */
	public Maze(Box[][] boxes)	{
		this.boxes = boxes;
		this.width = boxes[0].length;
		this.height = boxes.length;
	}


	public void display()	{
		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				boxes[i][j].display();
			}
			System.out.println("");
		}
	}

	/* To get a list of all the vertices, we need to traverse the
	 *  whole matrix. */

	public ArrayList<Vertex> getVertices()	{
		ArrayList<Vertex> ret = new ArrayList<Vertex>(width * height);

		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				ret.add((Vertex)boxes[i][j]);	
			}
		}

		return ret;
	}

	/* The successors of a box is its neighbours, IF they e'xist.
	 *  the conditional statements ensure to be within the bounds
	 *  of the matrix. */

	public ArrayList<Vertex> getSuccessors(Vertex v)	{
		ArrayList<Vertex> ret = new ArrayList<Vertex>(4);
		Box b = (Box) v;
		int x = b.getX();
		int y = b.getY();

		if(x > 0)	{
			ret.add((Vertex)boxes[y][x - 1]);
		}
		if(y > 0)	{
			ret.add((Vertex)boxes[y - 1][x]);
		}
		if(x + 1 < width)	{
			ret.add((Vertex)boxes[y][x + 1]);
		}
		if(y + 1 < height)	{
			ret.add((Vertex)boxes[y + 1][x]);
		}

		return ret;
	}

	/* The distance is given by the sum of the weights determined
	 *  by the type of each box. */

	public int distance(Vertex src, Vertex dst)	{
		int wsrc = ((Box)src).getPracticability();
		int wdst = ((Box)dst).getPracticability();
		
		if(wsrc == distant || wdst == distant)	{
			return distant;
		}
		else	{
			return wsrc + wdst;
		}
	}

	public int size()	{
		return width * height;
	}


	/* Selection and marking operation */

	public ArrayList<Vertex> getSelection(int flag)	{
		ArrayList<Vertex> ret = new ArrayList<Vertex>();

		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				if(boxes[i][j].hasFlag(flag))	{
					ret.add((Vertex)boxes[i][j]);
				}
			}
		}

		return ret;
	}

	public void setSelection(ArrayList<Vertex> sel, int flag)	{
		for(Vertex v: sel)	{
			boxes[((Box)v).getY()][((Box)v).getX()].addFlag(flag);
		}
	}

	public void clearSelection(ArrayList<Vertex> sel, int flag)	{
		for(Vertex v: sel)	{
			boxes[((Box)v).getY()][((Box)v).getX()].remFlag(flag);
		}
	}

	public void clearAll()	{
		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				boxes[i][j].clearFlags();
			}
		}
	}



	/* IO Operations */

	/* Loading a maze from file consists in parsing a map file and
	 *  create boxes of type depending on the letter read.
	 * The method reads each line, and for each line reads each
	 *  character. If some line is longer or shorter, it raises an
	 *  exception of bad map format. */

	public void write(OutputStream out)	
		throws IOException	{

		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				boxes[i][j].write(out);
			}
		}
	}

	private void convertBoxList(ArrayList<Box> boxList)	{
		int maxX = 0, maxY = 0;

		for(Box b: boxList)	{
			if(b.getX() > maxX)	{ maxX = b.getX(); }
			if(b.getY() > maxY)	{ maxY = b.getY(); }
		}

		width = maxX + 1;
		height = maxY + 1;
		boxes = new Box[height][width];

		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				boxes[i][j] = new DummyBox(j, i);
			}
		}

		for(Box b: boxList)	{
			boxes[b.getY()][b.getX()] = b;
		}
	}

	public void read(InputStream in)	
		throws IOException, BadFormatException, ReadingException	{
		ArrayList<Box> boxList = new ArrayList<Box>();
		int c = in.read();

		while(c != -1)	{
			Box newBox = null;
			System.out.println(c);
			switch(c)	{
				case Box.BOX_WALL:
					newBox = new WallBox();
					break;
				case Box.BOX_EMPTY:
					newBox = new EmptyBox();
					break;
				case Box.BOX_WATER:
					newBox = new WaterBox();
					break;
				case Box.BOX_BRIDGE:
					newBox = new BridgeBox();
					break;
				case Box.BOX_STAIRS:
					newBox = new StairsBox();
					break;
				default:
					while(in.read() != 255 && in.read() != -1);
					break;
			}
			if(newBox != null)	{
				try	{
					newBox.read(in);
					boxList.add(newBox);
				} catch (Exception e)	{
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
			c = in.read();
		}

		convertBoxList(boxList);
	}
}
