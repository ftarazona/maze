package dijkstra;

import maze.Vertex;


/** A Pi function keeps track of the minimal distance between 
 *  a vertex and the root considering paths in a given ASet when
 *  processing the Dijkstra algorithm.
 */

public interface Pi	{

	/** Sets the value of Pi for a given vertex. 
	 *  @param vertex vertex to be recorded.
	 *  @param value value to be recorded.
	 */
	public void set(Vertex vertex, int value);

	/** Gets the value of pi for a given vertex. 
	 *  @param vertex vertex to be searched.
	 *  @return minimal distance from the root by vertices in
	    the ASet used by algorithm.
	 */
	public int get(Vertex vertex);

	public void clear();
}
