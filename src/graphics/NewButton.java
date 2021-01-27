package graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import ui.*;

public class NewButton extends JButton implements ActionListener	{

	private final MazeApp app;

	public NewButton(MazeApp app)	{
		super("New maze");
		this.app = app;
		setPreferredSize(new Dimension(120, 20));

		addActionListener(this);
	}

	public final void actionPerformed(ActionEvent evt)	{
		int width = Integer.parseInt((String)JOptionPane.showInputDialog(
				app,
				"Enter width :",
				"NewMaze",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				""));
		int height = Integer.parseInt((String)JOptionPane.showInputDialog(
				app,
				"Enter height :",
				"NewMaze",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				""));

		app.getUI().addMaze(width, height);
	}
}
