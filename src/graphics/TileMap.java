package graphics;

import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import maze.InterfaceableMaze;
import maze.MazeException;
import maze.BoxFlag;

public class TileMap extends JPanel	{
	
	private final static Color START_COLOR	= new Color(49, 200, 214, 130);
	private final static Color END_COLOR	= new Color(234, 140, 39, 130);
	private final static Color MARKED_COLOR	= new Color(247, 185, 242, 130);
	
	public static final long serialVersionUID = 202101160145L;

	private final String tileset_file;
	private final Image tileset;
	private final int gridSize;
	private InterfaceableMaze maze;
	int[][] IDs;
	int[][] flagIDs;
	int height;
	int width;

	public TileMap(InterfaceableMaze maze)	
		throws IOException	{
		super();
		this.maze = maze;
		tileset_file = "resources/tileset.png";

		tileset = ImageIO.read(new File(tileset_file));

		gridSize = 32;
		IDs = new int[0][0];
		flagIDs = new int[0][0];
		refreshMaze();
	}

	public void refreshMaze()	{
		this.IDs 	= maze.drawIDs();
		this.flagIDs	= maze.drawFlags();
		this.height 	= maze.getHeight();
		this.width 	= maze.getWidth();
		setPreferredSize(new Dimension(width * gridSize, height * gridSize));
		
	}

	@Override
	public void paintComponent(Graphics g)	{
		refreshMaze();
		super.paintComponent(g);
		g.clearRect(0, 0, getWidth(), getHeight());

		for(int i = 0; i < height; i++)	{
			for(int j = 0; j < width; j++)	{
				int y = i * gridSize;
				int x = j * gridSize;
				int n = IDs[i][j];
				int f = flagIDs[i][j];
				if(n >= 0)	{
					g.drawImage(tileset, x, y, x + gridSize, y + gridSize, n * gridSize, 0, (n+1) * gridSize, gridSize, this);
				}

				if(f == BoxFlag.BOX_START.toInt())	{
					g.setColor(START_COLOR);
					g.fillRect(x, y, gridSize, gridSize);
				}
				else if(f == BoxFlag.BOX_END.toInt())	{
					g.setColor(END_COLOR);
					g.fillRect(x, y, gridSize, gridSize);
				}
				else if(f == BoxFlag.BOX_MARKED.toInt())	{
					g.setColor(MARKED_COLOR);
					g.fillRect(x, y, gridSize, gridSize);
				}

			}
		}
	}

	public static void drawMaze(InterfaceableMaze maze)	
		throws IOException	{
		JFrame frame = new JFrame();
		TileMap map = new TileMap(maze);
		frame.setContentPane(map);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
