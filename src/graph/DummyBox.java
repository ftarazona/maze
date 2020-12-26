package graph;

public class DummyBox extends Box	{

	public DummyBox(int x, int y)	{
		super(x, y, 0);
	}

	public int getPracticability()	{
		return Graph.distant;
	}
}
