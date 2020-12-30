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
	public Maze()	{
	}

	public Maze(int height, int width, int type)	{
		this();
		newMaze(height, width, type);
	}

	public void newMaze(int height, int width, int type)	{
		boxes = new Box[height][width];
		this.height = height;
		this.width = width;
		this.area = 0;
		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				try	{
					boxes[i][j] = null;
					addBox(j, i, 0, type);
				} catch (MazeOutOfBoundsException e) {}
			}
		}
	}

	/** Constructs a maze from a matrix of boxes.
	 *  @param boxes a matrix of boxes. */
	public Maze(Box[][] boxes)	{
		this();
		this.boxes = boxes;
		this.width = boxes[0].length;
		this.height = boxes.length;
	}


	public void display()	{
		System.out.print("  ");
		for(int j = 0; j < width; j++)	{
			System.out.print(j);
		}
		System.out.println("");
		for(int i = 0; i < height; i++)	{
			System.out.print(i + " ");
			for(int j = 0; j < width; j++)	{
				if(boxes[i][j] != null)	{
					boxes[i][j].display();
				}
				else	{
					System.out.print(" ");
				}
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
	public ArrayList<Vertex> getSelection(BoxFlag flag)	{
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
	public void setSelection(ArrayList<Vertex> sel, BoxFlag flag)	{
		for(Vertex v: sel)	{
			boxes[((Box)v).getY()][((Box)v).getX()].addFlag(flag);
		}
	}

	public void clearSelection(ArrayList<Vertex> sel, BoxFlag flag)	{
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
				if(boxes[i][j] != null)	{
					boxes[i][j].write(out);
				}
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
			switch(c)	{
				case BoxContext.WALL_ID:
					newBox = new WallBox();
					break;
				case BoxContext.EMPTY_ID:
					newBox = new EmptyBox();
					break;
				case BoxContext.WATER_ID:
					newBox = new WaterBox();
					break;
				case BoxContext.BRIDGE_ID:
					newBox = new BridgeBox();
					break;
				case BoxContext.STAIRS_ID:
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


	public void addRow(int pos, int type)	
		throws MazeOutOfBoundsException	{

		if(pos < 0 || pos > height)	{
			throw new MazeOutOfBoundsException();
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
				if(boxes[i][j] != null)	{
					boxes[i][j].setX(j);
					boxes[i][j].setY(i + 1);
				}
			}
		}
		boxes = temp;
		height++;
		
		for(int j = 0; j < width; j++)	{
			addBox(j, pos, 0, type);
		}
	}

	public void remRow(int pos)	
		throws MazeOutOfBoundsException	{

		if(pos < 0 || pos >= height)	{
			throw new MazeOutOfBoundsException();
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
		for(int i = pos; i < height - 1; i++)	{
			for(int j = 0; j < width; j++)	{
				temp[i][j] = boxes[i + 1][j];
				if(boxes[i + 1][j] != null)	{
					boxes[i + 1][j].setX(j);
					boxes[i + 1][j].setY(i);
				}
			}
		}
		boxes = temp;
		height--;
	}

	public void addCol(int pos, int type)	
		throws MazeOutOfBoundsException	{

		if(pos < 0 || pos > width)	{
			throw new MazeOutOfBoundsException();
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
				if(boxes[i][j] != null)	{
					boxes[i][j].setX(j + 1);
					boxes[i][j].setY(i);
				}
			}
		}
		boxes = temp;
		width++;
		for(int i = 0; i < height; i++)	{
			addBox(pos, i, 0, type);
		}
	}

	public void remCol(int pos)	
		throws MazeOutOfBoundsException	{

		if(pos < 0 || pos >= width)	{
			throw new MazeOutOfBoundsException();
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
		for(int j = pos; j < width - 1; j++)	{
			for(int i = 0; i < height; i++)	{
				temp[i][j] = boxes[i][j + 1];
				if(boxes[i][j + 1] != null)	{
					boxes[i][j + 1].setX(j);
					boxes[i][j + 1].setY(i);
				}
			}
		}
		boxes = temp;
		width--;

	}

	public void addBox(int x, int y, int z, int type)
		throws MazeOutOfBoundsException	{

		if(x < 0 || x >= width || y < 0 || y >= height)	{
			throw new MazeOutOfBoundsException();
		}

		if(boxes[y][x] == null && type != BoxContext.NULL_ID)	{
			area++;
		}

		Box box = null;
		switch (type)	{
			case BoxContext.WALL_ID:
				box = new WallBox(x, y, z);
				break;
			case BoxContext.EMPTY_ID:
				box = new EmptyBox(x, y, z);
				break;
			case BoxContext.WATER_ID:
				box = new WaterBox(x, y, z, 0);
				break;
			case BoxContext.BRIDGE_ID:
				box = new BridgeBox(x, y, z);
				break;
			case BoxContext.STAIRS_ID:
				box = new StairsBox(x, y, z, 0);
				break;
			default: break;
		}
		
		boxes[y][x] = box;
	}

	public void remBox(int x, int y)	
		throws MazeOutOfBoundsException	{

		if(x < 0 || x >= width || y < 0 || y >= height)	{
			throw new MazeOutOfBoundsException();
		}

		boxes[y][x] = null;
		area--;
	}
}
