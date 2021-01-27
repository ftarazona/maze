package graphics;

import java.io.IOException;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import ui.*;
import maze.InterfaceableMaze;

public class MazeApp extends JFrame implements ChangeListener	{

	private final WindowPanel	windowPanel;
	private final GraphicalInterface ui;

	public MazeApp(GraphicalInterface core)	
		throws IOException	{
		super("A-MAZE-ING!");

		this.ui = core;
		setContentPane(windowPanel	= new WindowPanel(this));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		pack();
		setResizable(false);
		setVisible(true);
	}

	public GraphicalInterface getUI()	{ return ui; }

	public void stateChanged(ChangeEvent evt)	{
		Exception e = ui.getException();
		if(e != null)	{
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		windowPanel.stateChanged(evt);
	}

	public static void drawMaze(GraphicalInterface core)	
		throws IOException	{
		MazeApp frame = new MazeApp(core);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(frame.getSize());
		frame.setVisible(true);
	}

}
