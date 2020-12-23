package dijkstra;

import java.util.ArrayList;
import graph.Vertex;
import graph.Graph;

public interface Previous	{

	/* Sets the previous vertex for a given one. */

	public void set(Vertex cur, Vertex prev);

	/* Gets the previous vertex. */

	public Vertex get(Vertex cur);

	/* Gets the full path from root. */

	public ArrayList<Vertex> getFullPath(Vertex cur);
}
