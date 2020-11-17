package dijkstra;

/*
 * The class below implements the interface ASet.
 *
 * As the set would eventually contain every vertex, the benefits of
 *  an implementation by the list of the vertices are useless in the
 *  end.
 * Therefore, it will use an array of boolean, indexed by the IDs of
 *  the vertices which are assumed to be successive.
 */

import java.util.Arrays;
import graph.Vertex;

public class Visited
	implements ASet	{

	private boolean[] visited;


	/* Constructor
	 * It requires to indicate the size. */

	public Visited(int size)	{
		visited = new boolean[size];
		Arrays.fill(visited, false);
	}


	public void add(Vertex vertex)	{
		visited[vertex.getID()] = true;
	}

	public boolean contains(Vertex vertex)	{
		return visited[vertex.getID()];
	}
}
