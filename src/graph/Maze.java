package graph;

/*
 * The class below implements the Graph interface.
 *
 * It is implemented by a matrix containing the boxes.
 * There is no need to store the distances, we can calculate them with
 *  the types of the boxes.
 */

import java.util.Arrays;
import java.util.ArrayList;

public class Maze
	implements Graph	{

	/* distant indicates two vertices are not connected. */

	public static int distant = Integer.MAX_VALUE;

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

	public Maze(String filename)	{
		//TO BE IMPLEMENTED
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

	/* The successors of a box is its neighbours, IF they exist.
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
			ret.add((Vertex)boxes[y][x + 1]);
		}

		return ret;
	}

	/* The distance is given by the sum of the weights determined
	 *  by the type of each box. */

	private int weight(Box box)	{
		switch(box.getType())	{
			case EMPTY: return 1;
			case WALL: return distant;
			case START: return 1;
			case END: return 1;
			default: return distant;
		}
	}

	public int distance(Vertex src, Vertex dst)	{
		int wsrc = weight((Box)src);
		int wdst = weight((Box)dst);
		
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
}
