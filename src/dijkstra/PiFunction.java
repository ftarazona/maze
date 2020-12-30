package dijkstra;

import graph.Vertex;
import graph.Graph;
import java.util.HashMap;

/** This class implements a function from the set of the graph's
 *  vertices to the integers. For each vertex in the graph, it
 *  returns the minimal distance from the root within a ASet. */
public class PiFunction	
	implements Pi	{

	/** The implementation uses a HashMap to store the values.
	 *  Please refer to {@link java.util.HashMap} for further
	 *  information. */
	private HashMap<Vertex, Integer> table;

	/** Constructs a function with no values. The HashMap is
	 *  initialized with default values, see
	 *  {@link java.util.HashMap}. */
	public PiFunction()	{
		table = new HashMap<Vertex, Integer>();
	}

	/** Gets the value of the function for a given vertex. Note
	 *  that if the given vertex is not yet in the set, OR DEFINED
	 *  IN THE GRAPH, this method tells the vertex is distant.
	 *  @param v is the vertex to be looked for.
	 *  @return the minimal distance from the root to v.
	 */
	public int get(Vertex v)	{
		if(table.containsKey(v))	{
			return table.get(v).intValue();
		}
		else	{
			return Graph.distant;
		}
	}

	/** Sets the minimal distance from the root to a given vertex.
	 *  In case the vertex is already recorded, the current
	 *  distance is overwritten no matter if the new value is 
	 *  greater.
	 *  @param v is the vertex concerned.
	 *  @param dist is the new distance to be written.
	 */
	public void set(Vertex vertex, int dist)	{
		table.put(vertex, Integer.valueOf(dist));
	}
}
