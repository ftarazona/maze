package maze;

import java.util.ArrayList;


/** A graph is an ordered set of vertices, linked by distances to one
 *  another.
 */

public interface Graph	{

	/** distant is the value considered to be greater than any other
	 *  distance. */
	public final static int distant = Integer.MAX_VALUE;

	/** Returns a list of all the vertices.
	 *  @return A list of all the vertices. */
	public ArrayList<Vertex> getVertices();

	/** Returns a list of the successors of vertex.
	 *  @param vertex origin vertex.
	 *  @return A list of the successors of vertex.*/
	public ArrayList<Vertex> getSuccessors(Vertex vertex);

	/** Returns the distance between two vertices.
	 *  @param src First vertex.
	 *  @param dst Second vertex.
	 *  @return The distance from src to dst. */
	public int distance(Vertex src, Vertex dst);

	/** Returns the number of vertices in the graph.
	 *  @return The number of vertices in the graph. */
	public int size();
}
