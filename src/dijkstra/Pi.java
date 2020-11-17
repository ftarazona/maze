package dijkstra;

import graph.Vertex;

public interface Pi	{
	
	/* Sets the value of pi for a given vertex. */

	public void set(Vertex vertex, int value);

	/* Gets the value of pi for a given vertex. */

	public int get(Vertex vertex);
}
