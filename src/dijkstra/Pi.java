package dijkstra;

import graph.Vertex;

public interface Pi	{

	public static int distant = Integer.MAX_VALUE;

	/* Sets the value of pi for a given vertex. */

	public void set(Vertex vertex, int value);

	/* Gets the value of pi for a given vertex. */

	public int get(Vertex vertex);
}
