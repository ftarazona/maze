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
	private int area;

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

	/** Returns a list of all the vertices in the graph, including
	 *  DummyBoxes.
	 *  @return a list of all the vertices. */
	public ArrayList<Vertex> getVertices()	{
		ArrayList<Vertex> ret = new ArrayList<Vertex>(width * height);

		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				if(boxes[i][j] != null)	{
					ret.add((Vertex)boxes[i][j]);	
				}
			}
		}

		return ret;
	}

	/** Returns a list of the successors of a given vertex, 
	 *  including DummyBoxes.
	 *  @param v is the parent vertex.
	 *  @return a list of the successors of parent. */
	public ArrayList<Vertex> getSuccessors(Vertex v)	{
		ArrayList<Vertex> ret = new ArrayList<Vertex>(4);
		Box b = (Box) v;
		int x = b.getX();
		int y = b.getY();

		if(x > 0 && boxes[y][x - 1] != null)	{
			ret.add((Vertex)boxes[y][x - 1]);
		}
		if(y > 0 && boxes[y - 1][x] != null)	{
			ret.add((Vertex)boxes[y - 1][x]);
		}
		if(x + 1 < width && boxes[y][x + 1] != null)	{
			ret.add((Vertex)boxes[y][x + 1]);
		}
		if(y + 1 < height && boxes[y + 1][x] != null)	{
			ret.add((Vertex)boxes[y + 1][x]);
		}

		return ret;
	}

	/** Returns the distance between two vertices.
	 *  @param src and dst are the two vertices to consider.
	 *  @return the distance between src and dst. */
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

	/** Returns the area of the maze, DummyBoxes excluded. 
	 *  @return the number of boxes in the graph. */
	public int size()	{
		return area;
	}

	/** Returns a list of vertices carrying a given flag.
	 *  @param flag is the flag to be looked for.
	 *  @return a list of vertices carrying the flag. */
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

	/** Gives all the vertices in the list the given flag.
	 *  @param sel is a list of the vertices to flag.
	 *  @param flag is the flag to be added. */
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

	/** Clears all the flags of every vertex in the graph. */
	public void clearAll()	{
		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				if(boxes[i][j] != null)	{
					boxes[i][j].clearFlags();
				}
			}
		}
	}

	/** Writes the graph in an output stream.
	 *  @param out is the output stream to be written.
	 *  @throws IOException if an I/O error occurs. */
	public void write(OutputStream out)	
		throws IOException	{

		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				boxes[i][j].write(out);
			}
		}
	}

	/** Converts a box list to a matrix, determines the size of it
	 *  from the coordinates of the vertices.
	 *  @param boxList is the list of vertices to be converted. */
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
				boxes[i][j] = null;
			}
		}

		for(Box b: boxList)	{
			boxes[b.getY()][b.getX()] = b;
			area++;
		}
	}

	/** Reads a graph from an input stream.
	 *  @param in is the input stream to be read.
	 *  @throws IOException if an I/O error occurs. */
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


	public void addRow(int pos)	
		throws MazeOutOfBounds	{

		if(pos < 0 || pos > height)	{
			throw new MazeOutOfBounds();
		}

		Box[][] temp = new Box[height + 1][width];

		for(int i = 0; i < pos; i++)	{
			for(int j = 0; j < width; j++)	{
				temp[i][j] = boxes[i][j];
			}
		}
		for(int j = 0; j < width; j++)	{
			temp[pos][j] = null;
		}
		for(int i = pos; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				temp[i + 1][j] = boxes[i][j];
				boxes[i][j].setX(j);
				boxes[i][j].setY(i + 1);
			}
		}
		boxes = temp;
		height++;
	}

	public void remRow(int pos)	
		throws MazeOutOfBounds	{

		if(pos < 0 || pos >= height)	{
			throw new MazeOutOfBounds();
		}

		Box[][] temp = new Box[height - 1][width];

		for(int i = 0; i < pos; i++)	{
			for(int j = 0; j < width; j++)	{
				temp[i][j] = boxes[i][j];
			}
		}
		for(int j = 0; j < width; j++)	{
			if(boxes[pos][j] == null)	{
				area--;
			}
		}
		for(int i = pos; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				temp[i - 1][j] = boxes[i][j];
				boxes[i][j].setX(j);
				boxes[i][j].setY(i - 1);
			}
		}
		boxes = temp;
		height--;
	}

	public void addCol(int pos)	
		throws MazeOutOfBounds	{

		if(pos < 0 || pos > width)	{
			throw new MazeOutOfBounds();
		}

		Box[][] temp = new Box[height][width + 1];

		for(int j = 0; j < pos; j++)	{
			for(int i = 0; i < height; i++)	{
				temp[i][j] = boxes[i][j];
			}
		}
		for(int i = 0; i < height; i++)	{
			temp[i][pos] = null;
		}
		for(int j = pos; j < width; j++)	{
			for(int i = 0; i < height; i++)	{
				temp[i][j + 1] = boxes[i][j];
				boxes[i][j].setX(j + 1);
				boxes[i][j].setY(i);
			}
		}
		boxes = temp;
		width++;
	}

	public void remCol(int pos)	
		throws MazeOutOfBounds	{

		if(pos < 0 || pos >= width)	{
			throw new MazeOutOfBounds();
		}

		Box[][] temp = new Box[height][width - 1];

		for(int j = 0; j < pos; j++)	{
			for(int i = 0; i < height; i++)	{
				temp[i][j] = boxes[i][j];
			}
		}
		for(int i = 0; i < height; i++)	{
			if(boxes[i][pos] == null)	{
				area--;
			}
		}
		for(int j = pos; j < width; j++)	{
			for(int i = 0; i < height; i++)	{
				temp[i][j - 1] = boxes[i][j];
				boxes[i][j].setX(j - 1);
				boxes[i][j].setY(i);
			}
		}
		boxes = temp;
		width--;

	}

	public void addBox(Box newBox)
		throws MazeOutOfBounds	{
		
		int x = newBox.getX();
		int y = newBox.getY();

		if(x < 0 || x >= width || y < 0 || y >= height)	{
			throw new MazeOutOfBounds();
		}

		if(boxes[y][x] == null)	{
			area--;
		}

		boxes[y][x] = newBox;
	}

	public void remBox(int x, int y)	
		throws MazeOutOfBounds	{

		if(x < 0 || x >= width || y < 0 || y >= height)	{
			throw new MazeOutOfBounds();
		}

		boxes[y][x] = null;
		area--;
	}
}
