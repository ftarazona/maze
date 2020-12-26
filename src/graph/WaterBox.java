package graph;

public class WaterBox extends Box	{

	private final boolean deep;

	public WaterBox(int x, int y, int z, int depth)	{
		super(x, y, z);
		deep = depth != 0;
	}

	public int getPracticability()	{
		return Graph.distant;
	}
}
