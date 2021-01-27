package graphics;

import java.awt.*;
import javax.swing.*;

public class RemFlagButton extends JButton	{

	private final MazeApp app;

	public RemFlagButton(MazeApp app)	{
		super("Remove flag");
		this.app = app;
		setPreferredSize(new Dimension(120, 20));
	}
}
