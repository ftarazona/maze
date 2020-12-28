import graph.*;
import dijkstra.*;
import fileops.*;
import java.io.*;

public class Main	{

	public static void main(String[] args)	{
		FileInputStream file = null;
		BufferedInputStream buffer = null;
		Maze maze = new Maze();
	
		try	{
			file = new FileInputStream("maps/maptest.mz");
			buffer = new BufferedInputStream(file);
			maze.read(buffer);
		} catch (Exception e)	{
			System.out.println(e.getMessage());
		} finally	{
			try	{
				buffer.close();
				file.close();
			} catch (Exception e)	{
				System.out.println("Could not close file.");
			}
		}
		
		maze.display();

		try	{
			maze.remBox(3, 3);
			maze.addBox(new WaterBox(2, 2, 0, 0));
			maze.addBox(new EmptyBox(3, 3, 0));
		} catch (MazeOutOfBounds e)	{
			System.out.println("Caught an error.");
		}

		maze.display();
	}
}
