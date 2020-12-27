import graph.*;
import dijkstra.*;
import fileops.*;
import java.io.*;

public class Main	{

	public static void prevmain(String[] args)	{
		Box[][] boxes = new Box[3][3];
		boxes[0][0] = new DummyBox(0, 0); 
		boxes[0][1] = new EmptyBox(1, 0, 0);
		boxes[0][1].addFlag(Box.START);
		boxes[0][2] = new EmptyBox(2, 0, 0);
		boxes[1][0] = new WallBox(0, 1, 0);
		boxes[1][1] = new EmptyBox(1, 1, 0);
		boxes[1][2] = new EmptyBox(2, 1, 0);
		boxes[1][2].addFlag(Box.END);
		boxes[2][0] = new StairsBox(0, 2, 0, 1);
		boxes[2][1] = new WaterBox(1, 2, 0, 1);
		boxes[2][2] = new BridgeBox(2, 2, 1);
		
		Maze maze = new Maze(boxes);
		
		maze.display();

		FileOutputStream file = null;
		BufferedOutputStream buffer = null;

	try	{
		file = new FileOutputStream("maps/maptest.mz");
		buffer = new BufferedOutputStream(file);
		maze.write(buffer);
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
	}

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
	}
}
