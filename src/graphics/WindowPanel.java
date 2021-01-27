package graphics;

import java.io.IOException;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import maze.InterfaceableMaze;

public class WindowPanel extends JPanel	{
	
	private final TileMap	tileMap;
	private final MainPanel	mainPanel;

	public WindowPanel(MazeApp app)	
		throws IOException	{
		setLayout(new BorderLayout());

		add(tileMap	= new TileMap(app, app.getUI().getMaze()), BorderLayout.CENTER);
		add(mainPanel	= new MainPanel(app), BorderLayout.EAST);
	}

	public void stateChanged(ChangeEvent evt)	{
		tileMap.stateChanged(evt);
	}
}
