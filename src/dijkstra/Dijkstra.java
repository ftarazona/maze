package dijkstra;

import java.util.ArrayList;

import maze.Graph;
import maze.Vertex;


/** The Dijkstra algorithm is applied to a graph given a vertex called
 *  root. Its output is a tree giving minimal paths from the root to
 *  every vertex that can be reached. */
public class Dijkstra	{

	/** Processes the Dijkstra algorithm.
	 *  @param graph the graph containing the vertices and
	 *   the distance between each couple.
	 *  @param root the vertex of the graph which will be used
	 *   as the root of the output tree.
	 *  @param a an empty set of vertices used to keep track of
	 *   which vertex has been visited.
	 *  @param pi an empty Pi function.
	 *  @param prev an empty Previous function.
	 *  @return None. The Pi and Previous functions must be
	 *   created before the method is called and are modified 
	 *   when running the algorithm. */
	public static void dijkstra(Graph graph, Vertex root, ASet a, Pi pi, Previous prev)	{
		ArrayList<Vertex> vertices = graph.getVertices();
		Vertex piv = root;
		a.add(root);

		// In this implementation, the "default value" is 
		// distant, hence no need to initialize every vertex.
		pi.set(root, 0);

		for(int i = 0; i < graph.size() - 1; i++)	{
			int dPiv = pi.get(piv);

			// We parse the successors of piv to update
			// them in case piv is a better parent.

			for(Vertex v: graph.getSuccessors(piv))	{
				if(a.contains(v) == false)	{
					int newDist = dPiv + graph.distance(piv, v);
					if(graph.distance(piv, v) == Graph.distant)	{
						newDist = Graph.distant;
					}

					if(newDist < pi.get(v))	{
						prev.set(v, piv);
						pi.set(v, newDist);
					}
				}
			}

			// Finding the new pivot comes down to a
			// linear research of minimal value.

			int minValue = Graph.distant;
			
			for(Vertex v : vertices)	{
				if(a.contains(v) == false && pi.get(v) < minValue)	{
					piv = v;
					minValue = pi.get(v);
				}
			}

			a.add(piv);
		}
	}
}
