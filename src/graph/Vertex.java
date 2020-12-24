package graph;

/*
 * A graph is a set of vertices, linked together by arcs.
 *
 * The interface below declares which methods a vertex might propose.
 * As vertices are distinct in a graph, a vertex shall be given a
 *  unique identifier.
 */

public interface Vertex	{

	/* Returns the ID of the vertex. */

	public int getID();
}
