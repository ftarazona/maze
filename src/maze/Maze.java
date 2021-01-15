package maze;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.EnumSet;


public class Maze
	implements Graph, InterfaceableMaze	{

	private Box[][] boxes;
	private int width;
	private int height;
	private int area;

	private boolean opened;

	EnumSet<BoxFlag> showFlag;

	/** Constructs an empty maze */
	public Maze()	{
		boxes = new Box[0][0];
		width = 0;
		height = 0;
		area = 0;
		opened = false;
		showFlag = EnumSet.noneOf(BoxFlag.class);
	}

	public Maze(int height, int width, int type)	
		throws UnexpectedBoxTypeException	{
		showFlag = EnumSet.noneOf(BoxFlag.class);
		newMaze(height, width, type);
	}

	public void newMaze(int height, int width, int boxType)	
		throws UnexpectedBoxTypeException	{
		boxes = new Box[height][width];
		this.height = height;
		this.width = width;
		this.area = 0;
		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				try	{
					boxes[i][j] = null;
					int[] args = new int[MazeContext.getNbArgs(boxType)];
					args[0] = j;
					args[1] = i;
					addBox(boxType, args);
				} catch (MazeException e) {}
			}
		}

		opened = true;
	}

	/** Constructs a maze from a matrix of boxes.
	 *  @param boxes a matrix of boxes. */
	public Maze(Box[][] boxes)	{
		this();
		this.boxes = boxes;
		this.width = boxes[0].length;
		this.height = boxes.length;
	}

	public boolean isOpened()	{ return opened; }
	public void close()	{
		boxes = new Box[0][0];
		width = 0;
		height = 0;
		area = 0;
		opened = false;
		showFlag = EnumSet.noneOf(BoxFlag.class);
	}

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

	public int getHeight()	{
		return height;
	}

	public int getWidth()	{
		return width;
	}

	/** Returns a list of vertices carrying a given flag.
	 *  @param flag is the flag to be looked for.
	 *  @return a list of vertices carrying the flag. */
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
				throw new MazeOutOfBoundsException(x, y, width - 1, height - 1);
			}
		}
	}

	public Vertex getRoot()	{
		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				if(boxes[i][j] != null && boxes[i][j].hasFlag(BoxFlag.BOX_START))	{ return boxes[i][j]; }
			}
		}

		return null;
	}

	public boolean setRoot(int x, int y)	
		throws MazeOutOfBoundsException	{
		try	{
			boxes[y][x].addFlag(BoxFlag.BOX_START);
		} catch (IndexOutOfBoundsException e)	{
			throw new MazeOutOfBoundsException(x, y, width - 1, height - 1);
		}
		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				if((i != y || j != x) && boxes[i][j] != null &&  boxes[i][j].hasFlag(BoxFlag.BOX_START))	{
					boxes[i][j].remFlag(BoxFlag.BOX_START);
					return true;
				}
			}
		}
		return false;
	}

	public void remRoot()	{
		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				if(boxes[i][j] != null && boxes[i][j].hasFlag(BoxFlag.BOX_START))	{
					boxes[i][j].remFlag(BoxFlag.BOX_START);
				}
			}
		}
	}

	public void remFlag(int x, int y, BoxFlag flag)	
		throws MazeOutOfBoundsException	{
		try	{
			boxes[y][x].remFlag(flag);
		} catch (IndexOutOfBoundsException e)	{
			throw new MazeOutOfBoundsException(x, y, width - 1, height - 1);
		}
	}

	public void clearSelection(ArrayList<Vertex> sel, BoxFlag flag)	{
		for(Vertex v: sel)	{
			boxes[((Box)v).getY()][((Box)v).getX()].remFlag(flag);
		}
	}

	/** Clears all the flags of every vertex in the graph. */
	public void clear()	{
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


	private Box readBox(InputStream in)
		throws IOException, MazeOutOfBoundsException, UnexpectedBoxTypeException, InvalidBoxArgumentsException	{
		ArrayList<Integer> input = new ArrayList<Integer>();

		int c, boxType;
		do	{
			c = in.read();
			input.add(Integer.valueOf(c));
		} while(c != 255 && c != -1);

		try	{
			boxType = input.get(0);
		} catch(IndexOutOfBoundsException e)	{
			throw new UnexpectedBoxTypeException();
		}
	
		int[] args = new int[input.size() - 2];
		for(int i = 1; i < input.size() - 1; i++)	{
			args[i - 1] = input.get(i);
		}
		
		Box box = MazeContext.newBox(boxType, args);
		return box;
	}

	/** Reads a graph from an input stream.
	 *  @param in is the input stream to be read.
	 *  @throws IOException if an I/O error occurs. */
	public void read(InputStream in)	
		throws IOException, ReadingException	{

		ArrayList<Box> boxList = new ArrayList<Box>();
		int xMax = -1, yMax = -1, iBox = 0;
		Box box = null;

		do	{
			try	{
				box = readBox(in);
				iBox++;
			} catch (MazeException e)	{
				throw new ReadingException(String.format("Maze : Could not parse given stream : box %d : %s", iBox, e.getMessage()));
			}

			if(box.getX() > xMax)	{ xMax = box.getX(); }
			if(box.getY() > yMax)	{ yMax = box.getY(); }
			boxList.add(box);
		} while(box != null);

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


	public void addRow(int pos, int type)	
		throws MazeOutOfBoundsException, UnexpectedBoxTypeException	{

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
			throw new MazeOutOfBoundsException(0, pos, width - 1, height - 1);
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
		throws MazeOutOfBoundsException, UnexpectedBoxTypeException	{

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
			throw new MazeOutOfBoundsException(pos, 0, width - 1, height - 1);
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
		throws MazeOutOfBoundsException, UnexpectedBoxTypeException, InvalidBoxArgumentsException	{

		if(args.length < MazeContext.MIN_ARGS)	{
			throw new InvalidBoxArgumentsException(2, MazeContext.MIN_ARGS);
		}
		int x = args[0];
		int y = args[1];

		if(x < 0 || x >= width || y < 0 || y >= height)	{
			throw new MazeOutOfBoundsException(x, y, width, height);
		}

		if(boxes[y][x] == null && type != MazeContext.NULL_ID)	{
			area++;
		}

		boxes[y][x] = MazeContext.newBox(type, args);
	}

	public void remBox(int x, int y)	
		throws MazeOutOfBoundsException	{

		if(x < 0 || x >= width || y < 0 || y >= height)	{
			throw new MazeOutOfBoundsException(x, y, width - 1, height - 1);
		}

		boxes[y][x] = null;
		area--;
	}

	public Vertex getBox(int x, int y)
		throws MazeOutOfBoundsException	{

		if(x < 0 || x >= width || y < 0 || y >= height || boxes[y][x] == null)	{
			throw new MazeOutOfBoundsException(x, y, width, height);
		}

		return boxes[y][x];
	}
}
