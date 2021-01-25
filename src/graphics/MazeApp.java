package graphics;

import java.io.IOException;

import java.awt.*;
import javax.swing.*;

import maze.InterfaceableMaze;

public class MazeApp extends JFrame	{

	private final WindowPanel	windowPanel;

	public MazeApp(InterfaceableMaze maze)	
		throws IOException	{
		super("A-MAZE-ING!");

		setContentPane(windowPanel	= new WindowPanel(this, maze));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		pack();
		setVisible(true);
	}

	public static void drawMaze(InterfaceableMaze maze)	
		throws IOException	{
		MazeApp frame = new MazeApp(maze);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(frame.getSize());
		frame.setVisible(true);
	}

}
