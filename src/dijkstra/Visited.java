package dijkstra;
  
import maze.Vertex;
import java.util.HashSet;

/** The class Visited implements a unordered set of vertices which can
 *  only increase in size.
 *  It uses {@link java.util.HashSet} to be more efficient.
 */
public class Visited
        implements ASet {

	/** The HashSet containing the vertices in the set. */
        private HashSet<Vertex> table;

	/** Constructs an empty set of vertices with the default
	 *  capacity and load  of a HashSet. Please refer to
	 *  {@link java.util.HashSet} for further information.
	 */
        public Visited()        {
                table = new HashSet<Vertex>();
        }

	/** Adds a vertex to the set. Once the vertex added, it shall
	 *  not be removed from the graph until the algorithm is
	 *  finished. An early removal would result in an undefined
	 *  behavior.
	 *  @param v is the vertex to be added.
	 */
        public void add(Vertex v)  {
                table.add(v);
        }

	/** Checks whether a vertex is in the set. In case of an early
	 *  removal of the vertex in the graph by the algorithm, the
	 *  has an undefined behavior.
	 *  @param v is the vertex to be check.
	 *  @return true if the set contains v, false otherwise.
	 */
        public boolean contains(Vertex v)  {
                return table.contains(v);
        }
}
