import java.util.Arrays;
import graph.*;

public class Main	{

	public static void main(String[] args)	{
		Maze maze = new Maze("maze.map");
		maze.writeToFile("maze2.map");
	}
}
