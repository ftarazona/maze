package dijkstra;

import java.util.ArrayList;
import java.util.HashMap;

import maze.Graph;
import maze.Vertex;


/** This class implements a function from the set of the visited
 *  vertices to itself. For each visited vertex, it returns its 
 *  parent in the tree built by the algorithm. */
public class PreviousFunction
	implements Previous	{

	/** The implementation uses a HashMap to store the values.
	 *  Please refer to {@link java.util.HashMap} for furhter
	 *  information. */
	private HashMap<Vertex, Vertex> table;

	/** Constructs a function with no values. The HashMap is
	 *  initialized with default values, see
	 *  {@link java.util.HashMap}. */
	public PreviousFunction()	{
		table = new HashMap<Vertex, Vertex>();
	}

	public void clear()	{
		table.clear();
	}

	/** Gets the parent of a given vertex. If the vertex was not
	 *  visited yet, the value returned is the vertex itself.
	 *  @param v is the vertex to be looked for.
	 *  @return the parent of v in case v was visited before, v
	 *  otherwise. */
	public Vertex get(Vertex v)	{
		if(table.containsKey(v))	{
			return table.get(v);
		}
		else	{
			return v;
		}
	}

	/** Gets the path from the root to a given vertex. If the 
	 *  vertex was not visited yet, it returns a list with v only.
	 *  @param v is the vertex to be reached from the root.
	 *  @return a list of vertices, beginning by the root, ending
	 *  by v's parent. */
	public ArrayList<Vertex> getFullPath(Vertex v)	{
		if(table.containsKey(v) == false)	{
			return new ArrayList<Vertex>();
		}
		else	{
			ArrayList<Vertex> path = getFullPath(table.get(v));
			path.add(v);
			return path;
		}
	}

	/** Sets the value of a given vertex. If the given vertex
	 *  already has a parent, it is overwritten.
	 *  @param cur is the vertex which value has to be set.
	 *  @param prev is the new parent of cur. */
	public void set(Vertex cur, Vertex prev)	{
		table.put(cur, prev);
	}
}
