package graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class RemBoxButton extends JButton implements ActionListener	{

	private final MazeApp app;

	public RemBoxButton(MazeApp app)	{
		super("Remove box");
		this.app = app;
		setPreferredSize(new Dimension(120, 20));

		addActionListener(this);
	}

	public final void actionPerformed(ActionEvent evt)	{

	}
}
