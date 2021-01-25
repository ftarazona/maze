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
	 *  initialized with default values. 
	 *  @see HashMap. */
	public PreviousFunction()	{
		table = new HashMap<Vertex, Vertex>();
	}

	/** Clears the values of the function. */
	public void clear()	{
		table.clear();
	}

	/** Gets the parent of a given vertex. If the vertex was not
	 *  visited yet, the value returned is the vertex itself.
	 *  @param vertex vertex to be searched.
	 *  @return The parent of vertex if recorded, vertex
	 *  otherwise. */
	public Vertex get(Vertex vertex)	{
		if(table.containsKey(vertex))	{
			return table.get(vertex);
		}
		else	{
			return vertex;
		}
	}

	/** Gets the path from the root to a given vertex. If the 
	 *  vertex was not visited yet, it returns a list with v only.
	 *  @param vertex vertex to be reached from the root.
	 *  @return A list of vertices, beginning by the root, ending
	 *  by v's parent. */
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

	/** Sets the value of a given vertex. If the given vertex
	 *  already has a parent, it is overwritten.
	 *  @param current is the vertex which value has to be set.
	 *  @param previous is the new parent of cur. */
	public void set(Vertex current, Vertex previous)	{
		table.put(current, previous);
	}
}
