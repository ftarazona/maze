package graph;

/*
 * The class below implements the Vertex interface.
 *
 * Although the course states it might be an abstract class extended
 *  by different kind of boxes, I think there are not enough 
 *  significant differences that may justify the need for an abstract
 *  class.
 * Therefore, the class adds an attribute describing the category of
 *  the box to allow the graph to estimate the distances.
 */

public class Box
	implements Vertex	{

	/* defaultID indicates there is no Box.
	 * It can prove useful when making recursive functions for
	 *  instance.
	 */

	static int defaultID = -1;

	private BoxType type;

	/* A box is unique by its coordinates. Therefore we need three
	 *  numbers to give an ID :
	 * * the two coordinates.
	 * * the maximum of the first one.
	 * Then the ID is x * maxx + y and a euclidian division allows
	 *  to find back x and y. Moreover, if maxx is minimal, the
	 *  IDs given are successive, which allows making arrays in
	 *  which index and IDs can be identified, with an optimal
	 *  cost in memory. */

	private class Coordinates	{
		public int X;
		public int Y;
		public Coordinates(int X, int Y)	{
			this.X = X;
			this.Y = Y;
		}
	};

	private int x;
	private int y;
	private int maxx;


	/* Default constructor
	 *  the ID by default is -1, which is maxx = 0, x = 0, y = -1.
	 *  the BoxType is EMPTY. */

	public Box()	{
		this.x = 0;
		this.y = -1;
		this.maxx = 0;
		this.type = BoxType.EMPTY;
	}

	public Box(int x, int y, int maxx, BoxType type)	{
		this.x = x;
		this.y = y;
		this.maxx = maxx;
		this.type = type;
	}


	public int getID()	{
		return x * maxx + y;
	}

	public int getX()	{
		return x;
	}

	public int getY()	{
		return y;
	}

	public Coordinates getXY()	{
		Coordinates c = new Coordinates(x, y);
		return c;
	}

	public void setID(int id)	{
		x = id / maxx;
		y = id % maxx;
	}

	public void setX(int x)	{
		this.x = x;
	}

	public void setY(int y)	{
		this.y = y;
	}

	public void setXY(Coordinates c)	{
		x = c.X;
		y = c.Y;
	}

	public void setMaxx(int maxx)	{
		this.maxx = maxx;
	}

	
	public BoxType getType()	{
		return type;
	}

	public void setType(BoxType type)	{
		this.type = type;
	}
}
