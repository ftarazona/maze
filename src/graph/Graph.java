/*
 * The interface below declares which methods a graph might propose.
 * A user shall be allowed to get a list of all the vertices of the
 *  graph.
 * It is also important to have access to the successors of a given
 *  vertex.
 * When two vertices are given, it is necessary to be able to estimate
 *  the distance of the arc.
 */

/*
 * Using an object of type ArrayList is a first choice of 
 *  implementation.
 */

import java.util.ArrayList;

public interface Graph	{

	/* Returns a list of all the vertices. */

	public ArrayList<Vertex> getVertices();

	/* Returns a list of the successors of the given vertex v. */
	
	public ArrayList<Vertex> getSuccessors(Vertex v);

	/* Returns the distance from src to dst. */

	public int distance(Vertex src, Vertex dst);

	/* Returns the number of vertices in the graph. */

	public int size();
}
