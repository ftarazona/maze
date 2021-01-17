package dijkstra;

import java.util.ArrayList;

import maze.Graph;
import maze.Vertex;


/** A Previous function keeps track, for each vertex, its parent in
 *  the tree built by the Dijkstra algorithm.
 */

public interface Previous	{

	/** Sets the parent for a given vertex. 
	 *  @param current current vertex to be recorded.
	 *  @param previous parent found by the algorithm.
	 */
	public void set(Vertex current, Vertex previous);

	/** Gets the parent of a given vertex. 
	 *  @param current vertex to be searched.
	 *  @return The parent in the current tree.
	 */
	public Vertex get(Vertex current);

	/** Gets the full path from root. 
	 *  @param current vertex to which the path must go.
	 *  @return an ordered list of adjacent vertices, starting 
	    by the root and ending by cur's parent which length is
	    minimal.
	 */
	public ArrayList<Vertex> getFullPath(Vertex current);

	/** Clears the values of the functions. */
	public void clear();
}
