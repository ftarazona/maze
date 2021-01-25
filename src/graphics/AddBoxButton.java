package graphics;

import java.awt.*;
import javax.swing.*;

public class AddBoxButton extends JButton	{

	private final MazeApp app;

	public AddBoxButton(MazeApp app)	{
		super("Add box");
		this.app = app;
		setPreferredSize(new Dimension(120, 20));
	}
}
