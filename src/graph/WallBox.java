package graph;

public class WallBox extends Box	{
	public WallBox(int x, int y, int z)	{
		super(x, y, z);
	}

	public int getPracticability()	{
		return Graph.distant;
	}
}
