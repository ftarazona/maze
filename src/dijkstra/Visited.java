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

import graph.Vertex;
import java.util.HashSet;

public class Visited
        implements ASet {

        private HashSet<Vertex> table;

        /* Constructor
         * It requires to indicate the size. */

        public Visited()        {
                table = new HashSet<Vertex>();
        }


        public void add(Vertex vertex)  {
                table.add(vertex);
        }

        public boolean contains(Vertex vertex)  {
                return table.contains(vertex);
        }
}
