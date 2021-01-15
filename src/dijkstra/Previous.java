package dijkstra;

import java.util.ArrayList;
import graph.Vertex;
import graph.Graph;

/** A Previous function keeps track, for each vertex, its parent in
 *  the tree built by the Dijkstra algorithm.
 */

public interface Previous	{

	/** Sets the parent for a given vertex. 
	 *  @param cur is the current vertex.
	 *  @param prev is the parent found for @param cur by the
	    algorithm.
	 */
	public void set(Vertex cur, Vertex prev);

	/** Gets the parent of a given vertex. 
	 *  @param cur is the vertex to be looked for.
	 *  @return the parent of @param cur in the current tree.
	 */
	public Vertex get(Vertex cur);

	/** Gets the full path from root. 
	 *  @param cur is the vertex to which the path must go.
	 *  @return an ordered list of adjacent vertices, starting 
	    by the root and ending by cur's parent which length is
	    minimal.
	 */
	public ArrayList<Vertex> getFullPath(Vertex cur);

	public void clear();
}
