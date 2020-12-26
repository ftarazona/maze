package dijkstra;

import graph.Vertex;

/** A Pi function keeps track of the minimal distance between 
 *  a vertex and the root considering paths in a given ASet when
 *  processing the Dijkstra algorithm.
 */

public interface Pi	{

	/** Sets the value of Pi for a given vertex. 
	 *  @param v is the vertex for which the @param value is set.
	 */
	public void set(Vertex v, int value);

	/** Gets the value of pi for a given vertex. 
	 *  @param v is the vertex to be looked for.
	 *  @return the minimal distance from the root by vertices in
	    the ASet used by algorithm.
	 */
	public int get(Vertex vertex);
}
