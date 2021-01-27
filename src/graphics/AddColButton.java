package graphics;

import java.awt.*;
import javax.swing.*;

public class AddColButton extends JButton	{

	private final MazeApp app;

	public AddColButton(MazeApp app)	{
		super("Add column");
		this.app = app;
		setPreferredSize(new Dimension(120, 20));
	}
}
