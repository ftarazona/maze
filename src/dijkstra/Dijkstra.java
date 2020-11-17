package dijkstra;

/*
 * The Dijkstra Algorithm allows to find least cost paths in a graph
 *  from a root to all of the vertices.
 * It assumes there is no loopin the graph nor negative distance arc.
 */

import java.util.ArrayList;
import graph.Vertex;
import graph.Graph;

public class Dijkstra	{

	public static void dijkstra(Graph graph, Vertex root, ASet a, Pi pi, Previous prev)	{
		ArrayList<Vertex> vertices = graph.getVertices();
		Vertex piv = root;
		a.add(root);

		/* Initialization of pi function. */

		for(Vertex v: vertices)	{
			pi.set(v, Pi.distant);
		}
		pi.set(root, 0);

		/* Main loop. */

		for(int i = 0; i < graph.size() - 1; i++)	{
			int dPiv = pi.get(piv);

			/* Updates the successors of piv. */

			for(Vertex v: vertices)	{
				if(a.contains(v) == false)	{
					int newDist = dPiv + graph.distance(piv, v);
					if(newDist < pi.get(v))	{
						prev.set(v, piv);
						pi.set(v, newDist);
					}
				}
			}

			/* Find the new pivot (search for the minimum
			 * of pi. */

			int minValue = Pi.distant;
			
			for(Vertex v : vertices)	{
				if(a.contains(v) == false && pi.get(v) < minValue)	{
					piv = v;
					minValue = pi.get(v);
				}
			}

			/* Updates the set of visited. */
			
			a.add(piv);
		}
	}
}
