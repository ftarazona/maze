package graph;

public class StairsBox extends Box	{

	public final static int STAIRS_DOWNTOP		= 0;
	public final static int STAIRS_LEFTRIGHT	= 1;

	private final int dir;

	public StairsBox(int x, int y, int z, int dir)	{
		super(x, y, z);
		this.dir = dir;
	}

	public int getPracticability()	{
		return 2;
	}
}
