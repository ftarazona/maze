package graphics;

import java.awt.*;
import javax.swing.*;

public class AddFlagButton extends JButton	{

	private final MazeApp app;

	public AddFlagButton(MazeApp app)	{
		super("Add flag");
		this.app = app;
		setPreferredSize(new Dimension(120, 20));
	}
}
