package dijkstra;
  
import java.util.HashSet;

import maze.Vertex;


/** The class Visited implements a unordered set of vertices which can
 *  only increase in size.
 *  It uses {@link java.util.HashSet} to be more efficient.
 */
public class Visited
        implements ASet {

	/** The HashSet containing the vertices in the set. 
	 *  Please refer to {@link java.util.HashSet} for further
	 *  information. */
        private HashSet<Vertex> table;

	/** Constructs an empty set of vertices with the default
	 *  capacity and load  of a HashSet.
	 *  @see HashSet
	 */
        public Visited()        {
                table = new HashSet<Vertex>();
        }

	/** Adds a vertex to the set. Once the vertex added, it shall
	 *  not be removed from the graph until the algorithm is
	 *  finished. An early removal would result in an undefined
	 *  behavior.
	 *  @param vertex vertex to be added.
	 */
        public void add(Vertex vertex)  {
                table.add(vertex);
        }

	/** Checks whether a vertex is in the set. In case of an early
	 *  removal of the vertex in the graph by the algorithm, the
	 *  method has an undefined behavior.
	 *  @param vertex vertex to be check.
	 *  @return true if the set contains vertex, false otherwise.
	 */
        public boolean contains(Vertex vertex)  {
                return table.contains(vertex);
        }
}
