package maze;

/** A vertex is the atomic unit used to describe a graph.
 *  As it is atomic, it should be made unique by an ID.
 */

public interface Vertex	{
	
	 /** Returns the ID of the vertex.
	  *  @return the ID of the vertex. */
	public int getID();
}
