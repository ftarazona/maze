package graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SaveButton extends JButton	implements ActionListener	{

	private final MazeApp app;

	public SaveButton(MazeApp app)	{
		super("Save maze");
		this.app = app;
		setPreferredSize(new Dimension(120, 20));

		addActionListener(this);
	}

	public final void actionPerformed(ActionEvent evt)	{

	}
}
