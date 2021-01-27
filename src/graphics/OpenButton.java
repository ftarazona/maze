package graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class OpenButton extends JButton implements ActionListener	{

	private final MazeApp app;

	public OpenButton(MazeApp app)	{
		super("Open maze");
		this.app = app;
		setPreferredSize(new Dimension(120, 20));

		addActionListener(this);
	}

	public final void actionPerformed(ActionEvent evt)	{

	}
}
