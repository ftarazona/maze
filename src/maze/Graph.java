package maze;

import java.util.ArrayList;


/** A graph is an ordered set of vertices, linked by distances to one
 *  another.
 */

public interface Graph	{

	/** distant is the value considered to be greater than any other
	 *  distance. */
	public final static int distant = Integer.MAX_VALUE;

	/** @return a list of all the vertices. */
	public ArrayList<Vertex> getVertices();

	/** @return a list of the successors of the vertex  @param v. */
	public ArrayList<Vertex> getSuccessors(Vertex v);

	/** @return the distance from @param src to @param dst. */
	public int distance(Vertex src, Vertex dst);

	/** @return the number of vertices in the graph. */
	public int size();
}
