package graphics;

import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import maze.InterfaceableMaze;
import maze.MazeException;
import maze.BoxFlag;

public class TileMap extends JPanel	{
	
	private final static Color START_COLOR	= new Color((float)49, (float)200, (float)214, (float)0.5);
	private final static Color END_COLOR	= new Color((float)234, (float)140, (float)39, (float)0.5);
	private final static Color MARKED_COLOR	= new Color((float)247, (float)185, (float)242, (float)0.5);
	
	public static final long serialVersionUID = 202101160145L;

	private final MazeApp app;

	private final String tileset_file;
	private final Image tileset;
	private final int gridSize;
	private InterfaceableMaze maze;
	int[][] IDs;
	int[][] flagIDs;
	int height;
	int width;

	public TileMap(MazeApp app, InterfaceableMaze maze)	
		throws IOException	{
		super();
		this.app = app;
		this.maze = maze;
		tileset_file = "resources/tileset.png";

		tileset = ImageIO.read(new File(tileset_file));

		gridSize = 32;
		IDs = new int[0][0];
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
					g.fillRect(x, y, x + gridSize, y + gridSize);
				}
				else if(f == BoxFlag.BOX_END.toInt())	{
					g.setColor(END_COLOR);
					g.fillRect(x, y, x + gridSize, y + gridSize);
				}
				else if(f == BoxFlag.BOX_MARKED.toInt())	{
					g.setColor(MARKED_COLOR);
					g.fillRect(x, y, x + gridSize, y + gridSize);
				}

			}
		}
	}
}
