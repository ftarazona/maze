package graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class RemFlagButton extends JButton implements ActionListener	{

	private final MazeApp app;

	public RemFlagButton(MazeApp app)	{
		super("Remove flag");
		this.app = app;
		setPreferredSize(new Dimension(120, 20));

		addActionListener(this);
	}

	public final void actionPerformed(ActionEvent evt)	{

	}
}
