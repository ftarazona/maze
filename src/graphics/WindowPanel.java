package graphics;

import java.io.IOException;

import java.awt.*;
import javax.swing.*;

import maze.InterfaceableMaze;

public class WindowPanel extends JPanel	{
	
	private final TileMap	tileMap;
	private final MainPanel	mainPanel;

	public WindowPanel(MazeApp app, InterfaceableMaze maze)	
		throws IOException	{
		setLayout(new BorderLayout());

		add(tileMap	= new TileMap(app, maze), BorderLayout.CENTER);
		add(mainPanel	= new MainPanel(app), BorderLayout.EAST);
	}
}
