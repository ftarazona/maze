package graphics;

import java.awt.*;
import javax.swing.*;

public class AddRowButton extends JButton	{

	private final MazeApp app;

	public AddRowButton(MazeApp app)	{
		super("Add row");
		this.app = app;
		setPreferredSize(new Dimension(120, 20));
	}
}
