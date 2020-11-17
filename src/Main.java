import java.util.Arrays;
import graph.*;
import dijkstra.*;

public class Main	{

	public static void main(String[] args)	{
		Maze maze = new Maze("maze.map");
		PiFunction pi = new PiFunction(maze.size());
		PreviousFunction prev = new PreviousFunction(maze.size());

		Dijkstra.dijkstra(maze, maze.select(BoxType.START).get(0), new Visited(maze.size()), pi, prev);

		maze.vmark(prev.getFullPath(maze.select(BoxType.END).get(0)));
		System.out.println(maze);
	}
}
