package dijkstra;

/*
 * The class below implements the function described by the interface
 *  Previous.
 */

import java.util.Arrays;
import java.util.ArrayList;
import graph.Vertex;
import graph.Graph;
import graph.Box;

public class PreviousFunction
	implements Previous	{

	private Vertex[] prevs;


	public PreviousFunction(int size)	{
		prevs = new Vertex[size];
		Box nullbox = new Box();
		Arrays.fill(prevs, (Vertex)nullbox);
	}

	public Vertex get(Vertex vertex)	{
		return prevs[vertex.getID()];
	}

	public ArrayList<Vertex> getFullPath(Vertex vertex)	{
		if(vertex.getID() == Box.nullID)	{
			return new ArrayList<Vertex>();
		}
		else	{
			ArrayList<Vertex> path = getFullPath(get(vertex));
			path.add(vertex);
			return path;
		}
	}

	public void printPath(Vertex vertex)	{
		System.out.println("From root to " + vertex + " : " + getFullPath(vertex));
	}

	public void printPaths(Graph graph)	{
		for(Vertex v: graph.getVertices())	{
			printPath(v);
		}
	}

	public void set(Vertex cur, Vertex prev)	{
		prevs[cur.getID()] = prev;
	}
}
