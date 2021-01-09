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
	implements Graph	{

	private Box[][] boxes;
	private int width;
	private int height;
	private int area;

	/** Constructs an empty maze */
	public Maze()	{
	}

	public Maze(int height, int width, int type)	
		throws InvalidBoxArgumentsException	{
		this();
		newMaze(height, width, type);
	}

	public void newMaze(int height, int width, int boxID)	
		throws InvalidBoxArgumentsException	{
		boxes = new Box[height][width];
		this.height = height;
		this.width = width;
		this.area = 0;
		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				try	{
					boxes[i][j] = null;
					int[] args = new int[MazeContext.getNbArgs(boxID)];
					args[0] = j;
					args[1] = i;
					addBox(boxID, args);
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

	public void displayAll()	{
		System.out.print("  ");
		for(int j = 0; j < width; j++)	{
			System.out.print(j);
		}
		System.out.println("");
		for(int i = 0; i < height; i++)	{
			System.out.print(i + " ");
			for(int j = 0; j < width; j++)	{
				if(boxes[i][j] != null)	{
					if(boxes[i][j].hasNoFlag())	{
						boxes[i][j].display();
					} else	{
						boxes[i][j].displayFlags();
					}
				}
				else	{
					System.out.print(" ");
				}
			}
			System.out.println("");
		}
	}


	public void display(BoxFlag flag)	{
		System.out.print("  ");
		for(int j = 0; j < width; j++)	{
			System.out.print(j);
		}
		System.out.println("");
		for(int i = 0; i < height; i++)	{
			System.out.print(i + " ");
			for(int j = 0; j < width; j++)	{
				if(boxes[i][j] != null && boxes[i][j].hasFlag(flag))	{
					boxes[i][j].displayFlag(flag);
				}
				else	{
					System.out.print(" ");
				}
			}
			System.out.println("");
		}
	}

	public void displayFlags()	{
		System.out.print("  ");
		for(int j = 0; j < width; j++)	{
			System.out.print(j);
		}
		System.out.println("");
		for(int i = 0; i < height; i++)	{
			System.out.print(i + " ");
			for(int j = 0; j < width; j++)	{
				if(boxes[i][j] != null && !boxes[i][j].hasNoFlag())	{
					boxes[i][j].displayFlags();
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
		if(src == null || dst == null)	{
			return distant;
		}

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
				if(boxes[i][j] == null && boxes[i][j].hasFlag(flag))	{
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

	public void addFlag(int x, int y, BoxFlag flag)	
		throws MazeOutOfBoundsException	{
		if(flag.equals(BoxFlag.BOX_START))	{
			setRoot(x, y);
		} else	{
			try	{
				boxes[y][x].addFlag(flag);
			} catch (IndexOutOfBoundsException e)	{
				throw new MazeOutOfBoundsException();
			}
		}
	}

	public boolean setRoot(int x, int y)	
		throws MazeOutOfBoundsException	{
		try	{
			boxes[y][x].addFlag(BoxFlag.BOX_START);
		} catch (IndexOutOfBoundsException e)	{
			throw new MazeOutOfBoundsException();
		}
		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				if(i != y || j != x && boxes[j][i].hasFlag(BoxFlag.BOX_START))	{
					boxes[i][j].remFlag(BoxFlag.BOX_START);
					return true;
				}
			}
		}
		return false;
	}

	public void remFlag(int x, int y, BoxFlag flag)	
		throws MazeOutOfBoundsException	{
		try	{
			boxes[y][x].remFlag(flag);
		} catch (IndexOutOfBoundsException e)	{
			throw new MazeOutOfBoundsException();
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
					out.write(255);
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

	private Box readBox(InputStream in)
		throws IOException, BadFormatException	{
		ArrayList<Integer> input = new ArrayList<Integer>();

		int c;
		do	{
			c = in.read();
			input.add(Integer.valueOf(c));
		} while(c != 255 && c != -1);

		try	{
			int boxID = input.get(0);
			int[] args = new int[input.size() - 1];
			for(int i = 1; i < input.size() - 1; i++)	{
				args[i] = input.get(i);
			}
			
			Box box = MazeContext.newBox(boxID, args);
			return box;
		} catch(IndexOutOfBoundsException e)	{
			throw new BadFormatException(1, 0);
		} catch(InvalidBoxArgumentsException e)	{
			throw new BadFormatException(e.getExpected(), e.getReceived());
		}
	}

	/** Reads a graph from an input stream.
	 *  @param in is the input stream to be read.
	 *  @throws IOException if an I/O error occurs. */
	public void read(InputStream in)	
		throws IOException, BadFormatException	{

		ArrayList<Box> boxList = new ArrayList<Box>();
		Box box = readBox(in);
		int xMax = -1, yMax = -1;
		
		while(box != null)	{
			if(box.getX() > xMax)	{ xMax = box.getX(); }
			if(box.getY() > yMax)	{ yMax = box.getY(); }
			boxList.add(box);
			box = readBox(in);
		}

		width = xMax + 1;
		height = yMax + 1;
		boxes = new Box[height][width];

		for(Box b : boxList)	{
			if(boxes[b.getY()][b.getX()] == null)	{
				area++;
			}
			boxes[b.getY()][b.getX()] = b;
		}
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
			int[] args = new int[MazeContext.getNbArgs(type)];
			args[0] = j;
			args[1] = pos;
			try	{
				addBox(type, args);
			} catch(Exception e)	{}
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
			int[] args = new int[MazeContext.getNbArgs(type)];
			args[0] = pos;
			args[1] = i;
			try	{
				addBox(type, args);
			} catch(Exception e)	{}
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

	public void addBox(int type, int[] args)
		throws MazeOutOfBoundsException, InvalidBoxArgumentsException	{

		if(args.length < 2)	{
			throw new InvalidBoxArgumentsException(2, args.length);
		}
		int x = args[0];
		int y = args[1];

		if(x < 0 || x >= width || y < 0 || y >= height)	{
			throw new MazeOutOfBoundsException();
		}

		if(boxes[y][x] == null && type != MazeContext.NULL_ID)	{
			area++;
		}

		boxes[y][x] = MazeContext.newBox(type, args);
	}

	public void remBox(int x, int y)	
		throws MazeOutOfBoundsException	{

		if(x < 0 || x >= width || y < 0 || y >= height)	{
			throw new MazeOutOfBoundsException();
		}

		boxes[y][x] = null;
		area--;
	}

	public Box getBox(int x, int y)
		throws MazeOutOfBoundsException	{

		if(x < 0 || x >= width || y < 0 || y >= height || boxes[y][x] == null)	{
			throw new MazeOutOfBoundsException();
		}

		return boxes[y][x];
	}
}
