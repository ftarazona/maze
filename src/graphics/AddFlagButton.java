package graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class AddFlagButton extends JButton implements ActionListener	{

	private final MazeApp app;

	public AddFlagButton(MazeApp app)	{
		super("Add flag");
		this.app = app;
		setPreferredSize(new Dimension(120, 20));

		addActionListener(this);
	}

	public final void actionPerformed(ActionEvent evt)	{

	}
}
