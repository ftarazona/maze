package dijkstra;

/*
 * The class below implements the interface Pi.
 *
 * It is basically a function, we will implement it by an array of
 *  values.
 */

import java.util.Arrays;
import graph.Vertex;
import graph.Maze;

public class PiFunction
	implements Pi	{

	private int[] dists;


	/* Constructor
	 * It requires the size. */

	public PiFunction(int size)	{
		dists = new int[size];
		Arrays.fill(dists, Maze.distant);
	}


	public int get(Vertex vertex)	{
		return dists[vertex.getID()];
	}

	public void set(Vertex vertex, int dist)	{
		dists[vertex.getID()] = dist;
	}
}
