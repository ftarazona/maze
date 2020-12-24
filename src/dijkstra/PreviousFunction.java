package dijkstra;

/*
 * The class below implements the function described by the interface
 *  Previous.
 */

import java.util.Arrays;
import java.util.ArrayList;
import graph.Vertex;
import graph.Graph;
import graph.Box;
import java.util.HashMap;

public class PreviousFunction
	implements Previous	{

	private HashMap<Vertex, Vertex> table;

	public PreviousFunction()	{
		table = new HashMap<Vertex, Vertex>();
	}

	public Vertex get(Vertex vertex)	{
		if(table.containsKey(vertex))	{
			return table.get(vertex);
		}
		else	{
			return new Box();
		}
	}

	public ArrayList<Vertex> getFullPath(Vertex vertex)	{
		if(table.containsKey(vertex) == false)	{
			return new ArrayList<Vertex>();
		}
		else	{
			ArrayList<Vertex> path = getFullPath(table.get(vertex));
			path.add(vertex);
			return path;
		}
	}

	public void set(Vertex cur, Vertex prev)	{
		table.put(cur, prev);
	}
}
