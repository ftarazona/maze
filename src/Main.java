import graph.*;
import dijkstra.*;

public class Main	{

	public static void main(String[] args)	{
		Maze maze = new Maze("maze.map");

		Box start = maze.select(BoxType.START).get(0);
		Box end = maze.select(BoxType.END).get(0);

		PiFunction pi = new PiFunction();
		PreviousFunction prev = new PreviousFunction();

		Dijkstra.dijkstra(maze, start, new Visited(maze.size()), pi, prev);

		maze.vmark(prev.getFullPath(end));
		System.out.println(maze);
	}
}
