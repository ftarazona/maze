package dijkstra;

/*
 * The class below implements the interface Pi.
 *
 * It is basically a function, we will implement it by an array of
 *  values.
 */

import graph.Vertex;
import graph.Maze;
import java.util.HashMap;

public class PiFunction	
	implements Pi	{

	private HashMap<Vertex, Integer> table;

	/* Constructor
	 * It requires the size. */

	public PiFunction()	{
		table = new HashMap<Vertex, Integer>();
	}


	public int get(Vertex vertex)	{
		if(table.containsKey(vertex))	{
			return table.get(vertex).intValue();
		}
		else	{
			return Pi.distant;
		}
	}

	public void set(Vertex vertex, int dist)	{
		table.put(vertex, new Integer(dist));
	}
}
