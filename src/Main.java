import graph.*;
import dijkstra.*;
import fileops.*;
import ui.*;
import java.io.*;

public class Main	{
	public static void main(String[] args)	{
		MazeManager ui = new MazeManager();
		ui.run();
	}

	public static void qmain(String[] args)	{
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
		} catch (MazeOutOfBoundsException e)	{
			System.out.println("Caught an error.");
		}

		maze.display();
	}

	public static void pmain(String[] args)	{
		Box[][] boxes = new Box[10][10];
		boxes[0][0] = new WallBox(0, 0, 0);
		boxes[0][1] = new WallBox(1, 0, 0);
		boxes[0][2] = new WallBox(2, 0, 0);
		boxes[0][3] = new WallBox(3, 0, 0);
		boxes[0][4] = new WallBox(4, 0, 0);
		boxes[0][5] = new WallBox(5, 0, 0);
		boxes[0][6] = new WallBox(6, 0, 0);
		boxes[0][7] = new WallBox(7, 0, 0);
		boxes[0][8] = new WallBox(8, 0, 0);
		boxes[0][9] = new WallBox(9, 0, 0);
		boxes[1][0] = new WallBox(0, 1, 1);
		boxes[1][1] = new EmptyBox(1, 1, 1);
		boxes[1][2] = new EmptyBox(2, 1, 1);
		boxes[1][3] = new WallBox(3, 1, 1);
		boxes[1][4] = new WallBox(4, 1, 1);
		boxes[1][5] = new WallBox(5, 1, 1);
		boxes[1][6] = new EmptyBox(6, 1, 1);
		boxes[1][7] = new EmptyBox(7, 1, 1);
		boxes[1][8] = new EmptyBox(8, 1, 1);
		boxes[1][9] = new WallBox(9, 1, 1);
		boxes[2][0] = new WallBox(0, 2, 2);
		boxes[2][1] = new WallBox(1, 2, 2);
		boxes[2][2] = new EmptyBox(2, 2, 2);
		boxes[2][3] = new EmptyBox(3, 2, 2);
		boxes[2][4] = new EmptyBox(4, 2, 2);
		boxes[2][5] = new WallBox(5, 2, 2);
		boxes[2][6] = new EmptyBox(6, 2, 2);
		boxes[2][7] = new WallBox(7, 2, 2);
		boxes[2][8] = new EmptyBox(8, 2, 2);
		boxes[2][9] = new WallBox(9, 2, 2);
		boxes[3][0] = new WallBox(0, 3, 3);
		boxes[3][1] = new WallBox(1, 3, 3);
		boxes[3][2] = new EmptyBox(2, 3, 3);
		boxes[3][3] = new WallBox(3, 3, 3);
		boxes[3][4] = new EmptyBox(4, 3, 3);
			boxes[3][4].addFlag(BoxFlag.BOX_START);
		boxes[3][5] = new WallBox(5, 3, 3);
		boxes[3][6] = new EmptyBox(6, 3, 3);
		boxes[3][7] = new WallBox(7, 3, 3);
		boxes[3][8] = new EmptyBox(8, 3, 3);
		boxes[3][9] = new WallBox(9, 3, 3);
		boxes[4][0] = new WallBox(0, 4, 4);
		boxes[4][1] = new WallBox(1, 4, 4);
		boxes[4][2] = new EmptyBox(2, 4, 4);
		boxes[4][3] = new WallBox(3, 4, 4);
		boxes[4][4] = new WallBox(4, 4, 4);
		boxes[4][5] = new WallBox(5, 4, 4);
		boxes[4][6] = new EmptyBox(6, 4, 4);
		boxes[4][7] = new WallBox(7, 4, 4);
		boxes[4][8] = new EmptyBox(8, 4, 4);
		boxes[4][9] = new EmptyBox(9, 4, 4);
			boxes[4][9].addFlag(BoxFlag.BOX_END);
		boxes[5][0] = new WallBox(0, 5, 5);
		boxes[5][1] = new WallBox(1, 5, 5);
		boxes[5][2] = new EmptyBox(2, 5, 5);
		boxes[5][3] = new WallBox(3, 5, 5);
		boxes[5][4] = new EmptyBox(4, 5, 5);
		boxes[5][5] = new EmptyBox(5, 5, 5);
		boxes[5][6] = new EmptyBox(6, 5, 5);
		boxes[5][7] = new WallBox(7, 5, 5);
		boxes[5][8] = new EmptyBox(8, 5, 5);
		boxes[5][9] = new WallBox(9, 5, 5);
		boxes[6][0] = new WallBox(0, 6, 6);
		boxes[6][1] = new EmptyBox(1, 6, 6);
		boxes[6][2] = new EmptyBox(2, 6, 6);
		boxes[6][3] = new EmptyBox(3, 6, 6);
		boxes[6][4] = new EmptyBox(4, 6, 6);
		boxes[6][5] = new WallBox(5, 6, 6);
		boxes[6][6] = new WallBox(6, 6, 6);
		boxes[6][7] = new WallBox(7, 6, 6);
		boxes[6][8] = new WallBox(8, 6, 6);
		boxes[6][9] = new WallBox(9, 6, 6);
		boxes[7][0] = new WallBox(0, 7, 7);
		boxes[7][1] = new EmptyBox(1, 7, 7);
		boxes[7][2] = new WallBox(2, 7, 7);
		boxes[7][3] = new EmptyBox(3, 7, 7);
		boxes[7][4] = new EmptyBox(4, 7, 7);
		boxes[7][5] = new EmptyBox(5, 7, 7);
		boxes[7][6] = new EmptyBox(6, 7, 7);
		boxes[7][7] = new WallBox(7, 7, 7);
		boxes[7][8] = new WallBox(8, 7, 7);
		boxes[7][9] = new WallBox(9, 7, 7);
		boxes[8][0] = new WallBox(0, 8, 8);
		boxes[8][1] = new EmptyBox(1, 8, 8);
		boxes[8][2] = new EmptyBox(2, 8, 8);
		boxes[8][3] = new WallBox(3, 8, 8);
		boxes[8][4] = new WallBox(4, 8, 8);
		boxes[8][5] = new WallBox(5, 8, 8);
		boxes[8][6] = new WallBox(6, 8, 8);
		boxes[8][7] = new EmptyBox(7, 8, 8);
		boxes[8][8] = new EmptyBox(8, 8, 8);
		boxes[8][9] = new WallBox(9, 8, 8);
		boxes[9][0] = new WallBox(0, 9, 9);
		boxes[9][1] = new WallBox(1, 9, 9);
		boxes[9][2] = new WallBox(2, 9, 9);
		boxes[9][3] = new WallBox(3, 9, 9);
		boxes[9][4] = new WallBox(4, 9, 9);
		boxes[9][5] = new WallBox(5, 9, 9);
		boxes[9][6] = new WallBox(6, 9, 9);
		boxes[9][7] = new WallBox(7, 9, 9);
		boxes[9][8] = new WallBox(8, 9, 9);
		boxes[9][9] = new WallBox(9, 9, 9);

		Maze maze = new Maze(boxes);

		try	{
			FileOutputStream file = new FileOutputStream("maps/maptest.mz");
			BufferedOutputStream br = new BufferedOutputStream(file);
			maze.display();
			maze.write(br);
			br.close();
			file.close();
		} catch (Exception e)	{
			System.out.println("Caught an error.");
		}

	}
}
