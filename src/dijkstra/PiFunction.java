package dijkstra;

import java.util.HashMap;

import maze.Vertex;
import maze.Graph;


/** This class implements a function from the set of the graph's
 *  vertices to the integers. For each vertex in the graph, it
 *  returns the minimal distance from the root within a ASet. */
public class PiFunction	
	implements Pi	{

	/** The implementation uses a HashMap to store the values.
	 *  Please refer to {@link java.util.HashMap} for further
	 *  information. */
	private HashMap<Vertex, Integer> table;

	/** Constructs a function with no values. 
	 *  The HashMap is initialized with default values.
	 *  @see HashMap */
	public PiFunction()	{
		table = new HashMap<Vertex, Integer>();
	}

	/** Clears the values of the fuction. */
	public void clear()	{
		table.clear();
	}

	/** Gets the value of the function for a given vertex.
	 *  @param vertex vertex to be searched.
	 *  @return The minimal distance from the root to vertex,
	 *  or Graph.distant if the vertex is not recorded.
	 */
	public int get(Vertex vertex)	{
		if(table.containsKey(vertex))	{
			return table.get(vertex).intValue();
		}
		else	{
			return Graph.distant;
		}
	}

	/** Records the minimal distance from the root to a vertex.
	 *  In case vertex is already recorded, the current
	 *  distance is overwritten no matter if the new value is 
	 *  greater.
	 *  @param vertex vertex to be recorded.
	 *  @param dist distance to be written.
	 */
	public void set(Vertex vertex, int dist)	{
		table.put(vertex, Integer.valueOf(dist));
	}
}
