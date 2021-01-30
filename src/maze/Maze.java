package maze;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.EnumSet;


/** Implements the InterfaceableMaze and Graph interfaces. */
public class Maze
	implements Graph, InterfaceableMaze	{

	private Box[][] boxes;
	private int width;
	private int height;
	private int area;

	private boolean opened;
	private boolean hasRoot;
	private int xRoot, yRoot;

	EnumSet<BoxFlag> showFlag;


/* **************************************************************** */
/* ************* Constructors, loaders and savers ***************** */
/* **************************************************************** */	
	/** Constructs an empty maze with null dimensions.
	 *  The new maze is not opened. */
	public Maze()	{
		boxes = null;
		width = 0;
		height = 0;
		area = 0;
		opened = false;
		xRoot = -1;
		yRoot = -1;
		hasRoot = false;
		showFlag = EnumSet.noneOf(BoxFlag.class);
	}

	/** Constructs a new maze with given dimensions.
	 *  The maze is considered opened.
	 *  @param height height of the new maze.
	 *  @param width width of the new maze.
	 *  @throws UnexpectedBoxTypeException if type does not match
	 *  any. */
	public Maze(int height, int width)	
		throws UnexpectedBoxTypeException	{
		showFlag = EnumSet.noneOf(BoxFlag.class);
		boxes = new Box[height][width];
		this.height = height;
		this.width = width;
		this.area = 0;
		opened = true;
		hasRoot = false;
	}

	/** Creates a new maze without invoking constructor.
	 *  This method effectively opens the maze. */
	public void newMaze(int height, int width, int[] args)	
		throws UnexpectedBoxTypeException, InvalidBoxArgumentsException	{
		close();
		boxes = new Box[height][width];
		this.height = height;
		this.width = width;
		this.area = 0;
		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				if(args.length >= 3)	{
					args[1] = j;
					args[2] = i;
				}
				boxes[i][j] = Box.newBox(args);
				if(boxes[i][j] != null)	{ area++; }
			}
		}

		opened = true;
	}

	/** Constructs a maze from a matrix of boxes.
	 *  @param boxes a matrix of boxes. */
	public Maze(Box[][] boxes)	{
		this();
		newMaze(boxes);
	}

	public void newMaze(Box[][] boxes)	{
		close();
		this.boxes = boxes;
		this.width = boxes.length;
		this.height = boxes[0].length;
		this.area = 0;
		for(int i = 0; i < width; i++)	{
			for(int j = 0; j < height; j++)	{
				if(boxes[j][i] != null)	{ 
					area++;
					if(boxes[j][i].hasFlag(BoxFlag.BOX_START))	{
						try	{
							setRoot(i, j);
						} catch (MazeException e)	{}
					}
				}
			}
		}
		opened = true;
	}

	/** Auxiliary method reading a box from an input stream.
	 *  It stops when it reads characters 255 or reaches EOF.
	 *  @param in input stream to be read.
	 *  @return The box read from in.
	 *  @throws IOException if an I/O error occurs.
	 *  @throws UnexpectedBoxTypeException if no box type is
	 *  available or box type does not match any.
	 *  @throws InvalidBoxArgumentsException if number of
	 *  arguments given is incorrect. */
	private Box readBox(InputStream in)
		throws IOException, UnexpectedBoxTypeException, InvalidBoxArgumentsException	{
		ArrayList<Integer> input = new ArrayList<Integer>();

		int c = in.read();
		if(c == -1)	{ return null; }

		//While not reaching a stop signal
		while(c != 255 && c!= -1)	{
			input.add(Integer.valueOf(c));
			c = in.read();
		}

	
		int[] args = new int[input.size()];
		for(int i = 0; i < input.size() - 1; i++)	{
			args[i] = input.get(i);
		}
		
		Box box = Box.newBox(args);
		return box;
	}

	/** Reads a new maze from a given input stream.
	 *  The stream, typically a file stream, shall fit with a
	 *  correct format. Boxes are read one after another, each
	 *  separated by integer value 255. Each box must be described
	 *  as follows : box_type x y z flags required_args.
	 *  It effecively opens the maze.
	 */
	public void read(InputStream in)	
		throws IOException, ReadingException	{

		close();

		ArrayList<Box> boxList = new ArrayList<Box>();

		//Recording max values of X and Y to convert more
		// quickly.
		int xMax = -1, yMax = -1;
		int iBox = 0;
		Box box = null;

		//A first reading is necessary to enter the while loop
		try	{
			box = readBox(in);
			iBox++;
		} catch (MazeException e)	{
			new ReadingException(iBox, e.getMessage());
		}

		while(box != null)	{
			if(box.hasFlag(BoxFlag.BOX_START) && !hasRoot)	{
				hasRoot = true;
				xRoot = box.getX();
				yRoot = box.getY();
			}
			if(box.getX() > xMax)	{ xMax = box.getX(); }
			if(box.getY() > yMax)	{ yMax = box.getY(); }
			boxList.add(box);

			try	{
				box = readBox(in);
				iBox++;
			} catch (MazeException e)	{
				throw new ReadingException(iBox, e.getMessage());
			}
		}

		//Now converting the list of boxes to a matrix.
		width = xMax + 1;
		height = yMax + 1;
		boxes = new Box[height][width];

		for(Box b : boxList)	{
			if(boxes[b.getY()][b.getX()] == null)	{
				area++;
			}
			boxes[b.getY()][b.getX()] = b;
		}

		opened = true;
	}

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

	public boolean isOpened()	{ return opened; }

	public void close()	{
		boxes = null;
		width = 0;
		height = 0;
		area = 0;
		opened = false;
		xRoot = -1;
		yRoot = -1;
		hasRoot = false;
		showFlag = EnumSet.noneOf(BoxFlag.class);
	}


/* **************************************************************** */
/* ********************** Dimension Getters *********************** */
/* **************************************************************** */

	public int size()	{ return area; }
	public int getArea()	{ return area; }
	public int getHeight()	{ return height; }
	public int getWidth()	{ return width; }


/* **************************************************************** */
/* ******************* Display methods and options **************** */
/* **************************************************************** */

	public void show(BoxFlag flag)	{ 
		showFlag.add(flag);
	}

	public void hide(BoxFlag flag)	{
		showFlag.remove(flag);
	}

	public void showAllFlags()	{
		showFlag = EnumSet.allOf(BoxFlag.class);
	}

	public void hideAllFlags()	{
		showFlag = EnumSet.noneOf(BoxFlag.class);
	}

	/** Auxiliary method for displaying a box.
	 *  First the method checks whether flags have to be
	 *  displayed. If none, it calls the display method of box. */
	private void displayBox(PrintStream out, int x, int y)	{
		Box box = boxes[y][x];
		if(box == null)	{ out.print(" "); return; }

		boolean start = 
			showFlag.contains(BoxFlag.BOX_START) &&
			box.hasFlag(BoxFlag.BOX_START);
		boolean end =
			showFlag.contains(BoxFlag.BOX_END) &&
			box.hasFlag(BoxFlag.BOX_END);
		boolean marked =
			showFlag.contains(BoxFlag.BOX_MARKED) &&
			box.hasFlag(BoxFlag.BOX_MARKED);
		
		if(start)	{ out.print("S"); }
		else if(end)	{ out.print("E"); }
		else if(marked)	{ out.print("X"); }
		else		{ box.display(out); }
	}

	/** Displays the maze in a given output stream.
	 *  The method adds coordinates up to 9, then it cycles.
	 *  If some flags are to display, rules of priority are as
	 *  follow, from highest to least priority :
	 *  BOX_START, BOX_END, BOX_MARKED. */
	public void display(PrintStream out)	{
		out.print("\n  ");
		for(int j = 0; j < width; j++)	{
			out.print(j % 10);
			if(j % 10 == 9)	{ out.print(" "); }
		}
		out.print("\n");
		for(int i = 0; i < height; i++)	{
			out.print(i % 10 + " ");
			for(int j = 0; j < width; j++)	{
				displayBox(out, j, i);
				if(j % 10 == 9)	{ out.print(" "); }
			}
			out.print("\n");
			if(i % 10 == 9)	{ out.print("\n"); }
		}
	}


/* **************************************************************** */
/* ****************** Dijkstra useful methods ********************* */
/* **************************************************************** */

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

	/** Returns a list of the successors of vertex.
	 *  It only returns boxes that are not distant and directly
	 *  adjacent (up to four directions). */
	public ArrayList<Vertex> getSuccessors(Vertex vertex)	{
		ArrayList<Vertex> ret = new ArrayList<Vertex>(4);
		Box b = (Box) vertex;
		Box l = null, r = null, u = null, d = null;
		int x = b.getX();
		int y = b.getY();

		if(x > 0)		{ l = boxes[y][x - 1]; }
		if(y > 0)		{ u = boxes[y - 1][x]; }
		if(x + 1 < width)	{ r = boxes[y][x + 1]; }
		if(y + 1 < height)	{ d = boxes[y + 1][x]; }

		if(distance(b, l) < distant)	{ ret.add(l); }
		if(distance(b, u) < distant)	{ ret.add(u); }
		if(distance(b, r) < distant)	{ ret.add(r); }
		if(distance(b, d) < distant)	{ ret.add(d); }
		
		return ret;
	}

	/** Returns the distance between two vertices.
	 *  The distance between two boxes is defined as the sum
	 *  of their practicabilities, with conventions :
	 *  null is distant, distant + anything = distant. */
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


/* **************************************************************** */
/* ******************** Flag Editing Methods ********************** */
/* **************************************************************** */

	public ArrayList<Vertex> getSelection(BoxFlag flag)	{
		ArrayList<Vertex> ret = new ArrayList<Vertex>();

		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				if(boxes[i][j] != null && boxes[i][j].hasFlag(flag))	{
					ret.add((Vertex)boxes[i][j]);
				}
			}
		}

		return ret;
	}

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

	public void clear()	{
		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				if(boxes[i][j] != null)	{
					boxes[i][j].clearFlags();
				}
			}
		}
		hasRoot = false;
	}

	/** Adds a flag to a box.
	 *  If the given flag is BOX_START, the method makes a call to
	 *  setRoot, so that it takes care of root management. */
	public void addFlag(int x, int y, BoxFlag flag)	
		throws MazeOutOfBoundsException, NullBoxException	{
		if(flag.equals(BoxFlag.BOX_START))	{
			setRoot(x, y);
		} else	{
			try	{
				boxes[y][x].addFlag(flag);
			} catch (IndexOutOfBoundsException e)	{
				throw new MazeOutOfBoundsException(x, y, width - 1, height - 1);
			} catch (NullPointerException e)	{
				throw new NullBoxException();
			}
		}
	}

	/** Removes a flag from a box.
	 *  If it removes the root, hasRoot is modified. */
	public void remFlag(int x, int y, BoxFlag flag)	
		throws MazeOutOfBoundsException, NullBoxException	{
		try	{
			boxes[y][x].remFlag(flag);
			if(flag.equals(BoxFlag.BOX_START) && x == xRoot && y == yRoot)	{
				xRoot = -1;
				yRoot = -1;
				hasRoot = false;
			}
		} catch (IndexOutOfBoundsException e)	{
			throw new MazeOutOfBoundsException(x, y, width - 1, height - 1);
		} catch (NullPointerException e)	{
			throw new NullBoxException();
		}
	}

	public Vertex getRoot()	{
		if(hasRoot)	{ return boxes[yRoot][xRoot]; }
		else		{ return null; }
	}

	public boolean setRoot(int x, int y)	
		throws MazeOutOfBoundsException, NullBoxException	{
		boolean ret = false;
		try	{
			boxes[y][x].addFlag(BoxFlag.BOX_START);
		} catch (IndexOutOfBoundsException e)	{
			throw new MazeOutOfBoundsException(x, y, width - 1, height - 1);
		} catch (NullPointerException e)	{
			throw new NullBoxException();
		}

		if(hasRoot && !(x == xRoot && y == yRoot))	{
			boxes[yRoot][xRoot].remFlag(BoxFlag.BOX_START);
			ret = true;
		}
		yRoot = y;
		xRoot = x;
		hasRoot = true;
		return ret;
	}

	public void remRoot()	{
		if(hasRoot && boxes[yRoot][xRoot] != null)	{
			boxes[yRoot][xRoot].remFlag(BoxFlag.BOX_START);
		}
		xRoot = -1;
		yRoot = -1;
		hasRoot = false;
	}


/* **************************************************************** */
/* ************************ Editing methods *********************** */
/* **************************************************************** */

	public void addRow(int pos, int[] args)	
		throws MazeOutOfBoundsException, UnexpectedBoxTypeException, InvalidBoxArgumentsException	{

		if(pos < 0 || pos > height)	{
			throw new MazeOutOfBoundsException(0, pos, width, height);
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
			if(args.length >= 3)	{
				args[1] = j;
				args[2] = pos;
			}
			addBox(args);
		}
	}

	public void remRow(int pos)	
		throws MazeOutOfBoundsException	{

		if(pos < 0 || pos >= height)	{
			throw new MazeOutOfBoundsException(0, pos, width - 1, height - 1);
		}

		Box[][] temp = new Box[height - 1][width];

		for(int i = 0; i < pos; i++)	{
			for(int j = 0; j < width; j++)	{
				temp[i][j] = boxes[i][j];
			}
		}
		for(int j = 0; j < width; j++)	{
			if(boxes[pos][j] != null)	{
				area--;
				if(boxes[pos][j].hasFlag(BoxFlag.BOX_START))	{
					hasRoot = false;
				}
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

	public void addCol(int pos, int[] args)	
		throws MazeOutOfBoundsException, UnexpectedBoxTypeException, InvalidBoxArgumentsException	{

		if(pos < 0 || pos > width)	{
			throw new MazeOutOfBoundsException(pos, 0, width, height);
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
			if(args.length >= 3)	{
				args[1] = pos;
				args[2] = i;
			}
			addBox(args);
		}
	}

	public void remCol(int pos)	
		throws MazeOutOfBoundsException	{

		if(pos < 0 || pos >= width)	{
			throw new MazeOutOfBoundsException(pos, 0, width - 1, height - 1);
		}

		Box[][] temp = new Box[height][width - 1];

		for(int j = 0; j < pos; j++)	{
			for(int i = 0; i < height; i++)	{
				temp[i][j] = boxes[i][j];
			}
		}
		for(int i = 0; i < height; i++)	{
			if(boxes[i][pos] != null)	{
				area--;
				if(boxes[i][pos].hasFlag(BoxFlag.BOX_START))	{
					hasRoot = false;
				}
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

	public void addBox(int[] args)
		throws MazeOutOfBoundsException, UnexpectedBoxTypeException, InvalidBoxArgumentsException	{

		int x = args[1];
		int y = args[2];

		if(x < 0 || x >= width || y < 0 || y >= height)	{
			throw new MazeOutOfBoundsException(x, y, width, height);
		}

		if(boxes[y][x] == null && args[0] != Box.ID)	{
			area++;
		}

		boxes[y][x] = Box.newBox(args);
	}

	public void remBox(int x, int y)	
		throws MazeOutOfBoundsException	{

		if(x < 0 || x >= width || y < 0 || y >= height)	{
			throw new MazeOutOfBoundsException(x, y, width - 1, height - 1);
		}

		if(boxes[y][x] != null)	{ area--; }
		if(boxes[y][x].hasFlag(BoxFlag.BOX_START))	{
			hasRoot = false;
		}
		boxes[y][x] = null;
	}

	public Vertex getBox(int x, int y)
		throws MazeOutOfBoundsException	{

		if(x < 0 || x >= width || y < 0 || y >= height || boxes[y][x] == null)	{
			throw new MazeOutOfBoundsException(x, y, width, height);
		}

		return boxes[y][x];
	}


/* **************************************************************** */
/* ************************ Drawing methods *********************** */
/* **************************************************************** */

	public int[][] drawIDs()	{
		int[][] IDs = new int[height][width];

		for(int y = 0; y < height; y++)	{
			for(int x = 0; x < width; x++)	{
				if(boxes[y][x] == null)	{ IDs[y][x] = -1; }
				else	{ IDs[y][x] = boxes[y][x].drawID(); }
			}
		}

		return IDs;
	}

	public int[][] drawFlags()	{
		int[][] flagIDs = new int[height][width];

		for(int y = 0; y < height; y++)	{
			for(int x = 0; x < width; x++)	{
				if(boxes[y][x] == null)	{ flagIDs[y][x] = -1; }
				else if(boxes[y][x].hasFlag(BoxFlag.BOX_START))	{ flagIDs[y][x] = BoxFlag.BOX_START.toInt(); }
				else if(boxes[y][x].hasFlag(BoxFlag.BOX_END))	{ flagIDs[y][x] = BoxFlag.BOX_END.toInt(); }
				else if(boxes[y][x].hasFlag(BoxFlag.BOX_MARKED))	{ flagIDs[y][x] = BoxFlag.BOX_MARKED.toInt(); }
				else	{ flagIDs[y][x] = 0; }
			}
		}

		return flagIDs;
	}
}
