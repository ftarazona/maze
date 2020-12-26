package graph;

public class EmptyBox extends Box	{
	
	public EmptyBox(int x, int y, int z)	{
		super(x, y, z);
	}

	public int getPracticability()	{
		return 1;
	}
}
