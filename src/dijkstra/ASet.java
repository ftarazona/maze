package dijkstra;

import maze.Vertex;

/** An ASet is an unordered set of vertices used in the Dijkstra 
 *  algorithm to keep track of the already visited vertices.
 *  
 *  Note that such a set may only increase in size.
 */

public interface ASet	{

	/** Adds vertex to the set.
	 *  @param vertex is the vertex to be added to the set.
	 */
	public void add(Vertex vertex);

	/** Checks whether vertex is in the set.
	 *  @param vertex is the vertex to be checked.
	 *  @return true if the ASet contains v, false otherwise.
	 */
	public boolean contains(Vertex vertex);
}
