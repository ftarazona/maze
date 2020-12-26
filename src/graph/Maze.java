package graph;

import java.util.Arrays;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Maze
	implements Graph	{

	private String mapsPrefix = "maps/";

	/* To store the boxes, we use a two-dimensional array. */

	private Box[][] boxes;
	private int width;
	private int height;


	/* Default constructor
	 *  there is none : an empty maze is not a maze...
	 *  you must either give a matrix or a file to be read. */

	public Maze(Box[][] boxes)	{
		this.boxes = boxes;
		this.width = boxes[0].length;
		this.height = boxes.length;
	}

	/*
	 * Loader constructor reads a maze map from a file input.
	 */

	public Maze(String filename)	{
		try	{
			loadFromFile(filename);
		} catch(MazeReadingException e)	{
			e.printMessage();
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

	private void loadFromFile(String filename)
		throws MazeReadingException	{
	try	{
	
		FileReader file;
		BufferedReader input;

		file = new FileReader(mapsPrefix + filename);
		input = new BufferedReader(file);

		ArrayList<ArrayList<Box> > tempBoxes = new ArrayList<ArrayList<Box> >();

		/* Reading the first line provides information about
		 *  the map format. */

		String line = input.readLine();
		int y = 0;
		final int maxx = line.length();

		/* Reads the whole file. */

		while(line != null)	{
			if(line.length() != maxx)	{
				throw new MazeReadingException(filename, y + 1, "Bad format : read " + Integer.toString(line.length()) + " instead of " + Integer.toString(maxx));
			}

			tempBoxes.add(new ArrayList<Box>());

			for(int x = 0; x < line.length(); x++)	{
				tempBoxes.get(y).add(new WallBox(x, y, 0));
			}

			line = input.readLine();
			y++;
		}

		/* Converts ArrayList into Arrays. */

		width = maxx;
		height = y;
		boxes = new Box[height][width];

		for(int i = 0; i < y; i++)	{
			for(int j = 0; j < maxx; j++)	{
				boxes[i][j] = tempBoxes.get(i).get(j);
			}
		}

		input.close();

	} catch(FileNotFoundException e)	{
		System.out.println("ERROR: File not found: " + mapsPrefix + filename);
		e.printStackTrace();
	} catch(IOException e)	{
		System.out.println("ERROR: Could not read input from file " + mapsPrefix + filename);
		e.printStackTrace();
	} catch(MazeReadingException e)	{
		e.printMessage();
	} finally	{
		try	{
			input.close();
		} catch(Exception e)	{
			System.out.println("ERROR: Could not close file " + mapsPrefix + filename);
			e.printStackTrace();
		}
	}
	}

	public String toString()	{
		String ret = new String();

		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				ret += '0';
			}
			ret += '\n';
		}

		return ret;
	}

	public void writeToFile(String filename)	{
	try	{
		FileWriter file = new FileWriter(filename);

		for(char c: toString().toCharArray())	{
			file.write(c);
		}

		file.flush();
	} catch(FileNotFoundException e)	{
		System.out.println("ERROR: File not found: " + mapsPrefix + filename);
		e.printStackTrace();
	} catch(IOException e)	{
		System.out.println("ERROR: Could not write input from file " + mapsPrefix + filename);
		e.printStackTrace();
	} finally	{
		try	{
			file.close();
		} catch(Exception e)	{
			System.out.println("ERROR: Could not close file " + mapsPrefix + filename);
			e.printStackTrace();
		}
	}
	}
}
