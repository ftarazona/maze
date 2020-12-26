import graph.*;
import dijkstra.*;

public class Main	{

	public static void main(String[] args)	{
		Maze maze = new Maze("maze.map");

		Box start = (Box)maze.getSelection(Box.START).get(0);
		Box end = (Box)maze.getSelection(Box.END).get(0);

		PiFunction pi = new PiFunction();
		PreviousFunction prev = new PreviousFunction();

		Dijkstra.dijkstra(maze, start, new Visited(), pi, prev);

		maze.setSelection(prev.getFullPath(end), Box.ON_PATH);
		System.out.println(maze);
	}
}
