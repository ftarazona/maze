package dijkstra;

/*
 * The interface below describes a set of vertices that can only add 
 *  elements and never removing one.
 */

import graph.Vertex;

public interface ASet	{

	/* Adds a vertex to the set. */

	public void add(Vertex vertex);

	/* Indicates whether the set contains the given vertex. */

	public boolean contains(Vertex vertex);
}
