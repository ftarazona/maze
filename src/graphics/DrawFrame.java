package graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import ui.*;
import maze.*;

/** Frame running in parallel of the prompt interface */
public class DrawFrame extends JFrame	{

	private final PromptInterface ui;
	private TileMap map;

	public DrawFrame(PromptInterface ui)	{
		super();
		this.ui = ui;

		try	{
			map = new TileMap(ui.getMazeSafe());
		} catch (Exception e)	{
			System.out.println("Error while trying to open the maze in a window.");
			e.printStackTrace();
			System.out.println("Exiting...");
			System.exit(-1);
		}

		setAutoRequestFocus(false);
		setTitle("My amazing Maze!");
		setLayout(new GridLayout(1, 1));
		setMinimumSize(new Dimension(300, 300));
		add(new JScrollPane(map));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		addWindowListener(new WindowAdapter()	{
			@Override
			public void windowClosing(WindowEvent e)	{
				ui.closeWindow();
			}
		});
	}

	public void refresh()	{
		map.refreshMaze();
		map.repaint();
		pack();
		repaint();
		setVisible(true);
	}
}
