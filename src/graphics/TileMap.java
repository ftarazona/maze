package graphics;

import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import maze.InterfaceableMaze;
import maze.MazeException;


public class TileMap extends JPanel	{
	
	public static final long serialVersionUID = 202101160145L;

	private final String tileset_file;
	private final Image tileset;
	private final int gridSize;
	private InterfaceableMaze maze;
	int[][] IDs;
	int height;
	int width;

	public TileMap(InterfaceableMaze maze, String filename)	
		throws IOException	{
		super();
		this.maze = maze;
		tileset_file = filename;

		tileset = ImageIO.read(new File(filename));

		gridSize = 32;
		IDs = new int[0][0];
	}

	public void refreshMaze()	{
		this.IDs 	= maze.drawIDs();
		this.height 	= maze.getHeight();
		this.width 	= maze.getWidth();
		setPreferredSize(new Dimension(width * gridSize, height * gridSize));
		
	}

	@Override
	public void paintComponent(Graphics g)	{
		super.paintComponent(g);
		g.clearRect(0, 0, getWidth(), getHeight());

		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				int y = i * gridSize;
				int x = j * gridSize;
				int n = IDs[i][j];
				if(n >= 0)	{
					g.drawImage(tileset, x, y, x + gridSize, y + gridSize, n * gridSize, 0, (n+1) * gridSize, gridSize, this);
				}
			}
		}
	}

	public static void drawMaze(InterfaceableMaze maze)	
		throws IOException	{
		JFrame frame = new JFrame("My Amazing Maze !");
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		TileMap map = new TileMap(maze, "resources/tileset.png");
		map.refreshMaze();
		panel.add(map);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(frame.getSize());
		frame.setVisible(true);
	}
}
