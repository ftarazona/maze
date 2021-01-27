package graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class RemRowButton extends JButton implements ActionListener	{

	private final MazeApp app;

	public RemRowButton(MazeApp app)	{
		super("Remove row");
		this.app = app;
		setPreferredSize(new Dimension(120, 20));

		addActionListener(this);
	}

	public final void actionPerformed(ActionEvent evt)	{

	}
}
